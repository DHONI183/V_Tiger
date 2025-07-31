package OrganizationModule;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Create_Org_Test {

	@Test
	public void createNewOrgTest() throws InterruptedException {
		
		// Open The Browser
		WebDriver driver = new ChromeDriver();
		
		// Maximize the Browser
		driver.manage().window().maximize();
		
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		// Navigate to an application
		driver.get("http://localhost:8888/");
		
		// Identify Username
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		
		// Indentify Password
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("password");
		
		// Click on login
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		//Identify organization link and click
		
				driver.findElement(By.linkText("Organizations")).click();
				
				//Identify create plus icon n click
				
				driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
				
				//click on org name textfield enter data
				
				driver.findElement(By.name("accountname")).sendKeys("Tcs");
				
				//identify save button and click
				
				driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
				//identify header in org info page and validate
				
				 WebElement  header=driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
				 
				 if(header.getText().contains("Tcs"))
				 {
					 System.out.println("create Org test pass");
						
		}
				 else {
					 
					 System.out.println("create Org test fail");

		}	

				//Identify organization link and click
					
					driver.findElement(By.linkText("Organizations")).click();
					
					//identify and click  on del link 
					
					driver.findElement(By.xpath("//a[text()='Tcs']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();
					
					//Handle delete pop up
					
					Alert al=driver.switchTo().alert();
					
					al.getText();
					System.out.println("text :"+al.getText());
					
					al.accept();
					
					Thread.sleep(3000);
					
					WebElement acts  =driver.findElement(By.xpath("//img[@style=\"padding: 0px;padding-left:5px\"]"));
					
					Actions act=new Actions(driver);
					
					act.moveToElement(acts).perform();
					
					driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
					
					Thread.sleep(2000);
					
					driver.quit();		
				 
	}
	
}
