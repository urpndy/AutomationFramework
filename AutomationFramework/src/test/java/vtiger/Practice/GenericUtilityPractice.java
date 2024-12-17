package vtiger.Practice;

import vtiger.GenericUtilities.JavaUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws Throwable {
		
	/*	PropertyFileUtility pUtil=new PropertyFileUtility();
		String value = pUtil.readDataFromPropertyFile("browser");
		System.out.println(value);
		String un = pUtil.readDataFromPropertyFile("username");
		System.out.println(un);
		
		ExcelFileUtility eUtil= new ExcelFileUtility();
		String data = eUtil.readDataFromExcelFile("contacts", 1, 2);
		System.out.println(data);
		
		eUtil.writeDataIntoExcel("Trial", 3, 7, "Hello Tester");
		System.out.println("Data Added");	*/
		
		JavaUtility jUtil=new JavaUtility();
		System.out.println(jUtil.getRandomNumber());
		System.out.println(jUtil.getSystemDate());
	}

}
