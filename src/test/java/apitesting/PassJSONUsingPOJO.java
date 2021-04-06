package apitesting;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PassJSONUsingPOJO {
	
	
	
	public static void main(String[] args) {
		
		
		User user = new User("trainer@way2automation", "Rahul", "Arora","A-131","New Delhi","Delhi","India");
		
		
		//varargs
		user.setMobileNumbers(123213,234234,2342434);
		Response response = given().contentType(ContentType.JSON).body(user).log().all().post("http://localhost:8080/api/users");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
	}

}
