package libraryAPI;

public class Payload {
	
	public static String addBook(String bookName,String isbn,String aisl)
	{
		String s="{\r\n" + 
				"\"name\":\""+bookName+"\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisl+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}";
		
		return s;
	}

}
