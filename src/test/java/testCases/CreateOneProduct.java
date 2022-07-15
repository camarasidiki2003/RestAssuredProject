package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateOneProduct {
	/*
	 * given: all input
	 * details(baseURI,Headers,Payload/Body,QueryParameters,Authorization) when:
	 * submit api requests(Http method,Endpoint/Resource) then: validate
	 * response(status code, Headers, responseTime, Payload/Body)
	 * 
	 * 
	 HTTP Method: POST
EndpointUrl: https://techfios.com/api-prod/api/product/create.php
Header:
"Content-Type" : "application/json; charset=UFT-8"
Payload/Body: "application/json; charset=UTF-8"
"id":"value"
POST https://techfios.com/api-prod/api/product/create.php
  
{
    "name" : "Amazing Pillow 2.0",
    "price" : "199",
    "description" : "The best pillow for amazing programmers.",
    "category_id" : 2,
    "created" : "2018-06-01 00:35:07"
}
	 */
	@Test
   public void readAllProducts() { //	//RestAssured.baseURI="";
		Response response=
				
		given()
		      .baseUri("https://techfios.com/api-prod/api/product")
		      .header("Content-Type", "application/json;charset=UTF-8")
		      .header("Authorizatio","Bearer         ")
		      .body(new File ("src\\main\\resources\\data\\CreateProductPayload.json"))		         
		      .relaxedHTTPSValidation().
		when()
		      .post("/create.php").
		then()
		      //.statusCode(200)
		      //.header("Content-Type\" : \"application/json; charset=UFT-8");
		      //.statusLine("OK")
		      .extract().response();
		int actualStatusCode=response.getStatusCode();
		Assert.assertEquals(actualStatusCode, 200);
		response.getHeader("Content-Type");
		String actualHeader = response.getHeader("Content-Type");
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Actual Response Time:" + actualResponseTime);
		
		if (actualResponseTime<=2000) {
			System.out.println("Response is within time frame");
		} else {
			System.out.println("Response is out of time frame");
		}
		//Assert.assertEquals(actualResponseTime, + actualResponseTime);
		String actualResponseBody = response.getBody().asString();
	     response.prettyPrint();
		System.out.println("Actual Response Body:" + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);
		String firstProductId = jp.get("records[0].id");
		System.out.println("First Product Id:"+firstProductId);
		
		if(firstProductId !=null) {
			System.out.println("First Product Id is not null");
			
		}else { System.out.println("First Product Id is null");
			
		}
		}
		
		
		
	}

