package apitesting;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SendPOSTRequestUsingRestAssured {

	/*public static void main(String[] args) {


		Response response = given().auth().basic("sk_test_8Ek0AIAk0fyvUyJiDbG5ZOIN", "")
		.formParam("name", "Rahul Arora").formParam("email", "trainer@way2automation.com")
		.formParam("description", "This is new POST request from Rest Assured API").post("https://api.stripe.com/v1/customers");

		response.prettyPrint();
		
	}*/
	
	
	public static void main(String[] args) {
		
		//String bodyString = "{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
		
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "rahularora@gmail.com");
		map.put("firstName", "Rahul");
		map.put("lastName", "Arora");*/
		
		Response response = given().contentType(ContentType.JSON).body(new File("./users.json")).log().all().post("http://localhost:8080/api/users");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
		/*response = given().contentType(ContentType.JSON).get("http://localhost:8080/api/users");
		response.prettyPrint();
		System.out.println(response.getStatusCode());*/
	}
	
	

}
