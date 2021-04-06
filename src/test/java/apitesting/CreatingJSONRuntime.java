package apitesting;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatingJSONRuntime {

	public static void main(String[] args) {


		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", "rahul@gmail.com");
		jsonObject.put("firstName", "Rahul");
		jsonObject.put("lastName", "Arora");
		
		JSONArray listOfMobiles = new JSONArray();
		listOfMobiles.put(2343243);
		listOfMobiles.put(2342344);
		
		jsonObject.put("mobile", listOfMobiles);
		
		JSONObject address = new JSONObject();
		address.put("flatNo", "A-131");
		address.put("city", "New Delhi");
		address.put("State", "Delhi");
		address.put("country", "India");
		
		jsonObject.put("address", address);
		
		System.out.println(address);
		
		
		Response response = given().contentType(ContentType.JSON).body(jsonObject.toString()).log().all().post("http://localhost:8080/api/users");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
	}

}
