package TestShelfWatch.TestShelfWatch;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

public class ShelfWatch extends ExcelTest
{
	public APIShelfWatch sWatch;
	public String resultCheck;
	public APIClients client;
	 HashMap<String, String> rowsValues = new HashMap<String, String>();
	
	public ShelfWatch()
	{
		sWatch = new APIShelfWatch();

		client = new APIClients();
	}
	
	

	@Test
	public void projectsCheck() throws ParseException, IOException 
	{	
		for(int i=0; i<rowsData.size();i++) 
		{
	    	 for(int a=0; a<rowsData.get(0).size();a++) 
	    	 {
	    		 rowsValues.put(header.get(a),rowsData.get(i).get(a));
//	    		 System.out.println(header.get(a) +rowsData.get(i).get(a));
	    	 }
	    	 if(rowsValues.get("role").equals("karna_qc")) 
	    	 {
	    		 if(rowsValues.get("username").equals("qc@shelfwatch.com"))
	    		 {	 				
	    			 sWatch.loginQC();
	    			 sWatch.getAPI(rowsValues.get("project_name"));
	    			 sWatch.viewAPI();
	    			 sWatch.analyticsAPI();
	    		 }		 
	    	 }
	    	 
	    	 else if(rowsValues.get("role").equals("client"))
	    	 {
	    		 client.loginSalesClient();
	    		 client.getClientAPI(rowsValues.get("project_name"));
	    		 client.viewClient();
	    	 }
		}
			
	}
	
	
}
