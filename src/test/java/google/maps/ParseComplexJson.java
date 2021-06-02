package google.maps;

import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.payloads.PayloadString;

import io.restassured.path.json.JsonPath;

public class ParseComplexJson {
	
	@Test
	public void test1(){
		
		JsonPath js=new JsonPath(PayloadString.complexPayload());
		
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		System.out.println("---- purchase amount ----");
		System.out.println(js.get("dashboard.purchaseAmount").toString());
		String amount=js.get("dashboard.purchaseAmount").toString();
		
		System.out.println("------------------ validating the Actual & Expected amounts-------------");
		int sum=0;
		for(int i=0;i<count;i++)
		{
			System.out.println(js.get("courses["+i+"].title").toString()+" and price is "+js.getInt("courses["+i+"].price")+" and copies are "+js.getInt("courses["+i+"].copies"));
			
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			sum=(copies*price)+sum;
			
		}
		System.out.println("purchaseAmount "+sum);
		//Assert.assertEquals(sum, amount, "NOT SAme");
		
	//	Map<Object, Object> map = js.getMap(PayloadString.complexPayload());
		
		/*
		 * for (Entry<Object, Object> entry : map.entrySet())
		 * System.out.println("Key = " + entry.getKey().toString() + ", Value = " +
		 * entry.getValue().toString());
		 */
	}

}
