package kafka

import java.time.Duration
import java.util.Collections
import java.util.Properties

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.errors.TopicExistsException
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer

class KafkaConsumerHelper {
	@Keyword
	Map consumeJsonMessageFromMockTopic(String topic, Map payload) {
		MockConsumer<String, String> consumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST)
		TopicPartition partition = new TopicPartition(topic as String, 0)

		try {
			consumer.assign(Collections.singletonList(partition))
			consumer.updateBeginningOffsets([(partition): 0L])

			String value = JsonOutput.toJson(payload)
			consumer.addRecord(new ConsumerRecord<String, String>(topic as String, 0, 0L, payload.id as String, value))

			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000))
			if (records.isEmpty()) {
				KeywordUtil.markFailedAndStop("No Kafka mock message consumed from topic '${topic}'.")
			}

			String consumedValue = records.iterator().next().value()
			KeywordUtil.logInfo("Kafka mock consumer received message from topic '${topic}': ${consumedValue}")
			return new JsonSlurper().parseText(consumedValue) as Map
		} finally {
			consumer.close()
		}
	}

	@Keyword
	void ensureTopicExists(String bootstrapServers, String topic) {
		Properties props = new Properties()
		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers as String)
		props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, '10000' as String)
		props.put(AdminClientConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, '15000' as String)

		AdminClient adminClient = AdminClient.create(props)

		try {
			if (adminClient.listTopics().names().get().contains(topic as String)) {
				KeywordUtil.logInfo("Kafka topic '${topic}' already exists.")
				return
			}

			NewTopic newTopic = new NewTopic(topic as String, 1, (short) 1)
			adminClient.createTopics(Collections.singletonList(newTopic)).all().get()
			KeywordUtil.logInfo("Kafka topic '${topic}' created.")
		} catch (Exception exception) {
			if (exception.cause instanceof TopicExistsException) {
				KeywordUtil.logInfo("Kafka topic '${topic}' already exists.")
				return
			}

			throw exception
		} finally {
			adminClient.close()
		}
	}

	@Keyword
	void sendJsonMessage(String bootstrapServers, String topic, Map payload) {
		ensureTopicExists(bootstrapServers, topic)

		Properties props = new Properties()
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers as String)
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.name as String)
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.name as String)
		props.put(ProducerConfig.ACKS_CONFIG, 'all' as String)

		KafkaProducer<String, String> producer = new KafkaProducer<>(props)

		try {
			String value = JsonOutput.toJson(payload)
			producer.send(new ProducerRecord<String, String>(topic as String, payload.id as String, value)).get()
			KeywordUtil.logInfo("Kafka setup message sent to topic '${topic}': ${value}")
		} finally {
			producer.close()
		}
	}

	@Keyword
	Map consumeJsonMessage(String bootstrapServers, String topic, String groupId, int timeoutSeconds) {
		String message = consumeMessage(bootstrapServers, topic, groupId, timeoutSeconds)
		return new JsonSlurper().parseText(message) as Map
	}

	@Keyword
	String consumeMessage(String bootstrapServers, String topic, String groupId, int timeoutSeconds) {
		Properties props = new Properties()
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers as String)
		props.put(ConsumerConfig.GROUP_ID_CONFIG, (groupId + '-' + System.currentTimeMillis()) as String)
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.name as String)
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.name as String)
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, 'earliest' as String)
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, 'false' as String)

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)
		long deadline = System.currentTimeMillis() + (timeoutSeconds * 1000L)

		try {
			consumer.subscribe(Collections.singletonList(topic))

			while (System.currentTimeMillis() < deadline) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000))
				if (!records.isEmpty()) {
					String value = records.iterator().next().value()
					KeywordUtil.logInfo("Kafka message received from topic '${topic}': ${value}")
					return value
				}
			}

			KeywordUtil.markFailedAndStop("No Kafka message received from topic '${topic}' within ${timeoutSeconds} seconds.")
			return null
		} finally {
			consumer.close()
		}
	}
}
