package specBuilder;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo_Classes.AddPlaceRequestBody;
import pojo_Classes.Location;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class AddPlace_Serialize_SpecBuilder {

	AddPlaceRequestBody ap = new AddPlaceRequestBody();
	Location loc = new Location();

	String place;

	@Test
	public void addPlace() {

		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
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

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req).body(ap);

		Response response = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();

		String responseString = response.asString();
		System.out.println(responseString);
		
		JsonPath js=new JsonPath(responseString);
		js.getString("place_id");

	}

	@Test(priority = -1, dependsOnMethods = { "addPlace" })
	public void getPlace() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().queryParam("key", "qaclick123").queryParam("place_id", place).when()
				.get("/maps/api/place/get/json").then().assertThat().statusCode(200).log().all().extract().response()
				.asString();

		System.out.println(ap.getLocation().getLat());

	}

}
