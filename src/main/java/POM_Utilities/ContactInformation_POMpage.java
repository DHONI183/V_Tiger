package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformation_POMpage {

	// Declaration

	@FindBy(id = "dtlview_Last Name")
	private WebElement lastname;

	@FindBy(xpath = "//td[@id=\"mouseArea_Organization Name\"]//a")
	private WebElement org;
	// Initiallization

	public ContactInformation_POMpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utillization

	public WebElement getlastName() {
		return lastname;
	}
	
	public WebElement getOrg() {
		return org;
	}

	public void verifyCont(WebElement ele, String str, String msg1, String msg2) {
		if (ele.getText().contains(str)) {
			System.out.println(msg1);
		} else {
			System.out.println(msg2);
		}
	}

}
