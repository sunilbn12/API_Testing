package google.maps;

import org.json.JSONPropertyIgnore;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.payloads.PayloadString;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class AddGetUpdateDeletePlace {

	public String place;

	@Test(enabled = true, alwaysRun = true)
	public void addPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").body(PayloadString.payLoad()).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).body("status", equalTo("OK"))
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

		System.out.println(response);

		JsonPath js = new JsonPath(response);
		place = js.getString("place_id");

	}

	@Test(priority = 1, dependsOnMethods = { "addPlace" })
	public void updatePlace() {

		System.out.println("place id is--> " + place);
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place + "\",\r\n" + "\"address\":\"Uppin Colony\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
	}

	@Test(priority = 2, dependsOnMethods = { "addPlace", "updatePlace" })
	public void getPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").queryParam("place_id", place).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
				.body("address", equalTo("Uppin Colony")).extract().response().asString();
		System.out.println("************************************");
		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String actValue = js.get("address");
		Assert.assertEquals(actValue, "Uppin Colony");

	}

	/*********************************************************
	 * method to Delete the place
	 ********************************************************/
	@Test(priority=3,dependsOnMethods = { "addPlace", "updatePlace", "getPlace" })
	public void deletePlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123")
				.body("{\r\n" + "    \"place_id\":\"" + place + "\"\r\n" + "}\r\n" + "")
				.when()
				.delete("maps/api/place/delete/json").then().log().all().assertThat().statusCode(200)
				.body("status", equalTo("OK")).extract().response().asString();
		System.out.println("************************************");
		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String actValue = js.get("status");
		Assert.assertEquals(actValue, "OK");
	}
		
		@Test(priority = 4,dependsOnMethods = { "addPlace", "updatePlace", "getPlace","deletePlace"})
		public void getPlaceAfterDelete() {
			
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			try {
			String response1 = given().queryParam("key", "qaclick123").queryParam("place_id", place).when()
					.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
					.body("address", equalTo("Uppin Colony")).extract().response().asString();
			System.out.println("************************************");
			System.out.println(response1);

			JsonPath js1 = new JsonPath(response1);
			String actValue1 = js1.get("address");
			Assert.assertEquals(actValue1, "Uppin Colony");
			}
			catch (RuntimeException e) {
				// TODO: handle exception
			}
		}
		
		String name=" ";
		/**************************************************************
		 * Add place using JSON file
		 *************************************************************/
		@Test(priority=-1)
		public void addPlaceUsingJson()
		{
			String path="C:\\Users\\Sunil\\Documents\\RestAPI_Resources\\RestAssuredAPI\\src\\test\\java\\com\\payloads\\addplace.json";
			
			RestAssured.baseURI = "https://rahulshettyacademy.com";

			String response = given().log().all().queryParam("key", "qaclick123").body(new File(path)).when()
					.post("maps/api/place/add/json").then().assertThat().statusCode(200).body("status", equalTo("OK"))
					.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

			System.out.println(response);

			JsonPath js = new JsonPath(response);
			place = js.getString("place_id");
			
		}
		
		/*********************************************************
		 * Get place
		 ********************************************************/
		@Test(dependsOnMethods= {"addPlaceUsingJson"})
		public void getPLACEJSON(String addPlaceUsingJson)
		{
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			String response = given().queryParam("key", "qaclick123").queryParam("place_id", addPlaceUsingJson).when()
					.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
					.body("address", equalTo("Uppin Colony")).extract().response().asString();
			System.out.println("************************************");
			System.out.println(response);

			JsonPath js = new JsonPath(response);
			String actValue = js.get("address");
			Assert.assertEquals(actValue, "Uppin Colony");
		}

}
