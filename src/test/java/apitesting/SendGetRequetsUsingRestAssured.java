package apitesting;

import static io.restassured.RestAssured.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
*/
public class SendGetRequetsUsingRestAssured {
	
	/*
	 * Set authentication: - Basic Auth / Bearer token using Headers
	 * 
	 * Request --> set of request specifications
	 * 
	 * 
	 * Response --> do validations
	 * 
	 * Content type -->
	 * 
	 * 1. using contentType()
	 * 2. adding the content type in the headers
	 * 
	 */

	public static void main(String[] args) {
	
		Response response = given().auth().basic("sk_test_8Ek0AIAk0fyvUyJiDbG5ZOIN", "").formParams("limit", 3).formParam("email", "abc@g.com")
							.get("https://api.stripe.com/v1/customers");
		
		
		//response.prettyPrint();
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		

		
		System.out.println("Response code --> "+response.statusCode());
		//given().contentType(ContentType.JSON);
		//given().contentType("application/json");
		//given().header("content-type","application/json").auth().basic(userName, password);
	}

	
/*	public static void main(String[] args) {
		
		Response response = given().header("Authorization", "Bearer sk_test_8Ek0AIAk0fyvUyJiDbG5ZOIN")
				.get("https://api.stripe.com/v1/customers/cus_FVSIfSz4Yrqqn6");
	
		response.prettyPrint();
	
	}*/
	
}
