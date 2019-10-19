package TestShelfWatch.TestShelfWatch;

import org.testng.annotations.Test;

import Resources.ExcelSheet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.http.Method;

public class APIClients extends APIShelfWatch
{



	public String user_Id;
	public String dashboard_Id;
	public String project_Name;
	public String id_of_version;
	public String imageId;
	public int clientImageStatusCode;
	public String roleClient;
	public ExcelSheet excelClient;
	public int ViewStatusCode;
//	public StringBuilder clientResult;
	
	public APIClients() {
		excelClient = new ExcelSheet();
		excelClient.writeHeading();
	}
	
	@Test(priority=1)
	public void loginSalesClient() throws UnsupportedEncodingException, ParseException 
	{
		url = "https://app.shelfwatch.karna.ai/api/login";
		
	    result.append(URLEncoder.encode("username", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode("sales@paralleldots.com", "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("password", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode("123456", "UTF-8"));
	
        
        httpRequest.header("Content-Type", "application/json");		 
        
        response =  api.callPostRequest(result, url, type);
        responseBody = response.getBody().asString();
        
        statusCode = api.getStatusCode(response,url);
           
		result.delete(0, result.length());
		
		JSONObject LoginClientResponse = (JSONObject)parser.parse(responseBody);
		Object userId = LoginClientResponse.get("user_id");
		user_Id = userId.toString();
//		System.out.println(user_Id);

		
		Object roleCl = LoginClientResponse.get("role");
//		System.out.println(roleCl);
		roleClient = roleCl.toString();
//		role = roleClient.toString();
	}
	
	@Test(priority=2)
	public void getClientAPI(String project_name) throws UnsupportedEncodingException, ParseException
	{
		project_Name = project_name;
		url = "https://app.shelfwatch.karna.ai/api/client/dashboard/get";

	    result.append(URLEncoder.encode("client_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(user_Id, "UTF-8"));
	
        
        httpRequest.header("Content-Type", "application/json");		 
        
        response =  api.callPostRequest(result, url, type);
        responseBody = response.getBody().asString();
        
        statusCode = api.getStatusCode(response,url);
           
		result.delete(0, result.length());
		
		JSONArray getClientResponse = (JSONArray)parser.parse(responseBody);
		
		if (getClientResponse != null)
		{ 
//			System.out.println(project_Name);
		   for (int i=0;i<getClientResponse.size();i++)
		   {
				JSONObject  projects = (JSONObject) getClientResponse.get(i);
				Object dashboard_name = projects.get("dashboard_name");
				if(dashboard_name.equals(project_Name))
				{				
					Object dashboard_id = projects.get("dashboard_id");
					dashboard_Id = dashboard_id.toString();
//					System.out.println(dashboard_Id);
					
					JSONObject version = (JSONObject) projects.get("version");
					JSONArray InnerVersion = (JSONArray) version.get("version");
					if (InnerVersion != null)
					{ 
					   for (int j=0;j<InnerVersion.size();j++)
					   {
						  JSONObject versionId = (JSONObject) InnerVersion.get(0);							
							Object idOfVersion = versionId.get("id");
							id_of_version = idOfVersion.toString();
//							System.out.println(idOfVersion);						   
					   }
					 }
				 }
			}
		  }
		}
	
	
	public void viewClient() throws UnsupportedEncodingException, ParseException
	{
		url = "https://app.shelfwatch.karna.ai/api/client/analytics/view";

	    result.append(URLEncoder.encode("client_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(user_Id, "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("dashboard_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(dashboard_Id, "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("version", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(id_of_version, "UTF-8"));
	
        
        httpRequest.header("Content-Type", "application/json");		 
        
        response =  api.callPostRequest(result, url, type);
        responseBody = response.getBody().asString();
        
        ViewStatusCode = api.getStatusCode(response,url);
           
		result.delete(0, result.length());
		
		JSONObject viewClientResponse = (JSONObject)parser.parse(responseBody);
		JSONArray imageDetails = (JSONArray) viewClientResponse.get("image_details");
		
		if(imageDetails != null) 
		{
			for(int i=0; i<3;i++) //imageDetails.size()
			{
				JSONObject imageObject = (JSONObject) imageDetails.get(i);
				Object img_id = imageObject.get("img_id");
				imageId = img_id.toString();
//				System.out.println(img_id);
				clientImageCheck(imageId);
				

			}
			clientOverAllStatus();
		}
	}

	
	
	public void clientImageCheck(String imageId) 
	{
		url ="https://app.shelfwatch.karna.ai/" + imageId; 
		CharSequence seq = ".jpg"; 
		if(url.contains(seq) || url.contains(".JPG")) 
		{
			response = httpRequest.request(Method.GET,url);		
			clientImageStatusCode = api.getStatusCode(response,url);
		}
		else 
		{
			System.out.println("image not present");
		}
//		finalResult.append("The result of Project: " + project_Name + " role: "+role );
		
	}
	
	public void clientOverAllStatus() 
	{

		if(ViewStatusCode == 200 && clientImageStatusCode==200)
		{
			resultCheck ="SUCCESS";
		}
		
		else
		{
			resultCheck ="FAILURE";
		}
		
		excelClient.writeExcel(project_Name, roleClient, resultCheck,"",ViewStatusCode,clientImageStatusCode);

	}
	
}
