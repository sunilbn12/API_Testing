package apitesting;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PassComplexJsonInBody {

	public static void main(String[] args) {

		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("email", "rahul@gmail.com");
		map.put("firstName", "Rahul");
		map.put("lastName", "Arora");
		
		ArrayList<Integer> listOfMoibleNos = new ArrayList<Integer>();
		listOfMoibleNos.add(234324234);
		listOfMoibleNos.add(234323422);
		
		map.put("mobile", listOfMoibleNos);
		
		HashMap<String,String> address = new HashMap<String,String>();
		address.put("flatNo", "A-131");
		address.put("city", "New Delhi");
		address.put("State", "Delhi");
		address.put("country", "India");
		
		map.put("address", address);
		
		
		Response response = given().contentType(ContentType.JSON).body(map).log().all().post("http://localhost:8080/api/users");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		

	}

}
