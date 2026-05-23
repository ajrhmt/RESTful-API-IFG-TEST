<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>GetPosts</name>
   <tag></tag>
   <elementGuidId>54c28b8c-2497-4374-964c-ed577a805057</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;\u003c?xml version\u003d\&quot;1.0\&quot; encoding\u003d\&quot;UTF-8\&quot;?\u003e\n\u003cWebServiceRequestEntity\u003e\n   \u003cdescription\u003e\u003c/description\u003e\n   \u003cname\u003eGET_All_Books\u003c/name\u003e\n   \u003ctag\u003e\u003c/tag\u003e\n   \u003celementGuidId\u003e8c594c14-f9b0-4040-a3b1-035fc445c7f1\u003c/elementGuidId\u003e\n   \u003cselectorMethod\u003eBASIC\u003c/selectorMethod\u003e\n   \u003csmartLocatorEnabled\u003efalse\u003c/smartLocatorEnabled\u003e\n   \u003cuseRalativeImagePath\u003efalse\u003c/useRalativeImagePath\u003e\n   \u003cautoUpdateContent\u003etrue\u003c/autoUpdateContent\u003e\n   \u003cconnectionTimeout\u003e0\u003c/connectionTimeout\u003e\n   \u003cfollowRedirects\u003etrue\u003c/followRedirects\u003e\n   \u003chttpBody\u003e\u003c/httpBody\u003e\n   \u003chttpBodyContent\u003e{\n  \u0026quot;text\u0026quot;: \u0026quot;\u0026quot;,\n  \u0026quot;contentType\u0026quot;: \u0026quot;application/json\u0026quot;,\n  \u0026quot;charset\u0026quot;: \u0026quot;UTF-8\u0026quot;\n}\u003c/httpBodyContent\u003e\n   \u003chttpBodyType\u003etext\u003c/httpBodyType\u003e\n   \u003chttpHeaderProperties\u003e\n      \u003cisSelected\u003etrue\u003c/isSelected\u003e\n      \u003cmatchCondition\u003eequals\u003c/matchCondition\u003e\n      \u003cname\u003eContent-Type\u003c/name\u003e\n      \u003ctype\u003eMain\u003c/type\u003e\n      \u003cvalue\u003eapplication/json\u003c/value\u003e\n      \u003cwebElementGuid\u003eeb434e93-42b3-4220-8e34-79a147337836\u003c/webElementGuid\u003e\n   \u003c/httpHeaderProperties\u003e\n   \u003ckatalonVersion\u003e10.4.3\u003c/katalonVersion\u003e\n   \u003cmaxResponseSize\u003e0\u003c/maxResponseSize\u003e\n   \u003cmigratedVersion\u003e5.4.1\u003c/migratedVersion\u003e\n   \u003cpath\u003e\u003c/path\u003e\n   \u003crestRequestMethod\u003eGET\u003c/restRequestMethod\u003e\n   \u003crestUrl\u003e${GlobalVariable.BaseUrl}/api/v1/Books\u003c/restUrl\u003e\n   \u003cserviceType\u003eRESTful\u003c/serviceType\u003e\n   \u003csoapBody\u003e\u003c/soapBody\u003e\n   \u003csoapHeader\u003e\u003c/soapHeader\u003e\n   \u003csoapRequestMethod\u003e\u003c/soapRequestMethod\u003e\n   \u003csoapServiceEndpoint\u003e\u003c/soapServiceEndpoint\u003e\n   \u003csoapServiceFunction\u003e\u003c/soapServiceFunction\u003e\n   \u003csocketTimeout\u003e0\u003c/socketTimeout\u003e\n   \u003cuseServiceInfoFromWsdl\u003etrue\u003c/useServiceInfoFromWsdl\u003e\n   \u003cverificationScript\u003eimport static org.assertj.core.api.Assertions.*\n\nimport com.kms.katalon.core.testobject.RequestObject\nimport com.kms.katalon.core.testobject.ResponseObject\nimport com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS\nimport com.kms.katalon.core.webservice.verification.WSResponseManager\n\nimport groovy.json.JsonSlurper\nimport internal.GlobalVariable as GlobalVariable\n\nRequestObject request \u003d WSResponseManager.getInstance().getCurrentRequest()\n\nResponseObject response \u003d WSResponseManager.getInstance().getCurrentResponse()\n\n\nWS.verifyResponseStatusCode(response, 200)\n\nassertThat(response.getStatusCode()).isEqualTo(200)\u003c/verificationScript\u003e\n   \u003cwsdlAddress\u003e\u003c/wsdlAddress\u003e\n\u003c/WebServiceRequestEntity\u003e&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>1d16b0be-c93d-4bc9-8f29-ac2b823016a6</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.4.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>https://fakerestapi.azurewebsites.net/api/v1/</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
