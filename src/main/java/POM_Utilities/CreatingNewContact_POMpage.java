package POM_Utilities;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Utilities.JavaUtility;
import Generic_Utilities.WebDriverUtility;

public class CreatingNewContact_POMpage {

	// Declaration

	@FindBy(name = "lastname")
	private WebElement lastname;

	@FindBy(name = "button")
	private WebElement savebtn;

	@FindBy(name = "support_start_date")
	private WebElement startDateTF;

	@FindBy(name = "support_end_date")
	private WebElement endDateTF;

	@FindBy(xpath = "//tbody/tr[5]/td[2]/img[1]")
	private WebElement org;

	// Initiallization

	public CreatingNewContact_POMpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utillization

	public void enterLastName(String str) {
		lastname.sendKeys(str);
	}

	public void selectOrg(WebDriver driver, String str) {
		WebDriverUtility web = new WebDriverUtility();
		String mainWindow = web.fetchParentWindowID(driver);
		org.click();
		Set<String> allWindows = web.fetchMultipleWindowIDs(driver);

		// Switch to the new window
		for (String window : allWindows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(str);

		driver.findElement(By.xpath("//input[@name='search']")).click();

		WebElement org_drop = driver.findElement(By.xpath("//a[text()='" + str + "']"));
		org_drop.click();

		driver.switchTo().window(mainWindow);

	}

	public void setStartingDate() {
		startDateTF.clear();
		JavaUtility a = new JavaUtility();
		String start_date = a.getSystemCurrentDate();
		startDateTF.sendKeys(start_date);
	}

	public void setEndingDate(int n) {
		endDateTF.clear();
		JavaUtility a = new JavaUtility();
		String end_date = a.getDateAfterSpecifiedDays(n);
		endDateTF.sendKeys(end_date);
	}

	public void saveCont() {
		savebtn.click();
	}

}
