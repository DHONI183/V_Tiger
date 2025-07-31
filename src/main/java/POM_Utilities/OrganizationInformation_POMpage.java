package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformation_POMpage {

	// Declaration
		
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

		@FindBy(xpath = "//span[@id='dtlview_Organization Name']")
		private WebElement orgname;
		
		@FindBy(id="dtlview_Phone")
		private WebElement phno;
		
		@FindBy(id="dtlview_Industry")
		private WebElement industry_dd;
		
		@FindBy(id="dtlview_Type")
		private WebElement type_dd;
		
		// Initialization
		public OrganizationInformation_POMpage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		// Utilization
		
		public void getCreateOrg() {
			createOrg.click();
		}
		
		public WebElement getheaderOrg() {
			return orgname;
		}
		
		public WebElement getPhoneNum() {
			return phno;
		}
		
		public WebElement getIndustry() {
			return industry_dd;
		}
		
		public WebElement getType() {
			return type_dd;
		}
		
		
		public void verifyOrg(WebElement ele , String str , String msg1 , String msg2) {
			if (ele.getText().contains(str)) {
				System.out.println(msg1);
			} else {
				System.out.println(msg2);
			}
		}

		

		// Business logic for logout
	
}
