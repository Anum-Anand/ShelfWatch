package TestShelfWatch.TestShelfWatch;

import org.testng.annotations.Test;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.testng.annotations.Test;

import Resources.APIUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AnalyticsFifteen
{
	public StringBuilder result;
	public RequestSpecification httpRequest;
	public APIUtils api;
	public String type;
	public Response response;
	public String responseBody;
	public int statusCode;
	
	
	
	public AnalyticsFifteen() 
	{
		httpRequest=RestAssured.given();
		api = new APIUtils();
		result = new StringBuilder();
		type = "application/x-www-form-urlencoded";
	}

	public void loginVisicoolers() throws UnsupportedEncodingException 
	{
		String url = "https://app.shelfwatch.karna.ai/api/login";
		
		result.append(URLEncoder.encode("username", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode("visicoolers2@karna.ai", "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("password", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode("pdkarna@010", "UTF-8"));
	
         
		httpRequest.header("Content-Type", "application/json");		 
        type = "application/x-www-form-urlencoded";
        
        response =  api.callPostRequest(result, url, type);
        responseBody = response.getBody().asString();
        
        statusCode = api.getStatusCode(response,url);
           
		result.delete(0, result.length());
	}
	
	@Test
	public void test() {
	
		ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
		ArrayList<String> singleList = new ArrayList<String>();
		singleList.add("hello");
		singleList.add("world");
		listOLists.add(singleList);
	
		ArrayList<String> anotherList = new ArrayList<String>();
		anotherList.add("this is another list");
		listOLists.add(anotherList);
		System.out.println(listOLists);
		
		
	}
}
