package Resources;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

	public RequestSpecification httpRequest;
	int statusCode;
	public Response response;
	public String result ;
	
	
	public APIUtils()
	{
		httpRequest = RestAssured.given();
	}
	
	
	
	public Response callPostRequest(StringBuilder result,String url , String type) 
	{
		 response = httpRequest.body(result.toString()).contentType(type).post(url);
		 return response;
	}
	
	
	
	public int getStatusCode(Response response, String url)
	{
		statusCode = response.getStatusCode();	
		System.out.println("Status Code for api " + "'"+ url + "' " + statusCode);
		
		return statusCode;
	}
	
	
	
	public Response callGetRequest(String url)
	{
		 response = httpRequest.request(Method.GET,url);
		 return response;					
	}
	
}
