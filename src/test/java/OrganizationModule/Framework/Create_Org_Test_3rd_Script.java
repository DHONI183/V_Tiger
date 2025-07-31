package OrganizationModule.Framework;


import java.io.IOException;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Generic_Utilities.ExcelFileUtility;
import Generic_Utilities.PropertyFileUtility;
import Generic_Utilities.WebDriverUtility;
import POM_Utilities.CreatingNewOrganization_POMpage;
import POM_Utilities.Home_POMpage;
import POM_Utilities.Login_POMpage;
import POM_Utilities.OrganizationInformation_POMpage;
import POM_Utilities.Organizations_POMpage;

public class Create_Org_Test_3rd_Script {

	@Parameters("browser")
	@Test (groups = {"smoke"})
	public void createNewOrgTest_Excel_3rd_script(String BROWSER) throws InterruptedException, IOException {

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
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 7, 2);
		String phno = ex.fetchDataFromExcelFile("Org_Data", 7, 3);
		String industry_drop = ex.fetchDataFromExcelFile("Org_Data", 7, 4);
		String type_drop = ex.fetchDataFromExcelFile("Org_Data", 7, 5);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		WebDriverUtility web = new WebDriverUtility(); // Open The Browser
		WebDriver driver = null;
				
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		web.maximizetheWindow(driver); // Implicit wait
		web.waituntilElementIsFound(driver, timeouts); // Navigate to an application
		web.navigateToAnApplication(driver, url);

		// *******************************************************************************************************************************//
		phno = web.phoneFormat(phno);
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
		CrtOrgPage.enterPhoneName(phno); // Enter the Phone Number
		CrtOrgPage.selIndustry(driver, industry_drop); // Industry dropdown
		CrtOrgPage.selType(driver, type_drop); // type drop down
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

		WebElement industryinfo = OrgInfoPage.getIndustry();
		OrgInfoPage.verifyOrg(industryinfo, industry_drop, " Organization Name is " + industry_drop + " is correct ",
				" Organization Name is" + industry_drop + " is Wrong "); // Verifying the phone
		// Number
		
		WebElement typeinfo = OrgInfoPage.getType();
		OrgInfoPage.verifyOrg(typeinfo, type_drop, " Organization Name is " + type_drop + " is correct ",
				" Organization Name is" + type_drop + " is Wrong "); // Verifying the phone
		// Number
		
		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		// identify and click on del link
		OrgPage.deleteOrg(driver, org_Name);

		// *******************************************************************************************************************************//
		// Handle delete pop up

		web.HandleAlertPopupAndClickOK(driver);

		// *******************************************************************************************************************************//
		// SignOut From the Application

		HomePage.logout(driver);

		// *******************************************************************************************************************************//
		// Quiting the Browser

		web.quitTheBrowser(driver);

	}

}
