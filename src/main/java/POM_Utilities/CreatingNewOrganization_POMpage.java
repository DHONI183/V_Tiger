package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Utilities.WebDriverUtility;

public class CreatingNewOrganization_POMpage {

	// Declaration

	@FindBy(partialLinkText = "Home")
	private WebElement header;

	@FindBy(linkText = "Organizations")
	private WebElement orgTab;

	@FindBy(linkText = "Contacts")
	private WebElement conTab;

	@FindBy(xpath = "//span[text()=' Administrator']/../../descendant::img")
	private WebElement admin;

	@FindBy(linkText = "Sign Out")
	private WebElement signout;

	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement createOrg;

	@FindBy(name = "accountname")
	private WebElement orgname;

	@FindBy(name="button")
	private WebElement saveOrg;
	
	@FindBy(id="phone")
	private WebElement phnoTF;
	
	@FindBy(name="industry")
	private WebElement indusDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;

	// Initialization
	public CreatingNewOrganization_POMpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public void getCreateOrg() {
		createOrg.click();
	}

	public void enterOrgName(String OrgName) {
		orgname.sendKeys(OrgName);
	}
	
	public void selIndustry(WebDriver driver , String opt) {		
		WebDriverUtility web = new WebDriverUtility();
		web.SelectDropDown_UsingValue(driver, indusDD , opt);
	}
	
	public void selType(WebDriver driver , String opt) {		
		WebDriverUtility web = new WebDriverUtility();
		web.SelectDropDown_UsingValue(driver, typeDD , opt);
	}
	
	public void enterPhoneName(String phno) {
		phnoTF.sendKeys(phno);
	}
	
	public void saveOrg() {
		saveOrg.click();
	}

	// Business logic for logout
}
