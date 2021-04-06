package com.JsonParsing;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Demo1 {

	@Test
	public void parseJson() {
		JsonPath js = new JsonPath(new File(
				"C:\\Users\\Sunil\\Documents\\RestAPI_Resources\\RestAssuredAPI\\src\\test\\java\\com\\JsonParsing\\Complex.json"));
		int count = js.getInt("batters.batter.size()");
		System.out.println(count);

		for (int i = 0; i < count; i++) {

			String s = js.get("batters.batter[" + i + "].type");
			if (s.equalsIgnoreCase("Blueberry")) {
				System.out.println(s.toString() + " found at " + i);
				int id=js.getInt("batters.batter[" + i + "].id");
				System.out.println(s+ " id is "+id);
				break;

			}

		}
		
		int count1 = js.getInt("topping.size()");
		System.out.println(count1);
		
		for (int i = 0; i < count1; i++) {

			String s = js.get("topping[" + i + "].type");
			int ide=js.getInt("topping[" + i + "].id");
			
			System.out.println(s+"---"+ide);
			

			}

		}
	

}
