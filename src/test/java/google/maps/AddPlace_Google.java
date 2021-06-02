package google.maps;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlace_Google {

	@Test(alwaysRun = true, enabled = true)
	public void addPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123")
				.queryParam("place_id", "f3d752aa4141dd7e9bb208dca7779962").contentType("application/json").log().all()
				.when().get("maps/api/place/get/json").then().assertThat().statusCode(200).assertThat()
				.statusLine("HTTP/1.1 200 OK").log().all().extract().response().asString();

		JsonPath js = new JsonPath(response);
		String actValue = js.get("address");
		System.out.println(actValue);
		System.out.println(js.getString("name"));

		String sbfr = new String("$234.05M");
		sbfr.replace("$", "");
		// sbfr.substring(1, sbfr.length() - 1);
		System.out.println(sbfr);

	}

	

}
