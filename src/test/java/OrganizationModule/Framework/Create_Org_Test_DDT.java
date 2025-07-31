package OrganizationModule.Framework;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import Generic_Utilities.*;
import POM_Utilities.*;

public class Create_Org_Test_DDT {

	// Organization Module 1st script

	@Test (groups = {"smoke"})
	public void createNewOrgTest() throws InterruptedException, IOException {

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
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 1, 2);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		WebDriverUtility web = new WebDriverUtility(); // Open The Browser
		WebDriver driver = new ChromeDriver(); // Maximize the Browser
		web.maximizetheWindow(driver); // Implicit wait
		web.waituntilElementIsFound(driver, timeouts); // Navigate to an application
		web.navigateToAnApplication(driver, url);

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		OrganizationInformation_POMpage OrgInfoPage = new OrganizationInformation_POMpage(driver);
		CreatingNewOrganization_POMpage CrtOrgPage = new CreatingNewOrganization_POMpage(driver);
		Organizations_POMpage OrgPage = new Organizations_POMpage(driver);
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
		CrtOrgPage.saveOrg(); // identify save button and click

		// *******************************************************************************************************************************//
		// Virify the Orginization Provided

		WebElement orgName = OrgInfoPage.getheaderOrg(); // getting the webelement of Orginization Name
		OrgInfoPage.verifyOrg(orgName, org_Name, " Organization Name is" + org_Name + " is correct ",
				" Organization Name is" + org_Name + " is Wrong "); // Verifying the Text
		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		Thread.sleep(3000);
		// identify and click on del link
		OrgPage.deleteOrg(driver, org_Name);

		// *******************************************************************************************************************************//
		// Handle delete pop up
Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver);

		// *******************************************************************************************************************************//
		// SignOut From the Application

		HomePage.logout(driver);

		// *******************************************************************************************************************************//
		// Quiting the Browser

		web.quitTheBrowser(driver);

	}
}
