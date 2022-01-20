package com.qa.testcases;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

import com.qa.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {

    TestBase testBase;
    String serviceurl;
    String apiURL;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws IOException {

        testBase = new TestBase();

        serviceurl = prop.getProperty("URL");
        apiURL = prop.getProperty("serviceURL");
        url = serviceurl + apiURL;

    }

    @Test
    public void getTest() throws IOException {

        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        //2. Status Code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("StatusCode ->"+ statusCode);
        Assert.assertEquals(RESPONSE_STATUS_CODE_200, statusCode,"Status Code didn't Matched");

        // 3. Json String
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("JSON API Response "+closeableHttpResponse);

        //Hint: For single Json Object assertions
        //String s = TestUtil.getValueByJPath(responseJson, "/**jsonobject");
        //System.out.println(s);

        //Multiple Json Objects or Json Array Assertions
        for (int i = 0; i< JSON_RESPONSE_SIZE ; i++){
            for (int j=0 ; j < 1; j++){
                    String promotionId = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/promotionId");
                    System.out.println(i + " " + promotionId);
                    Assert.assertNotNull(promotionId);
                    String orderId = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/orderId");
                    System.out.println("orderId " + orderId);
                    Assert.assertNotNull(orderId);
                    String promoArea = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/promoArea["+j+"]");
                    System.out.println("promoArea " + promoArea);
                    Assert.assertNotNull(promoArea);
                    String showPrice = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/showPrice");
                    System.out.println("showPrice " + showPrice);
                    Assert.assertNotNull(showPrice);
                    String showText = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/showText");
                    System.out.println("showText " + showText);
                    Assert.assertNotNull(showText);
                    String localizedTexts = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/localizedTexts");
                    System.out.println("localizedTexts " + localizedTexts);
                    Assert.assertNotNull(localizedTexts);
//                    String properties = TestUtil.getValueByJPath(responseJson, "/promotions["+i+"]/properties["+j+"]/programType");
//                    System.out.println("properties " + properties);
//                    Assert.assertNotNull(properties);

            }

        }

        //4. Headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header: headersArray){
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array ->"+ allHeaders);

    }
}
