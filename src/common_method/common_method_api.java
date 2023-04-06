package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_api {
	public static void evidanceFileCreator(String fileName,String request,String response) throws IOException
	{
		File newTextFile = new File("E:\\RestAssuredEvidance\\" +fileName+ ".txt");
		if(newTextFile.createNewFile())
		{
			FileWriter dataWriter = new FileWriter(newTextFile);
			dataWriter.write("Requestbody is :\n " +request+"\n\n");
			dataWriter.write("Responsebody is :\n " +response);
			dataWriter.close();
			System.out.println("Request & Response data saved in :" +newTextFile.getName());
		}
		else
		{
			System.out.println(newTextFile.getName()+"Already exist take a bakup of it !");
		}
	}
 
}