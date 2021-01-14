package com.JsonParsing;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Demo2 {

	@Test
	public void parseJson() {
		JsonPath js = new JsonPath(new File(
				"C:\\Users\\Sunil\\Documents\\RestAPI_Resources\\RestAssuredAPI\\src\\test\\java\\com\\JsonParsing\\Cmplx.json"));
		int count = js.getInt("Children.size()");
		System.out.println(count);
		int count1 = js.getInt("Firstname.size()");
		System.out.println(count1);

		/*
		 * for (int i = 0; i < count1; i++) {
		 * System.out.print("First Name is @ index "+i+" is "+js.get(" Firstname[" + i +
		 * "]")); System.out.print("Lastname is @ index "+i+" is "+js.get(" Lastname[" +
		 * i + "]")); System.out.print("City is @ index "+i+" is "+js.get(" City[" + i +
		 * "]")); System.out.print("State is @ index "+i+" is "+js.get(" State[" + i +
		 * "]")); System.out.println(""); }
		 */

		int child = js.getInt("Children.size()");
		for (int i = 0; i < child; i++) {

			int j = (js.get("Children[" + i + "].Name.size()"));
			for (int k = 0; k < j; k++) {
				System.out.println(js.get("Children[" + i + "].Name[" + k + "]"));
			}

			System.out.println("");
		}

	}

}
