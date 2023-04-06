package common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Put_common_method_api {
	public static int responsestatuscode_extractor(String baseuri ,
			String resource , String requestBody)
	{
		RestAssured.baseURI=baseuri;
		int response_statuscode = given().header("Content-Type","application/json").
				body(requestBody).when().put(resource).then().
				extract().statusCode();
		return response_statuscode;
	}
	public static String responsebody_extractor(String baseuri ,
			String resource , String requestBody)
	{
		RestAssured.baseURI=baseuri;
		String response_body = given().header("Content-Type","application/json")
				.body(requestBody).when().put(resource).then().
				extract().response().asString();
		return response_body;
	}

}