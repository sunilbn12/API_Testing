package libraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddBook {

	@Test(testName = "Add a Book",enabled=false)
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";

		String response = given()
				.body(new File("C:\\Users\\Sunil\\Documents\\RestAPI_Resources\\RestAssuredAPI\\AddBook.json")).when()
				.post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added")).header("access-control-allow-methods", equalTo("POST"))
				.extract().response().asString();

		JsonPath js = new JsonPath(response);
		js.get("Msg").toString();

	}

	@Test
	public void getBook() {
		JsonPath js=new JsonPath(new File("C:\\Users\\Sunil\\Documents\\RestAPI_Resources\\RestAssuredAPI\\AddBook.json"));
		String id = js.get("aisle").toString() + js.get("author").toString();
		System.out.println("Id is " + id);
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given()
				.when().get("Library/GetBook.php?AuthorName=John foe")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
	
	@Test(dataProvider="Book")
	public void addBookUsingDP(String name,String isbn,String aisl) {
		RestAssured.baseURI = "http://216.10.245.166";

		String response = given()
				.body(Payload.addBook(name, isbn, aisl)).when()
				.post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added")).header("access-control-allow-methods", equalTo("POST"))
				.extract().response().asString();

		JsonPath js = new JsonPath(response);
		js.get("Msg").toString();

	}
	
	@DataProvider(name="Book")
	public Object[][] books(){
		return new Object[][] 
		    	{
		            { "Gru99", "123","abs" },
		            { "Krshna", "446","lks" },
		            { "Bhuesh", "567","wer" }
		        };
	}

}
