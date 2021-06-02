package google.maps;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPlace_google1 {

	public String payload = "{\r\n" + "	\"location\": {\r\n" + "		\"lat\": 108.383494,\r\n"
			+ "		\"lng\": 33.427362\r\n" + "	},\r\n" + "	\"accuracy\": 100,\r\n"
			+ "	\"name\": \"Shri Bhageshweri Nilay\",\r\n" + "	\"phone_number\": \"(+91) 983 893 3937\",\r\n"
			+ "	\"address\": \"BLDEA Road\",\r\n" + "	\"types\": [\r\n" + "		\"Kirana shop\",\r\n"
			+ "		\" fragrance shop\"\r\n" + "	],\r\n" + "	\"website\": \"www.api.com\",\r\n"
			+ "	\"language\": \"Kannada\"\r\n" + "}";

	String place;

	@Test(alwaysRun = true, singleThreaded = false)
	public void addPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().queryParam("key", "qaclick123").body(payload)
				/*
				 * body("{\r\n" + "	\"location\": {\r\n" + "		\"lat\": 108.383494,\r\n" +
				 * "		\"lng\": 33.427362\r\n" + "	},\r\n" + "	\"accuracy\": 100,\r\n" +
				 * "	\"name\": \"Shri Bhageshweri Nilay\",\r\n" +
				 * "	\"phone_number\": \"(+91) 983 893 3937\",\r\n" +
				 * "	\"address\": \"BLDEA Road\",\r\n" + "	\"types\": [\r\n" +
				 * "		\"Kirana shop\",\r\n" + "		\" fragrance shop\"\r\n" +
				 * "	],\r\n" + "	\"website\": \"www.api.com\",\r\n" +
				 * "	\"language\": \"Kannada\"\r\n" + "}")
				 */
				.header("Content-Type", "application/json").when().post("/maps/api/place/add/json").then().assertThat()
				.body("scope", equalTo("APP")).statusCode(200).assertThat().body("status", equalTo("OK"))
				.headers("access-control-allow-headers",
						"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
				.assertThat().contentType("application/json;charset=UTF-8").log().all().extract().response().asString();

		JsonPath js = new JsonPath(response);
		place = js.getString("place_id");

		System.out.println(place);

	}

	@Test(priority = -1, dependsOnMethods = { "addPlace" })
	public void getPlace() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().queryParam("key", "qaclick123").queryParam("place_id", place).when()
				.get("/maps/api/place/get/json").then().assertThat().statusCode(200).log().all().extract().response()
				.asString();

		JsonPath js = new JsonPath(response);
		String addess = js.getString("address");
		System.out.println(addess);

		int type1 = js.getInt("types.size()");
		System.out.println(type1);

		String str = js.get("types[1]");
		System.out.println(str);

	}

}
