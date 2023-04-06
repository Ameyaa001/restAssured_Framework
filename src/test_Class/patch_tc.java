package test_Class;
import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.Patch_common_method_api;
import request_repository.Patch_request_repository;
import io.restassured.path.json.JsonPath;

public class patch_tc {
	@Test
	public static void orchestrator() throws IOException
	{    
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseUri = Patch_request_repository.baseuri();
		String resource = Patch_request_repository.resource();
		String requestBody = Patch_request_repository.Patch_request_tc1();
		for(int i=0 ; i<5 ; i++) 
        {
		 responseStatuscode = Patch_common_method_api.responsestatuscode_extractor(baseUri, resource, requestBody);
		 if (responseStatuscode == 200)
		  {
			responseBody = Patch_common_method_api.responsebody_extractor(baseUri, resource, requestBody);
			responseBodyValidator(responseBody);
			
			break;
	      }
          else
          {
        	  System.out.println("correct status code is not found in the iteration " + i);
          }
        } 
		common_method.common_method_api.evidanceFileCreator("PATCH_TC1",requestBody,responseBody);
		
		Assert.assertEquals(responseStatuscode, 200);
		
     }
 
    public static void responseBodyValidator(String responseBody)
	{
		// create jsonPath object to extract responsebody parameters
		JsonPath jsp = new JsonPath(responseBody);

		// extract responsebody parameters
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_updatedAt = jsp.getString("updatedAt");

		System.out.println("name : " + res_name + "\njob : " + res_job );

		// validate responsebody parameter
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		
		// extract date from updatedAt parameter
		String actual_date = res_updatedAt.substring(0, 10);
		String current_date = LocalDate.now().toString(); // Create a date object
		Assert.assertEquals(actual_date, current_date);
		System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);

	}
}