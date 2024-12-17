package vtiger.Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelNewSheet {

	public static void main(String[] args) throws Throwable {
		
		//Step 1 : Open the document in Java Readable Format
		FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 2 : Create a Workbook
		Workbook wb=WorkbookFactory.create(fis);
		
		//Step 3 : Create a new Sheet
		//Sheet sh = wb.getSheet("Contacts");
		Sheet sh = wb.createSheet("TestSheetcreate"); //Instead of getting a sheet we are creating a sheet
		
		//Step 4 : Navigate to Row
		Row rw = sh.createRow(4);
				
		//Step 5 : Create a cell
		Cell cl = rw.createCell(5);
		
		//Step 6 : Provide data to be written
		cl.setCellValue("SELENIUM");
		
		//Step 7 : Open document in java write format
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 8 : Write the data
		wb.write(fos);
		System.out.println("Data Added Successfully");
		
		//Step 9 : Close the workbook
		wb.close();
	}

}
