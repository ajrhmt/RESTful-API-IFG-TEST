package api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class RestApiHelper {
	@Keyword
	ResponseObject postJson(String baseUrl, String path, Map payload) {
		RequestObject request = buildRequest('POST', baseUrl, path, payload)
		return WS.sendRequest(request)
	}

	@Keyword
	ResponseObject get(String baseUrl, String path) {
		RequestObject request = buildRequest('GET', baseUrl, path, null)
		return WS.sendRequest(request)
	}

	@Keyword
	Map parseJson(ResponseObject response) {
		return new JsonSlurper().parseText(response.getResponseBodyContent()) as Map
	}

	@Keyword
	void verifyStatus(ResponseObject response, int expectedStatus) {
		WS.verifyResponseStatusCode(response, expectedStatus)
	}

	private RequestObject buildRequest(String method, String baseUrl, String path, Map payload) {
		String normalizedBaseUrl = baseUrl.replaceAll('/+$', '')
		String normalizedPath = path.startsWith('/') ? path : "/${path}"

		RequestObject request = new RequestObject("${method} ${normalizedPath}")
		request.setRestRequestMethod(method)
		request.setRestUrl("${normalizedBaseUrl}${normalizedPath}")
		request.setHttpHeaderProperties([
			new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json'),
			new TestObjectProperty('Accept', ConditionType.EQUALS, 'application/json')
		])

		if (payload != null) {
			request.setBodyContent(new HttpTextBodyContent(JsonOutput.toJson(payload), 'UTF-8', 'application/json'))
		}

		return request
	}
}
