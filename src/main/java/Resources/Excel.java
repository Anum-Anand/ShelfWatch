package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

public class Excel {
	public int i = 1;
    public int j;
    public FileInputStream inputStream;
    public Workbook workbook = null;
    public Sheet sheet = null;
    public File file ;
    public FileOutputStream outputStream ;
    
    public Excel() throws IOException 
    {
       file =    new File("src/main/java/Resources/testData.xls");
   		
   	   String path = file.getAbsolutePath();
		
   	   inputStream = new FileInputStream(path);
       
       workbook = new HSSFWorkbook(inputStream);
       workbook.setSheetName(0, "Overall Result");
       sheet = workbook.getSheetAt(0);
       outputStream = new FileOutputStream(file);
    }
    
    @Test(priority=1)
	 public void writeHeading() throws IOException, InterruptedException
		{
		 
		       int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
//		       System.out.println("rowCountvalue heading" +rowCount);
		       for(i = 0; i<= rowCount; i++)
		       {
		    	   sheet.removeRow(sheet.getRow(i));
		       }
		
		       Row row0 = sheet.createRow(0);
//			    System.out.println("header row : " + row0);

			   
			    Cell cellA1 = row0.createCell(0);
				cellA1.setCellValue("Project Name");
			  
			    Cell cellB1 = row0.createCell(1);
				cellB1.setCellValue("Role");
		     
		       Cell cellC1 = row0.createCell(2);
		       cellC1.setCellValue("Status");
		 		
		
		}
	@Test(priority=2)
    public void run() throws Exception {
//    	resultEntry("name", "client", "pass");
		projectName("pop-Agra");
		role("qc");
		status("pass");
//		projectName("pop");
//		role("qcsdf");
//		status("fail");
    }
    
    
//	public void resultEntry(String project_name, String role, String status) throws IOException, InterruptedException  
//	{		   
//		
//		   int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
//	       rowCount = rowCount+1;
//	       
//	       Row row = sheet.createRow(rowCount);
//	              	       
//	       for(j=0; j<=2 ;j++) {
//		    	   
//		       Cell cellC2 = row.createCell(j);
//		       
//		       switch(j)
//		       {    
//				    case 0:   
//				    	cellC2.setCellValue(project_name);		
//				    	System.out.println(project_name);
//				     break;
//			       
//			       case 1:
//			    	   cellC2.setCellValue(role);
//			    	   System.out.println("check2");	
//			  	     break;
//			       
//				   	case 2:
//					   cellC2.setCellValue(status);		
//					   System.out.println(status);
//				     break;     
//		    	}
//	       }		   
//		   	    
//	       System.out.println("reached");
//	       inputStream.close();
//	       workbook.write(outputStream);
//	      
//	       outputStream.close();
//		       
//	}
	
	
	public void projectName(String project_name) throws Exception  {
		
	
	   int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	   int rowCountvalue = rowCount+1;
	   System.out.println("rowCountvalue expected" +rowCountvalue);
	
	    
	   Row row = sheet.createRow(rowCountvalue);
	    System.out.println("expected row : " + row);

	   Cell cellA2 = row.createCell(0);
	   cellA2.setCellValue(project_name);

	   	       
	}
	
	
	public void role(String role) throws Exception  {
		
		
		   int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		   int rowCountvalue = rowCount+1;
		   System.out.println("rowCountvalue expected" +rowCountvalue);
		
		    
		   Row row = sheet.createRow(rowCountvalue);
		    System.out.println("expected row : " + row);

		   Cell cellA2 = row.createCell(1);
		   cellA2.setCellValue(role);
		   	       
		}
	
	public void status(String status) throws Exception  {
		
		
		   int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		   int rowCountvalue = rowCount+1;
		   System.out.println("rowCountvalue expected" +rowCountvalue);
		
		    
		   Row row = sheet.createRow(rowCountvalue);
		    System.out.println("expected row : " + row);

		   Cell cellA2 = row.createCell(2);
		   cellA2.setCellValue(status);
		   	       
		}
}
