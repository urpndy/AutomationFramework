package vtiger.Practice;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustryUsingDataDrivenTesting {

	public static void main(String[] args) throws Throwable {
		
		Random r= new Random();
		int random=r.nextInt(1000);
		
		WebDriver driver = null;
		
		//Step 1 : Read all the data
		
		/* Read data from property file */
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties"); 
		Properties p=new Properties();
		p.load(fisp);
		String BROWSER=p.getProperty("browser");
		String URL=p.getProperty("url");
		String USERNAME=p.getProperty("username");
		String PASSWORD=p.getProperty("password");
		
		/* Read data from Excel file */
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fise);
		Sheet sh = wb.getSheet("Organizations");
		String ORGNAME = sh.getRow(4).getCell(2).getStringCellValue()+random;
		String INDUSTRY = sh.getRow(4).getCell(3).getStringCellValue();
		
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		
		Select sel=new Select(dropdown);
		sel.selectByValue(INDUSTRY);
		
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
		Actions act=new Actions(driver);
		act.moveToElement(mouseHover).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 11 : Close the browser
		driver.quit();
		
	}
	
}
