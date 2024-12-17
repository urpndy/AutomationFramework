package vtiger.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateOrgWithIndustryUsingGU {

	public static void main(String[] args) throws Throwable {
		
		//Create objects of all utility classes
		JavaUtility jUtil=new JavaUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil= new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		WebDriver driver = null;
		
		//Step 1 : Read all the data
		
		/* Read data from property file */
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String  USERNAME = pUtil.readDataFromPropertyFile("username");
		String  PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/* Read data from Excel file */
		String ORGNAME = eUtil.readDataFromExcelFile("Organizations", 4, 2)+jUtil.getRandomNumber();
		String INDUSTRYTYPE = eUtil.readDataFromExcelFile("Organizations", 4, 3);
		
		//Step 2 : Launch the browser		//Runtime Polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
		
		wUtil.maximiseWindow(driver);
		wUtil.waitForpageLoad(driver);
		driver.get(URL);
		
		//Step 3 : Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4 : Navigate to organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5 : Click on Create Organization look Up Image 
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 6 : Create Organization with Mandatory fields 
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 7 : Select "Chemicals" in the industry drop down 
		WebElement dropdown = driver.findElement(By.name("industry"));
		wUtil.handleDropdown(dropdown, INDUSTRYTYPE);
		
		//Step 8 : Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 9 : Validate
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println("PASS");
			System.out.println(orgHeader);
		}
		else {
			System.out.println("FAIL");
		}
		
		//Step 10 : logout of Application
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(driver, mouseHover);
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 11 : Close the browser
		driver.quit();
		
	}

}
