package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of Generic/reusable Methods related to Excel file
 * @author AdarshPandey
 * 
 */
public class ExcelFileUtility {
	
	/**
	 * This method will read data from Excel file and return the value to caller
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return data
	 * @throws Throwable
	 */
	
	public String readDataFromExcelFile(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		
	}

	/**
	 * This method will write data into Excel sheet
	 * @param sheet
	 * @param rowNum
	 * @param cellNum
	 * @param value
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String sheet, int rowNum, int cellNum, String value) throws Throwable
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.createSheet(sheet).createRow(rowNum).createCell(cellNum).setCellValue(value);
		
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		wb.write(fos);
		wb.close();
	}
}