package POM_Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contacts_POMpage {

	// Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createCont;

	// Initialization
	public Contacts_POMpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public void getCreateNewContact() {
		createCont.click();
	}

	public void deleteCont(WebDriver driver, String contName) {
		String xpath = "//a[text()='" + contName + "']/ancestor::tr[@bgcolor='white']//a[text()='del']";
		WebElement deleteLink = driver.findElement(By.xpath(xpath));
		deleteLink.click();
	}

}
