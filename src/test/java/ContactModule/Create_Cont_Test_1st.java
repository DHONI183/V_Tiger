package ContactModule;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import Generic_Utilities.*;

public class Create_Cont_Test_1st {

	@Test
	public void createContactModule_1st() throws IOException, InterruptedException {

		// *****************************************************************************************************************************//
		/*
		 * // Fetch the Common Data From the Properties File FileInputStream fis = new
		 * FileInputStream("./src/test/resources/VTiger_Commondata.properties"); //
		 * Creating a Java Object Object File Properties p = new Properties(); //
		 * Loading all the data From fis file to p java object p.load(fis); fis.close();
		 */
		// *****************************************************************************************************************************//

		PropertyFileUtility a = new PropertyFileUtility();

		// *****************************************************************************************************************************//
		/*
		 * // Convert the physical file into java object FileInputStream fis_exl = new
		 * FileInputStream("./src/test/resources/VTiger.xlsx"); // Fetch the sheet
		 * Workbook wb = WorkbookFactory.create(fis_exl); // Fetch the Sheet Sheet sh =
		 * wb.getSheet("Contact_Data"); // Fetch the Row Row r = sh.getRow(1); // Fetch
		 * the Cell Cell c2 = r.getCell(2); Cell c3 = r.getCell(3); Cell c4 =
		 * r.getCell(4); // Fetch the data String con_Name = c2.toString(); String
		 * last_Name = c3.toString(); String gender = c4.toString();
		 */
		// *****************************************************************************************************************************//

		ExcelFileUtility b = new ExcelFileUtility();

		String con_Name = b.fetchDataFromExcelFile("Contact_Data", 1, 2);
		String last_Name = b.fetchDataFromExcelFile("Contact_Data", 1, 3);
		String gender = b.fetchDataFromExcelFile("Contact_Data", 1, 4);

		// *****************************************************************************************************************************//

		// Open The Browser
		WebDriver driver = new ChromeDriver();

		// Maximize the Browser
		driver.manage().window().maximize();

		String timeouts = a.fetchDataFromPropFile("timeouts");

		int timeout = Integer.parseInt(timeouts);

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

		// Navigate to an application
		driver.get(a.fetchDataFromPropFile("url"));

		// Identify Username
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(a.fetchDataFromPropFile("username"));

		// Indentify Password
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(a.fetchDataFromPropFile("password"));

		// Click on login
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// Identify organization link and click

		driver.findElement(By.linkText("Contacts")).click();

		// Identify create plus icon n click
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// *****************************************************************************************************************************//

		// Industry dropdown
		WebElement mr = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		mr.click();
		Select dropmr = new Select(mr);
		dropmr.selectByValue(gender);

		// click on org name textfield enter data
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(con_Name);

		// Click on Phone Number textfield to enter the data
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(last_Name);

		// Click on Phone Number textfield to enter the data
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// *****************************************************************************************************************************//

		// identify header in org info page and validate
		WebElement ContactName = driver.findElement(By.xpath("//span[@id='dtlview_First Name']"));

		// identify header in org info page and validate
		WebElement lastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));

		if (ContactName.getText().contains(con_Name)) {
			System.out.println("create Contact with Contact Name test pass");

		} else {

			System.out.println("create Contact with Contact Name test fail");

		}

		if (lastName.getText().contains(last_Name)) {
			System.out.println("create Contact with Contact Name test pass");

		} else {

			System.out.println("create Contact with Contact Name test fail");

		}

		driver.findElement(By.linkText("Contacts")).click();

		// *****************************************************************************************************************************//

		// identify and click on del link
		driver.findElement(
				By.xpath("//a[text()='Marcus']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();

		// Handle delete pop up
		Alert al = driver.switchTo().alert();

		al.getText();
		System.out.println("text :" + al.getText());

		al.accept();

		Thread.sleep(3000);

		WebElement acts = driver.findElement(By.xpath("//img[@style=\"padding: 0px;padding-left:5px\"]"));

		Actions act = new Actions(driver);

		act.moveToElement(acts).perform();

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		Thread.sleep(2000);

		driver.quit();

	}

}
