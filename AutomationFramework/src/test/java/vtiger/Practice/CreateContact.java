package vtiger.Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact {

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
		
		//Step 3 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4 : Click on Create contact look Up Image 
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 5 : Create Contact with Mandatory fields 
		driver.findElement(By.name("lastname")).sendKeys("Reddy");
		
		//Step 6 : Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7 : Validate
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains("Reddy"))
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
