package Resources;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtils {
	public  XSSFWorkbook workbook;
	public  XSSFSheet sheet;
	int rownum = 0; 
	Map<String, Object[]> data = new TreeMap<String, Object[]>(); 
	 
	 
	 public ExcelUtils() {
		    workbook = new XSSFWorkbook(); 
	        sheet = workbook.createSheet("Result Data"); 
	 }
	 
	 @Test(priority=1)
	 public void writeHeading() {
		 data.put("1", new Object[]{ "Project Name", "Role", "Status" ,"", "runAnalyticsStatus","reRunAnalyticsStatus", "imageStatus" }); 
		 Set<String> keyset = data.keySet(); 
	        
	        for (String key : keyset) { 
	            // this creates a new row in the sheet 
	            Row row = sheet.createRow(rownum++); 
	            Object[] objArr = data.get(key); 
	            int cellnum = 0; 
	            for (Object obj : objArr) { 
	                // this line creates a cell in the next column of that row 
	                Cell cell = row.createCell(cellnum++); 
	                if (obj instanceof String) 
	                    cell.setCellValue((String)obj); 
	                else if (obj instanceof Integer) 
	                    cell.setCellValue((Integer)obj); 
	            } 
	        } 
	        try { 
	         
	            FileOutputStream out = new FileOutputStream(new File("resultQCKarna.xlsx")); 
	            workbook.write(out); 
	            out.close(); 
//	            System.out.println("written successfully"); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	        data.remove("1");
	 }
	
	 @Test(priority=2)
	 public void run() {
//		 writeExcel( "project_name" ,  "role",  "pass" ,"",  "200", "500",  "200");
	 }
	
	
	public void writeExcel(String project_name , String role, String status,String empty, int runAnalyticsStatus, int reRunAnalyticsStatus, int imageStatus) {
		
	       
	       
	        data.put("5", new Object[]{ project_name, role, status, empty,runAnalyticsStatus,reRunAnalyticsStatus, imageStatus }); 
	  
	        Set<String> keyset = data.keySet(); 
	        
	        for (String key : keyset) { 
	            Row row = sheet.createRow(rownum++); 
	            Object[] objArr = data.get(key); 
	            int cellnum = 0; 
	            for (Object obj : objArr) {
	                Cell cell = row.createCell(cellnum++); 
	                if (obj instanceof String) 
	                    cell.setCellValue((String)obj); 
	                else if (obj instanceof Integer) 
	                    cell.setCellValue((Integer)obj); 
	            } 
	        } 
	        try { 
	         
	            FileOutputStream out = new FileOutputStream(new File("resultQCKarna.xlsx")); 
	            workbook.write(out); 
	            out.close(); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
		
	}
}
