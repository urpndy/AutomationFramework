package vtiger.Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class CreateContactWithOrgUsingGU {

	public static void main(String[] args) throws Throwable {

		//Create Object of all utility classes
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		
		WebDriver driver=null;
		
		//Read all the data
		
		/* Read data from property file */
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		/* Read data from excel file */
		String ORGNAME = eUtil.readDataFromExcelFile("Contacts", 4, 2)+jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts", 4, 3);
		
		// Step 1 : Launch the browser
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

		// Step 2 : Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4 : Click on Create contact look Up Image 
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5 : Create Contact with Mandatory fields 
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step 6 : CLick on organizations lookup image
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		
		//Step 7 : Switch the control to child window
		String mainWindowID = driver.getWindowHandle();
		
		Set<String> allWindowIds = driver.getWindowHandles();
		
		for(String ID:allWindowIds)
		{
			if(!ID.equals(mainWindowID))
			{
				driver.switchTo().window(ID);
				System.out.println("Window Switched to Child");
			}
		}
		
		//Step 8 : Search for organization
		driver.findElement(By.name("search_text")).sendKeys("Pizza Hut");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("Pizza Hut")).click();
		
		//Step 9 : Switch back to main window
		driver.switchTo().window(mainWindowID);
		
		//Step 10 : Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 11 : Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains("Reddy"))
		{
			System.out.println("PASS");
			System.out.println(contactHeader);
		}
		else {
			System.out.println("FAIL");
		}

		
		
		//Step 12 : logout of Application
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(mouseHover).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout Success");
				
		//Step 13 : Close the browser
		driver.quit();
	}
	
}
