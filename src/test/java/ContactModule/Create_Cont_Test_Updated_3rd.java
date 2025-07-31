package ContactModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_Cont_Test_Updated_3rd {

	@Test
	public void createContactModule_3rd() throws IOException {


        // STEP 1: Load common configuration data from properties file (like URL, username, password)
        FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_Commondata.properties");
        Properties prop = new Properties();
        prop.load(fis);
        fis.close(); // Close the file to avoid memory leaks

        // STEP 2: Load Excel workbook that contains contact and organization data
        FileInputStream fis_excel = new FileInputStream("./src/test/resources/VTiger.xlsx");
        Workbook workbook = WorkbookFactory.create(fis_excel); // Read Excel file as a Workbook

        // STEP 3: Fetch contact-related data from 'Contact_Data' sheet
        Sheet contactSheet = workbook.getSheet("Contact_Data");
        Row contactRow = contactSheet.getRow(1); // Read 2nd row (index starts from 0)
        String firstName = contactRow.getCell(2).toString(); // First name
        String lastName = contactRow.getCell(3).toString();  // Last name
        String salutation = contactRow.getCell(4).toString(); // Mr/Ms/Mrs

        // STEP 4: Fetch organization-related data from 'Org_Data' sheet
        Sheet orgSheet = workbook.getSheet("Org_Data");
        Row orgRow = orgSheet.getRow(4); // Read 5th row
        String orgName = orgRow.getCell(2).toString(); // Organization name
        double phoneRaw = orgRow.getCell(3).getNumericCellValue(); // Phone number in number format
        String phone = new DecimalFormat("0").format(phoneRaw); // Convert to string without decimal

        workbook.close(); // Always close workbook to release memory

        // STEP 5: Launch the browser
        WebDriver driver = new ChromeDriver(); // Using Chrome browser
        driver.manage().window().maximize(); // Maximize the window

        // Set implicit wait for element loading
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("timeouts"))));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait

        // STEP 6: Navigate to the VTiger application URL
        driver.get(prop.getProperty("url"));

        // STEP 7: Login to the application using credentials from property file
        driver.findElement(By.name("user_name")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.name("user_password")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.id("submitButton")).click();

        // STEP 8: Navigate to "Organizations" page and create a new organization
        driver.findElement(By.linkText("Organizations")).click();
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        driver.findElement(By.name("accountname")).sendKeys(orgName); // Enter organization name
        driver.findElement(By.id("phone")).sendKeys(phone); // Enter phone number
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); // Click on Save button

        // STEP 9: Validate organization creation (check org name and phone number on details page)
        WebElement orgHeader = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
        WebElement phoneDisplay = driver.findElement(By.id("mouseArea_Phone"));
        Assert.assertTrue(orgHeader.getText().contains(orgName), "Org name verification failed");
        Assert.assertTrue(phoneDisplay.getText().contains(phone), "Org phone verification failed");

        // STEP 10: Navigate to "Contacts" module and create a new contact
        driver.findElement(By.linkText("Contacts")).click();
        driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

        // STEP 11: Fill contact form with salutation, first name and last name
        Select salutationDropdown = new Select(driver.findElement(By.name("salutationtype")));
        salutationDropdown.selectByValue(salutation); // Select Mr/Mrs/Ms from dropdown
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);

        // STEP 12: Click on organization lookup icon to associate contact with org (opens new window)
        String mainWindow = driver.getWindowHandle(); // Save main window handle
        driver.findElement(By.xpath("//tbody/tr[5]/td[2]/img")).click(); // Click on lookup icon

        // STEP 13: Switch to the new popup window
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window); // Switch to child window
                break;
            }
        }

        // STEP 14: Click on the organization name (dynamically selected from Excel)
        WebElement orgSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + orgName + "']")));
        orgSelect.click();

        // STEP 15: Switch back to the main window
        driver.switchTo().window(mainWindow);

        // STEP 16: Save the contact
        driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

        // STEP 17: Validate contact creation (first name and last name match expected)
        WebElement firstNameDisplay = driver.findElement(By.id("dtlview_First Name"));
        WebElement lastNameDisplay = driver.findElement(By.id("dtlview_Last Name"));
        Assert.assertTrue(firstNameDisplay.getText().contains(firstName), "Contact first name verification failed");
        Assert.assertTrue(lastNameDisplay.getText().contains(lastName), "Contact last name verification failed");

        // STEP 18: Sign out of the application
        WebElement userIcon = driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"));
        new Actions(driver).moveToElement(userIcon).perform(); // Hover on user icon
        driver.findElement(By.xpath("//a[text()='Sign Out']")).click(); // Click on Sign Out

        // STEP 19: Close the browser
        driver.quit();
	}

}
