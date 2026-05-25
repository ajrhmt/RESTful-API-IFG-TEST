import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

Map producerPayload = [
	title : 'Katalon REST producer test',
	body  : 'Message created by Katalon test automation',
	userId: 1
]

def producerResponse = CustomKeywords.'api.RestApiHelper.postJson'(GlobalVariable.REST_BASE_URL, '/posts', producerPayload)
CustomKeywords.'api.RestApiHelper.verifyStatus'(producerResponse, 201)

Map producerResult = CustomKeywords.'api.RestApiHelper.parseJson'(producerResponse)
assert producerResult.title == producerPayload.title
assert producerResult.body == producerPayload.body
assert producerResult.userId == producerPayload.userId
assert producerResult.id != null

KeywordUtil.logInfo("REST producer created data with id: ${producerResult.id}")

def consumerResponse = CustomKeywords.'api.RestApiHelper.get'(GlobalVariable.REST_BASE_URL, '/posts/1')
CustomKeywords.'api.RestApiHelper.verifyStatus'(consumerResponse, 200)

Map consumerResult = CustomKeywords.'api.RestApiHelper.parseJson'(consumerResponse)
assert consumerResult.id == 1
assert consumerResult.userId != null
assert consumerResult.title
assert consumerResult.body

KeywordUtil.logInfo("REST consumer received data: ${consumerResult}")
