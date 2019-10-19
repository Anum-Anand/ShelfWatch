package TestShelfWatch.TestShelfWatch;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

public class ExcelTest
{
	 public Workbook workbook = null;
     public Sheet sheet = null;
     public Row row;
     public Cell cell;
     public ArrayList<ArrayList<String>> rowsData = new ArrayList<ArrayList<String>>();  
     public ArrayList<String> header = new ArrayList<String>();


     
	 @Test 
     public void readExcel() throws IOException    
     {
	     File file = new File("/home/paralleldots/Documents/testExcel.xls");
	     FileInputStream inputStream = new FileInputStream(file);
	     workbook = new HSSFWorkbook(inputStream);
	     sheet = workbook.getSheetAt(0);
	     row = sheet.getRow(0);
	     for(int k=0; k<row.getLastCellNum(); k++)
	     {
	    	 String headerValue = row.getCell(k).getStringCellValue();	    	
	    	 header.add(headerValue);
	    	 
	     }

	    String dataValue = null;
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	    	
	    	row = sheet.getRow(i);
	    	ArrayList<String> individualRowValue = new ArrayList<String>();
		    for(int k=0; k<row.getLastCellNum(); k++)
		     {		    	
		    	cell = row.getCell(k);
		    	
	       	 if(cell.getCellType() == Cell.CELL_TYPE_STRING)
	       	 {
	           	 dataValue = cell.getStringCellValue();           	  
	       	 }
	       	 
	       	 if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
	       	 {
	       		  double cellValue = cell.getNumericCellValue();
	       		  int value = (int)cellValue;
	       		  dataValue =String.valueOf(value);	
	       	 }
		    	
	       	individualRowValue.add(dataValue);	
		     }
		    rowsData.add(individualRowValue);
	    }
	    
//	    System.out.println(rowsData);
    }
     
}
