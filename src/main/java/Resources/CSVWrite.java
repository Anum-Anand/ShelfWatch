package Resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

public class CSVWrite {
	@Test
	public void run() {
		writeCSV("pop-Agra", "QC", "pass");
		writeCSV("pop-Ahmedabad","QC","pass"); 
	}
	
	
	public void writeCSV(String project_name,String role, String status) {
		
		 File file = new File("/home/paralleldots/eclipse-workspace/TestShelfWatch/src/main/java/Resources/testCSV.csv"); 
		    try { 
		        FileWriter outputfile = new FileWriter(file); 

		        CSVWriter writer = new CSVWriter(outputfile); 

		        String[] header = { "Project_name", "Role", "Status" }; 
		        writer.writeNext(header); 
//		        writer.
//		        String[] data1 = { "Aman", "10", "620" }; 
//		        writer.writeNext(data1); 
//		        String[] data2 = { "Suraj", "10", "630" }; 
//		        writer.writeNext(data2);
		        
//		        String [] data = { project_name, role, status};
//		        writer.writeNext(data); 
//		        writer.append("Name");
//		        writer.append(",");
//		        writer.append("Role");
//		        writer.append(",");
//		        writer.append("Topic");
//		        writer.append("\n");

		        writer.close(); 
		    } 
		    catch (IOException e) { 
		        // TODO Auto-generated catch block 
		        e.printStackTrace(); 
		    } 
		
		
	}
	
}
