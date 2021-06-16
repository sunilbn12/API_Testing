package Serialization_GoogleMaps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo_Classes.AddPlaceRequestBody;
import pojo_Classes.Location;

public class AddPlace_Serialize {

	/***************************************
	 * Serialization
	 ***************************************/
	AddPlaceRequestBody ap = new AddPlaceRequestBody();
	Location loc = new Location();
	String place;

	@Test
	public void addPlace() {

		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http:google.com");
		ap.setName("name");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setLanguage("language");

		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		ap.setTypes(types);

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").contentType(ContentType.JSON).body(ap).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).assertThat()
				.body("status", equalTo("OK")).assertThat().headers("server", equalTo("Apache/2.4.18 (Ubuntu)")).log()
				.all().extract().response().asString();

		JsonPath js = new JsonPath(response);
		System.out.println("place id is: ---> " + js.get("place_id").toString());

		place = js.get("place_id").toString();

		System.out.println(ap.getAccuracy());
		System.out.println(ap.getTypes().get(1));

	}

	@Test(dependsOnMethods = { "addPlace" })
	public void getPlace() {

		System.out.println("starting test: --->" + "getPlace");

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		ResponseClass response = given().queryParam("key", "qaclick123").queryParam("place_id", place).log().all()
				.expect().defaultParser(Parser.JSON).when().get("/maps/api/place/get/json").as(ResponseClass.class);

		System.out.println(response.getLocation().getLatitude());
		System.out.println(response.getAddress());

		/*
		 * String response = given().queryParam("key",
		 * "qaclick123").queryParam("place_id", place).log().all().when()
		 * .get("/maps/api/place/get/json").then().log().all().extract().response().
		 * asString();
		 * 
		 * System.out.println("response is" + "/n" + response);
		 */

	}

}
