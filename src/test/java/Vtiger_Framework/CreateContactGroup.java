package Vtiger_Framework;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import Generic_Utilities.ExcelFileUtility;
import Generic_Utilities.WebDriverUtility;
import Listners_Utilities.BaseClass;
import POM_Utilities.ContactInformation_POMpage;
import POM_Utilities.Contacts_POMpage;
import POM_Utilities.CreatingNewContact_POMpage;
import POM_Utilities.CreatingNewOrganization_POMpage;
import POM_Utilities.Home_POMpage;
import POM_Utilities.OrganizationInformation_POMpage;
import POM_Utilities.Organizations_POMpage;

 @Listeners(Listners_Utilities.Listners.class)

public class CreateContactGroup extends BaseClass {

	
	@Test(groups = "regression", enabled = true  )
	public void createContactModule_1st() throws IOException, InterruptedException {

		Listners_Utilities.Listners.test.log(Status.INFO , "Fetching data from excel file");
		ExcelFileUtility b = new ExcelFileUtility();
		String lastName = b.fetchDataFromExcelFile("Contact_Data", 1, 3);

		// *******************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		Listners_Utilities.Listners.test.log(Status.INFO , "Opening The Browser and navigating to the Application");
		WebDriverUtility web = new WebDriverUtility(); // Open The Browser WebDriver

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		ContactInformation_POMpage ContInfoPage = new ContactInformation_POMpage(driver);
		CreatingNewContact_POMpage CrtContPage = new CreatingNewContact_POMpage(driver);
		Contacts_POMpage ContPage = new Contacts_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);
		SoftAssert soft = new SoftAssert();
		//soft.assertEquals(HomePage.getHeader(), "Home");

		// Identify organization link and click

		Listners_Utilities.Listners.test.log(Status.INFO , "Identify organization link and click");
		HomePage.getConTab();
		ContPage.getCreateNewContact();

		// ******************************************************************************************************************************//

		CrtContPage.enterLastName(lastName);
		CrtContPage.saveCont();

		// ******************************************************************************************************************************//
		// Verify the Contact Info

		Listners_Utilities.Listners.test.log(Status.INFO , "Verify the Contact Info");
		WebElement last_name = ContInfoPage.getlastName();
		String lastName_1 = last_name.getText();
		Assert.assertEquals(lastName_1, lastName);
		HomePage.getConTab();

		// ******************************************************************************************************************************//
		// identify and click on del link
		
		Listners_Utilities.Listners.test.log(Status.INFO , "Deleting the Contact");
		Thread.sleep(3000);
		ContPage.deleteCont(driver, lastName);

		// *******************************************************************************************************************************//
		// Handle delete pop up
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver);
		soft.assertAll();

	}

	@Test(groups =  "regression" , enabled = true )
	public void createContactModule_2nd() throws IOException, InterruptedException {

		// *****************************************************************************************************************************//
		// Fetching Data From Excel Utility File

		Listners_Utilities.Listners.test.log(Status.INFO , "Fetching the Data from Excel sheet");
		ExcelFileUtility b = new ExcelFileUtility();
		String lastName = b.fetchDataFromExcelFile("Contact_Data", 1, 3);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		Listners_Utilities.Listners.test.log(Status.INFO , "Opening the browser");
		WebDriverUtility web = new WebDriverUtility(); // Open The Browser

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		ContactInformation_POMpage ContInfoPage = new ContactInformation_POMpage(driver);
		CreatingNewContact_POMpage CrtContPage = new CreatingNewContact_POMpage(driver);
		Contacts_POMpage ContPage = new Contacts_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// Get into Contact Page then new Contact Page

		Listners_Utilities.Listners.test.log(Status.INFO , "Creating new Contact");
		Thread.sleep(3000);
		HomePage.getConTab();
		ContPage.getCreateNewContact();

		// *****************************************************************************************************************************//
		// creating new Contact

		CrtContPage.enterLastName(lastName); // Entering lastName
		CrtContPage.setStartingDate(); // setting the starting date
		CrtContPage.setEndingDate(30); // setting the ending date
		CrtContPage.saveCont();

		// *****************************************************************************************************************************//
		// Verify the Contact Info

		Listners_Utilities.Listners.test.log(Status.INFO , "Verify the Contact Info");
		WebElement last_name = ContInfoPage.getlastName();
		String lastName_1 = last_name.getText();
		Assert.assertEquals(lastName_1, lastName);

		// *****************************************************************************************************************************//
		// Open the Contact Page

		Listners_Utilities.Listners.test.log(Status.INFO , "deleting the Contact");
		HomePage.getConTab();

		// *****************************************************************************************************************************//
		// identify Contact and delete that contact.

		ContPage.deleteCont(driver, lastName);

		// *******************************************************************************************************************************//
		// Handle delete pop up
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver);

	}

	@Test(groups = "regression" , enabled = true )
	public void createContactModule_3rd() throws IOException, InterruptedException {

		// Fetching Data From Excel Utility File

		Listners_Utilities.Listners.test.log(Status.INFO , "Fetching the data from Excel sheet");
		ExcelFileUtility ex = new ExcelFileUtility();
		String lastName = ex.fetchDataFromExcelFile("Contact_Data", 7, 3);
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 7, 2);
		String phno = ex.fetchDataFromExcelFile("Org_Data", 7, 3);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		WebDriverUtility web = new WebDriverUtility(); // Open The Browser
		phno = web.phoneFormat(phno);

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		OrganizationInformation_POMpage OrgInfoPage = new OrganizationInformation_POMpage(driver);
		CreatingNewOrganization_POMpage CrtOrgPage = new CreatingNewOrganization_POMpage(driver);
		Organizations_POMpage OrgPage = new Organizations_POMpage(driver);
		ContactInformation_POMpage ContInfoPage = new ContactInformation_POMpage(driver);
		CreatingNewContact_POMpage CrtContPage = new CreatingNewContact_POMpage(driver);
		Contacts_POMpage ContPage = new Contacts_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// Creating New Orgination and Saving

		Listners_Utilities.Listners.test.log(Status.INFO , "Creating a new Organization");
		HomePage.getOrgTab(); // Identify organization link and click
		OrgPage.getCreateOrg(); // Identify create plus icon n click
		CrtOrgPage.enterOrgName(org_Name); // click on org name textfield enter data
		CrtOrgPage.enterPhoneName(phno); // Enter the Phone Number
		CrtOrgPage.saveOrg(); // identify save button and click

		// *******************************************************************************************************************************//
		// Virify the Orginization Provided

		Listners_Utilities.Listners.test.log(Status.INFO , "Verify the Organization Info");
		WebElement orgName = OrgInfoPage.getheaderOrg(); // getting the webelement of Orginization Name
		Thread.sleep(3000);
		String orgnalname = orgName.getText();
		Assert.assertEquals(orgnalname, org_Name);	
		
		WebElement phoneinfo = OrgInfoPage.getPhoneNum();
		String actualorgphone = phoneinfo.getText();
		Assert.assertEquals(phno, actualorgphone);

		// *******************************************************************************************************************************//
		// Get into Contact Page then new Contact Page

		Listners_Utilities.Listners.test.log(Status.INFO , "Creating new Contact");
		Thread.sleep(3000);
		HomePage.getConTab();
		Thread.sleep(3000);
		ContPage.getCreateNewContact();

		// *****************************************************************************************************************************//
		// creating new Contact

		CrtContPage.enterLastName(lastName); // Entering lastName
		CrtContPage.setStartingDate(); // setting the starting date
		CrtContPage.setEndingDate(30); // setting the ending date
		Thread.sleep(3000);
		CrtContPage.selectOrg(driver, org_Name); // selecting organization by passed value
		Thread.sleep(3000);
		CrtContPage.saveCont();

		// *****************************************************************************************************************************//
		// Verify the Contact Info
		
		Listners_Utilities.Listners.test.log(Status.INFO , "Verify the New Contact Info");
		WebElement last_name = ContInfoPage.getlastName();
		String lastName_1 = last_name.getText();
		Assert.assertEquals(lastName_1, lastName);
		
		WebElement orgname = ContInfoPage.getOrg();
		Thread.sleep(3000);
		String orgName_1 = orgname.getText();
		Assert.assertEquals(orgName_1, org_Name);

		// *****************************************************************************************************************************//
		
		Thread.sleep(3000);
		HomePage.getConTab(); // Open the Contact Page

		// *****************************************************************************************************************************//
		// identify Contact and delete that contact.
		
		Listners_Utilities.Listners.test.log(Status.INFO , "deleting Contact");
		Thread.sleep(3000);
		ContPage.deleteCont(driver, lastName);
		
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver); // Handle delete pop up

		// *******************************************************************************************************************************//

		
		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		// identify and click on del link
		
		Listners_Utilities.Listners.test.log(Status.INFO , "Deleting Oragnization");
		Thread.sleep(3000);
		OrgPage.deleteOrg(driver, org_Name);
		
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver); // Handle delete pop up

	}

}
