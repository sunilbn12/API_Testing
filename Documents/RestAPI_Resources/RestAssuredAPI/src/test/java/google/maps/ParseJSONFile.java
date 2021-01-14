package google.maps;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.payloads.PayloadString;

import io.restassured.path.json.JsonPath;

public class ParseJSONFile {
	
	@Test
	public void parseJSon()
	{
		File jsonFile=new File("C:\\Users\\Sunil\\Documents\\RestAPI_Resources\\RestAssuredAPI\\newuser.json");
		System.out.println(jsonFile.getAbsolutePath());
		
		JsonPath js=new JsonPath(jsonFile);

		
		System.out.println(js.get("email").toString());
		
		System.out.println(js.get("mobile[0]").toString());
		
		System.out.println(js.get("address.size()").toString());
	}
	

}
