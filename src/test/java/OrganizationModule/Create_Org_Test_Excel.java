package OrganizationModule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.text.DecimalFormat;
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
import org.testng.annotations.Test;

public class Create_Org_Test_Excel {

	@Test
	public void createNewOrgTest_Excel() throws InterruptedException, IOException {

		// *****************************************************************************************************************************//

		// Fetch the Common Data From the Properties File
		FileInputStream fis_prop = new FileInputStream("./src/test/resources/VTiger_Commondata.properties");

		// Creating a Java Object Object File
		Properties p = new Properties();

		// Loading all the data From fis file to p java object
		p.load(fis_prop);

		// close the Fis file
		fis_prop.close();

		// *****************************************************************************************************************************//

		// Convert the physical file into java object
		FileInputStream fis_exl = new FileInputStream("./src/test/resources/VTiger.xlsx");

		// Fetch the sheet
		Workbook wb = WorkbookFactory.create(fis_exl);

		// Fetch the Sheet
		Sheet sh = wb.getSheet("Org_Data");

		// Fetch the Row
		Row r = sh.getRow(4);

		// Fetch the Cell
		Cell c2 = r.getCell(2);

		// Fetch the Cell
		Cell c3 = r.getCell(3);

		// Fetch the data
		String orgName = c2.toString();

		// Fetch the data
		double phno1 = c3.getNumericCellValue();

		// No decimal point
		DecimalFormat df = new DecimalFormat("0");
		String phno = df.format(phno1);

		// *****************************************************************************************************************************//
		// Open The Browser
		WebDriver driver = new ChromeDriver();

		// Maximize the Browser
		driver.manage().window().maximize();

		// getting the timeouts in string variable
		String timeouts = p.getProperty("timeouts");

		// converting the string variable into integer
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
		driver.findElement(By.linkText("Organizations")).click();

		// Identify create plus icon n click
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		// click on org name textfield enter data
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		// Click on Phone Number textfield to enter the data
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phno);
		;

		// identify save button and click
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		// identify header in org info page and validate
		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));

		// identify phno
		WebElement verifyphno = driver.findElement(By.id("mouseArea_Phone"));

		if (header.getText().contains(orgName) && verifyphno.getText().contains(phno)) {
			System.out.println("create Org with phno Verified OrgName and phno test pass");

			// Fetch the Cell
			Cell c4 = r.createCell(4);
			c4.setCellValue("Passed");

			FileOutputStream fos = new FileOutputStream("./src/test/resources/VTiger.xlsx");

			wb.write(fos);

		} else {

			System.out.println("create Org with phno Verified OrgName and phno test pass");

			// Fetch the Cell
			Cell c4 = r.createCell(4);
			c4.setCellValue("Failed");

			FileOutputStream fos = new FileOutputStream("./src/test/resources/VTiger.xlsx");

			wb.write(fos);

		}

		// Identify organization link and click
		driver.findElement(By.linkText("Organizations")).click();

		// identify and click on del link
		driver.findElement(By.xpath("//a[text()='Qsp']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

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
