package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadOneProduct {
	/*
	 * GET https://techfios.com/api-prod/api/product/read_one.php?id=65 HTTP Method:
	 * GET EndpointUrl: https://techfios.com/api-prod/api/product/read_one.php?id=65
	 * Header: "Content-Type": "application/json" QueryParam: "id":"value"
	 */
	@Test
   public void readOneProduct() { //	//RestAssured.baseURI="";
		Response response=
		given()		      
		      .baseUri("https://techfios.com/api-prod/api/product")
		      .header("Content-Type", "application/json")
		      .auth().preemptive().basic("demo@techfios.com","abc123")
		      .queryParam("id", "4659")
		      .relaxedHTTPSValidation().
		when()
		      .get("/read_one.php").
		then()
		      .extract().response();
		int actualStatusCode=response.getStatusCode();
		Assert.assertEquals(actualStatusCode, 200);
		//response.getHeader("Content-Type");
		
		String actualHeader = response.getHeader("Content-Type");
		Assert.assertEquals(actualHeader, "application/json");
		
		String actualResponseBody = response.getBody().asString();
		System.out.println("Actual Response Body:" +actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);
		
		String productId = jp.get("Id");
		System.out.println("Product Id:.... "+productId);
		Assert.assertEquals(productId, "4659");
		
		String productName = jp.get("name");
		System.out.println("Product Name: "+productName);
		Assert.assertEquals(productName,"Amazing Pillow 2.0 By Sangeeta");
		
		String productDescription = jp.get("Description");
		System.out.println("Product Description: "+productDescription);
		Assert.assertEquals(productDescription,"The best pillow for amazing programmers.");
		
		String productPrice = jp.get("Price");
		System.out.println("Product Price: "+productPrice);
		Assert.assertEquals(productId,"199");
		
	}
		
		
		
	}

