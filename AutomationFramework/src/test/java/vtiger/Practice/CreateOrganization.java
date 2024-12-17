package vtiger.Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization {

	public static void main(String[] args) throws Throwable {
		
		//Step 1 : Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		//Step 2 : Login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3 : Navigate to organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4 : Click on Create Organization look Up Image 
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 5 : Create Organization with Mandatory fields 
		driver.findElement(By.name("accountname")).sendKeys("Pizza Hub");
		
		//Step 6 : Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7 : Validate
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains("Pizza Hub"))
		{
			System.out.println("PASS");
			System.out.println(orgHeader);
		}
		else {
			System.out.println("FAIL");
		}
		
		//Step 8 : logout of Application
		WebElement mouseHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(mouseHover).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 9 : Close the browser
		driver.quit();

	}

}
