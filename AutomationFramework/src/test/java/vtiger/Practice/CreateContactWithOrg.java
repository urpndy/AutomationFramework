package vtiger.Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrg {

	public static void main(String[] args) throws InterruptedException {

		// Step 1 : Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");

		// Step 2 : Login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4 : Click on Create contact look Up Image 
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5 : Create Contact with Mandatory fields 
		driver.findElement(By.name("lastname")).sendKeys("Reddy");
		
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
