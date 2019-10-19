package TestShelfWatch.TestShelfWatch;

import org.testng.annotations.Test;
import Resources.APIUtils;
import Resources.ExcelUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIShelfWatch 
{

	public String type;
	public String url;
	public RequestSpecification httpRequest;
	public StringBuilder result;
	public APIUtils api;
	public Object projectVersion;
	public String project_id;
	public Response response;
	public int statusCode;
	public String responseBody;
	public JSONParser parser;
	public String resultCheck;
	public  String dashboard_type_id;
	public int imageStatusCode;
	public int reRunStatusCode;
	public String version_id;
	public String user_id;
	public String role;
	public String projectName;
	public ExcelUtils excel;
	
	
	
	public APIShelfWatch() 
	{
		api = new APIUtils();
		result = new StringBuilder();
		type = "application/x-www-form-urlencoded";
		httpRequest = RestAssured.given();
		parser =new JSONParser();
//		sWatch=new ShelfWatch();
		excel = new ExcelUtils();
		excel.writeHeading();
	}
	
	
	public void loginQC() throws UnsupportedEncodingException, ParseException 
	{		
			url = "https://app.shelfwatch.karna.ai/api/login";
	
		    result.append(URLEncoder.encode("username", "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode("qc@shelfwatch.com", "UTF-8"));
	        result.append("&");
	        result.append(URLEncoder.encode("password", "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode("123456", "UTF-8"));
		
	        
	        httpRequest.header("Content-Type", "application/json");		 
	        
	        response =  api.callPostRequest(result, url, type);
	        responseBody = response.getBody().asString();
	        
	        statusCode = api.getStatusCode(response,url);
	           
			result.delete(0, result.length());
			
			JSONObject LoginResponse = (JSONObject)parser.parse(responseBody);
			Object userId = LoginResponse.get("user_id");
			user_id = userId.toString();
			
		
			Object roleValue = LoginResponse.get("role");
			role = roleValue.toString();

	}
	
	public void loginDemoQC() throws UnsupportedEncodingException 
	{
		url = "https://app.shelfwatch.karna.ai/api/login";
		
	    result.append(URLEncoder.encode("username", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode("demo_qc@shelfwatch.com", "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("password", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode("123456", "UTF-8"));
	
        
        httpRequest.header("Content-Type", "application/json");		 
        
        response =  api.callPostRequest(result, url, type);
        responseBody = response.getBody().asString();
        
        statusCode = api.getStatusCode(response,url);
           
		result.delete(0, result.length());

	}
	


	
	
//	public void loginVisicoolers() throws UnsupportedEncodingException 
//	{
//		url = "https://app.shelfwatch.karna.ai/api/login";
//		
//	    result.append(URLEncoder.encode("username", "UTF-8"));
//        result.append("=");
//        result.append(URLEncoder.encode("visicoolers2@karna.ai", "UTF-8"));
//        result.append("&");
//        result.append(URLEncoder.encode("password", "UTF-8"));
//        result.append("=");
//        result.append(URLEncoder.encode("pdkarna@010", "UTF-8"));
//	
//        
//        httpRequest.header("Content-Type", "application/json");		 
//        
//        response =  api.callPostRequest(result, url, type);
//        responseBody = response.getBody().asString();
//        
//        statusCode = api.getStatusCode(response,url);
//           
//		result.delete(0, result.length());
//	}
	
	
//	public void getAPI(String user_id) throws UnsupportedEncodingException, ParseException 
//	{		 
//		url = "https://app.shelfwatch.karna.ai/api/qc/projects/get";
//		
//		result.append(URLEncoder.encode("user_id", "UTF-8"));
//        result.append("=");
//        result.append(URLEncoder.encode(user_id, "UTF-8"));
//        result.append("&");
//        result.append(URLEncoder.encode("user_role", "UTF-8"));
//        result.append("=");
//        result.append(URLEncoder.encode("karna_qc", "UTF-8"));
//        
//
//        httpRequest.header("Content-Type", "application/json");
//
//
//        response =  api.callPostRequest(result, url, type);
//        
//		responseBody = response.getBody().asString();
//		api.getStatusCode(response,url);
//
//		JSONObject APIResponse = (JSONObject)parser.parse(responseBody);		
//		
//		JSONArray message =  (JSONArray) APIResponse.get("message");
//
//		if (message != null)
//		{ 
//		   for (int i=0;i<message.size();i++)
//		   {
//				JSONObject  messageData = (JSONObject) message.get(i);
//	
//				projectId = messageData.get("project_id");
//				projectVersion = messageData.get("project_versions");
//				
//				if (projectVersion != null) { 
//					   for (int k=0;k<((ArrayList) projectVersion).size();k++)
//					   {
//						    JSONObject  version = (JSONObject) ((ArrayList) projectVersion).get(k);
//							Object VersionId = version.get("version_id");
//						}
//				}
//		   }
//		}
//		
//		result.delete(0, result.length());
//		
//		} 
	
	
	public void getAPI(String project_name) throws UnsupportedEncodingException, ParseException 
	{		
		projectName = project_name;
//		System.out.println(project_name);
		url = "https://app.shelfwatch.karna.ai/api/qc/projects/get";
		
		result.append(URLEncoder.encode("user_id", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(user_id, "UTF-8"));
        result.append("&");
        result.append(URLEncoder.encode("user_role", "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(role, "UTF-8"));
        

        httpRequest.header("Content-Type", "application/json");


        response =  api.callPostRequest(result, url, type);
        
		responseBody = response.getBody().asString();
		api.getStatusCode(response,url);

		JSONObject APIResponse = (JSONObject)parser.parse(responseBody);		
		
		JSONArray message =  (JSONArray) APIResponse.get("message");
//		System.out.println("message"+message);
		if (message != null)
		{ 
		   for (int i=0;i<message.size();i++)
		   {
				JSONObject  messageData = (JSONObject) message.get(i);
//				System.out.println("messageData"+messageData);
				Object project_Name = messageData.get("project_name");
				
				if(project_Name.equals(project_name))
				{
					Object projectId = messageData.get("project_id");
					project_id = projectId.toString();
					projectVersion = messageData.get("project_versions");
//					System.out.println("projectId"+projectId);
					JSONObject version = (JSONObject) ((ArrayList) projectVersion).get(((ArrayList) projectVersion).size()-1);					 	
//						System.out.println(version);
					Object VersionId = version.get("version_id");
					version_id = VersionId.toString();
//					System.out.println("VersionId:"+ VersionId);					
				}				
		   }
		}
		
		result.delete(0, result.length());
		
		} 
	
		
	
		public void viewAPI() throws UnsupportedEncodingException, ParseException 
		{		
				url ="https://app.shelfwatch.karna.ai/api/qc/projects/view";
				
				result.append(URLEncoder.encode("version_id", "UTF-8"));
		        result.append("=");
		        result.append(URLEncoder.encode(version_id, "UTF-8"));
		        result.append("&");
		        result.append(URLEncoder.encode("project_id", "UTF-8"));
		        result.append("=");
		        result.append(URLEncoder.encode(project_id, "UTF-8"));
		        result.append("&");
		        result.append(URLEncoder.encode("user_id", "UTF-8"));
		        result.append("=");
		        result.append(URLEncoder.encode(user_id, "UTF-8"));
		        
		
		        httpRequest.header("Content-Type", "application/json");
		        response =  api.callPostRequest(result, url, type);		        
				responseBody = response.getBody().asString();
				api.getStatusCode(response,url);
				
				result.delete(0, result.length());
//				System.out.println("View API Reached");
				JSONObject viewResponse = (JSONObject)parser.parse(responseBody);
				JSONObject message =  (JSONObject) viewResponse.get("message");
				JSONArray dashboardIdArray = (JSONArray) message.get("dashboard_ids");
				
				for (int i=0;i<dashboardIdArray.size();i++)
				   {
						JSONObject get_dashboard_id = (JSONObject) dashboardIdArray.get(i);
//						System.out.println("get_dashboard_id"+get_dashboard_id);
					    Object dashboardTypeId = get_dashboard_id.get("dashboard_type_id");
					    dashboard_type_id = dashboardTypeId.toString();
//						System.out.println("dashboard_type_id"+dashboard_type_id);
						
				   }
				
		}
	
	
	
	public void analyticsAPI() throws UnsupportedEncodingException, ParseException
	{			
			url ="https://app.shelfwatch.karna.ai/api/qc/view/analytics";
			
			result.append(URLEncoder.encode("version_id", "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(version_id, "UTF-8"));
	        result.append("&");
	        result.append(URLEncoder.encode("project_id", "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(project_id, "UTF-8"));
	        result.append("&");
	        result.append(URLEncoder.encode("user_id", "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(user_id , "UTF-8"));
	        result.append("&");
	        result.append(URLEncoder.encode("dashboard_type_id", "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(dashboard_type_id, "UTF-8"));
	        
	        httpRequest.header("Content-Type", "application/json");

	        response =  api.callPostRequest(result, url, type);
	        
			responseBody = response.getBody().asString();
			statusCode = api.getStatusCode(response,url);		
			
//			reRunAnalytics(statusCode);
			
			if(dashboard_type_id.equals("16")) {
			clickImage(statusCode,project_id,version_id,"group_wise","count_data");
			clickImage(statusCode,project_id,version_id,"class_wise","count_data");
			clickImage(statusCode,project_id,version_id,"group_wise","presence_data");
			clickImage(statusCode,project_id,version_id,"class_wise","presence_data");
			}
			
			else if(dashboard_type_id.equals("1")|| dashboard_type_id.equals("3")) 
			{
				imageAnalyticsOneThree(statusCode,project_id,version_id,"count_data");
				imageAnalyticsOneThree(statusCode,project_id,version_id,"presence_data");
			}
			
			displayOverAllResult(statusCode,reRunStatusCode,imageStatusCode);
			result.delete(0, result.length());
			
		}
	
	
	public void reRunAnalytics(int statusCode)
	{		
		if(statusCode == 200) {
			
			url = "https://app.shelfwatch.karna.ai/api/qc/rerun/analytics";
			
			httpRequest.header("Content-Type", "application/json");

			Response reRunResponse = api.callPostRequest(result, url, type);
	        
//			String reRunResponseBody = reRunResponse.getBody().asString();
			reRunStatusCode = api.getStatusCode(reRunResponse,url);
			
			if(reRunStatusCode != 200) {
				System.out.println("Error exist on rerun and Status Code is " + reRunStatusCode);
			}
			
		}
		
		else {
			System.out.println("Error exist and status Code is " + statusCode);
		}
		
	}
	
	public void clickImage(int statusCode,String project_id,String version_id,String value_wise, String data_value) throws ParseException 
	{
		if(statusCode==200)
		{
			JSONObject analyticsResponse = (JSONObject)parser.parse(responseBody);
			
			JSONObject shopLevelStats = (JSONObject) analyticsResponse.get("shop_level_stats");

			JSONObject groupWise = (JSONObject) shopLevelStats.get(value_wise);
			
			JSONArray countData = (JSONArray) groupWise.get(data_value);

			String countDataArray = countData.get(0).toString();
			String[] imageName = countDataArray.split(",");

			int i=0;
			for ( i = 0; i<imageName.length;i++)
			{ 
				String image = imageName[i]; 
				image = image.replaceAll("]", "");
				
	            if (image.equals("\"Image 1\""))
	            { 
	                for(int j=1; j<4;j++)
					{
						countDataArray = countData.get(j).toString();
						String[] imageData = countDataArray.split(",");
				
						String imageValue = imageData[i];
						imageValue = imageValue.replaceAll("\"]", "");
						imageValue = imageValue.replaceAll("\"", "");
						
						url = "https://storage.googleapis.com/shelfwatch-prod-data/dist/test_images_modified/" + project_id + "_"+version_id+"/"+imageValue;

						imagesCheck(url);
						}
	             } 
	         } 
		}
	}
	

	
	
//	 finalResult(statusCode, reRunStatusCode, imageStatusCode);
	
	
	public void imageAnalyticsOneThree(int statusCode, String project_id, String version_id,String data_value) throws ParseException 
	{
		if(statusCode==200)
		{
			JSONObject analyticsResponse = (JSONObject)parser.parse(responseBody);
			
			JSONObject shopLevelStats = (JSONObject) analyticsResponse.get("shop_level_stats");
			
			JSONArray countData = (JSONArray) shopLevelStats.get(data_value);

			String countDataArray = countData.get(0).toString();
			String[] imageName = countDataArray.split(",");

			int i=0;
			for ( i = 0;i <imageName.length;i++)
			{ 
				String image = imageName[i]; 
				image = image.replaceAll("]", "");
				
	            if (image.equals("\"Image 1\""))
	            { 
	                for(int j=1; j<4;j++)
					{
						countDataArray = countData.get(j).toString();
						String[] imageData = countDataArray.split(",");
				
						String imageValue = imageData[i];
						imageValue = imageValue.replaceAll("\"]", "");
						imageValue = imageValue.replaceAll("\"", "");
						
						url = "https://storage.googleapis.com/shelfwatch-prod-data/dist/test_images_modified/" + project_id + "_"+version_id+"/"+imageValue;

						imagesCheck(url);
					}
	             } 
	         } 
		}
	}
	
	public void imagesCheck(String url)
	{
		CharSequence seq = ".jpg"; 
		if(url.contains(seq)) 
		{
			response = httpRequest.request(Method.GET,url);		
			imageStatusCode = api.getStatusCode(response,url);
		}
		else 
		{
			System.out.println("image not present");
		}
		
		
	}
	
	
	
	
	public void displayOverAllResult(int statusCode, int reRunStatusCode, int imageStatusCode) 
	{
		
		if(statusCode == 200 && imageStatusCode==200)// && reRunStatusCode==200
		{
			resultCheck ="SUCCESS";
		}
		
		else
		{
			resultCheck ="FAILURE";
		}
		excel.writeExcel(projectName, role, resultCheck,"",statusCode,reRunStatusCode,imageStatusCode);
	}
	
	
	
	
}
