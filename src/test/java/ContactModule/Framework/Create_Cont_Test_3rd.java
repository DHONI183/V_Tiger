package ContactModule.Framework;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import Generic_Utilities.*;
import POM_Utilities.ContactInformation_POMpage;
import POM_Utilities.Contacts_POMpage;
import POM_Utilities.CreatingNewContact_POMpage;
import POM_Utilities.CreatingNewOrganization_POMpage;
import POM_Utilities.Home_POMpage;
import POM_Utilities.Login_POMpage;
import POM_Utilities.OrganizationInformation_POMpage;
import POM_Utilities.Organizations_POMpage;

public class Create_Cont_Test_3rd {

	@Test(groups = {"regression"})
	public void createContactModule_3rd() throws IOException, InterruptedException {

		// *****************************************************************************************************************************//
		// Fetching Data From Property Utility File

		PropertyFileUtility p = new PropertyFileUtility();
		String url = p.fetchDataFromPropFile("url");
		String timeouts = p.fetchDataFromPropFile("timeouts");
		String username = p.fetchDataFromPropFile("username");
		String password = p.fetchDataFromPropFile("password");

		// *****************************************************************************************************************************//
		// Fetching Data From Excel Utility File

		ExcelFileUtility ex = new ExcelFileUtility();

		// Fetching Contact Data From Contact_Data Sheet
		// String con_Name = ex.fetchDataFromExcelFile("Contact_Data", 7, 2);
		String lastName = ex.fetchDataFromExcelFile("Contact_Data", 7, 3);
		// String gender = ex.fetchDataFromExcelFile("Contact_Data", 7, 4);

		// Fetching Organization Data From Org_Data Sheet
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 7, 2);
		String phno = ex.fetchDataFromExcelFile("Org_Data", 7, 3);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		WebDriverUtility web = new WebDriverUtility(); // Open The Browser
		WebDriver driver = new ChromeDriver(); // Maximize the Browser
		web.maximizetheWindow(driver); // Implicit wait
		web.waituntilElementIsFound(driver, timeouts); // Navigate to an application
		web.navigateToAnApplication(driver, url);
		
		phno = web.phoneFormat(phno);

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		OrganizationInformation_POMpage OrgInfoPage = new OrganizationInformation_POMpage(driver);
		CreatingNewOrganization_POMpage CrtOrgPage = new CreatingNewOrganization_POMpage(driver);
		Organizations_POMpage OrgPage = new Organizations_POMpage(driver);
		ContactInformation_POMpage ContInfoPage = new ContactInformation_POMpage(driver);
		CreatingNewContact_POMpage CrtContPage = new CreatingNewContact_POMpage(driver);
		Contacts_POMpage ContPage = new Contacts_POMpage(driver);
		Login_POMpage loginPage = new Login_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// *******************************************************************************************************************************//
		// Login

		loginPage.login(username, password);

		// *******************************************************************************************************************************//
		// Creating New Orgination and Saving

		HomePage.getOrgTab(); // Identify organization link and click
		OrgPage.getCreateOrg(); // Identify create plus icon n click
		CrtOrgPage.enterOrgName(org_Name); // click on org name textfield enter data
		CrtOrgPage.enterPhoneName(phno); // Enter the Phone Number
		CrtOrgPage.saveOrg(); // identify save button and click

		// *******************************************************************************************************************************//
		// Virify the Orginization Provided

		WebElement orgName = OrgInfoPage.getheaderOrg(); // getting the webelement of Orginization Name
		OrgInfoPage.verifyOrg(orgName, org_Name, " Organization Name is " + org_Name + " is correct ",
				" Organization Name is" + org_Name + " is Wrong "); // Verifying the
		// Text

		WebElement phoneinfo = OrgInfoPage.getPhoneNum();
		OrgInfoPage.verifyOrg(phoneinfo, phno, " Organization Name is " + phno + " is correct ",
				" Organization Name is" + phno + " is Wrong "); // Verifying the phone
		// Number

		// *******************************************************************************************************************************//
		// Get into Contact Page then new Contact Page

		HomePage.getConTab();
		ContPage.getCreateNewContact();

		// *****************************************************************************************************************************//
		// creating new Contact

		CrtContPage.enterLastName(lastName); // Entering lastName
		CrtContPage.setStartingDate(); // setting the starting date
		CrtContPage.setEndingDate(30); // setting the ending date
		CrtContPage.selectOrg(driver,org_Name ); // selecting organization by passed value		
		CrtContPage.saveCont();

		// *****************************************************************************************************************************//
		// Verify the Contact Info

		WebElement last_name = ContInfoPage.getlastName();
		ContInfoPage.verifyCont(last_name, lastName, "Correct", "Wrong");
		
		WebElement orgname = ContInfoPage.getOrg();
		ContInfoPage.verifyCont(orgname, org_Name, "Correct", "Wrong");		

		// *****************************************************************************************************************************//
		
		HomePage.getConTab();  // Open the Contact Page

		// *****************************************************************************************************************************//
		// identify Contact and delete that contact.
		Thread.sleep(3000);

		ContPage.deleteCont(driver, lastName);
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver); // Handle delete pop up

		// *******************************************************************************************************************************//

		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		// identify and click on del link
		Thread.sleep(3000);
		OrgPage.deleteOrg(driver, org_Name);
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver); // Handle delete pop up

		// *******************************************************************************************************************************//
		// SignOut From the Application

		HomePage.logout(driver);

		// *******************************************************************************************************************************//
		// Quiting the Browser

		web.quitTheBrowser(driver);
	}

}
