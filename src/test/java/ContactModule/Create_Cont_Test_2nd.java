package ContactModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Create_Cont_Test_2nd {

	@Test
	public void createContactModule_2nd() throws IOException, InterruptedException {

		// *****************************************************************************************************************************//

		// Fetch the Common Data From the Properties File
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_Commondata.properties");

		// Creating a Java Object Object File
		Properties p = new Properties();

		// Loading all the data From fis file to p java object
		p.load(fis);

		fis.close();

		// *****************************************************************************************************************************//

		// Convert the physical file into java object
		FileInputStream fis_exl = new FileInputStream("./src/test/resources/VTiger.xlsx");

		// Fetch the sheet
		Workbook wb = WorkbookFactory.create(fis_exl);

		// Fetch the Sheet
		Sheet sh = wb.getSheet("Contact_Data");

		// Fetch the Row
		Row r = sh.getRow(1);

		// Fetch the Cell
		Cell c2 = r.getCell(2);

		Cell c3 = r.getCell(3);

		Cell c4 = r.getCell(4);

		// Fetch the data
		String con_Name = c2.toString();

		String last_Name = c3.toString();

		String gender = c4.toString();

		// *****************************************************************************************************************************//

		// Open The Browser
		WebDriver driver = new ChromeDriver();

		// Maximize the Browser
		driver.manage().window().maximize();

		String timeouts = p.getProperty("timeouts");

		int timeout = Integer.parseInt(timeouts);

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

		// Navigate to an application
		driver.get(p.getProperty("url"));

		// Identify Username
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(p.getProperty("username"));

		// Indentify Password
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(p.getProperty("password"));

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

		// *****************************************************************************************************************************//

		// 
		WebElement start_date_TF = driver.findElement(By.name("support_start_date"));
		start_date_TF.clear();

		WebElement end_date_TF = driver.findElement(By.name("support_end_date"));
		end_date_TF.clear();

		Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String start_date = sim.format(dobj);
		start_date_TF.sendKeys(start_date);
		System.out.println(start_date);

		Calendar cal = sim.getCalendar();		
		cal.add(Calendar.DAY_OF_MONTH, 30);		
		String end_date = sim.format(cal.getTime());
		System.out.println(end_date);

		// *****************************************************************************************************************************//

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

		WebElement verifystartdate = driver.findElement(By.id("dtlview_Support Start Date"));

		if (verifystartdate.getText().contains(start_date)) {
			System.out.println("create Contact and start date with Contact Name test pass");

		} else {

			System.out.println("create Contact and start date with Contact Name test fail");

		}

		WebElement verifyenddate = driver.findElement(By.id("dtlview_Support End Date"));

		if (verifyenddate.getText().contains(end_date)) {
			System.out.println("create Contact and enddate with Contact Name test pass");

		} else {

			System.out.println("create Contact and enddate with Contact Name test fail");

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
