package Vtiger_Framework;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Generic_Utilities.ExcelFileUtility;
import Generic_Utilities.WebDriverUtility;
import Listners_Utilities.BaseClass;
import POM_Utilities.CreatingNewOrganization_POMpage;
import POM_Utilities.Home_POMpage;
import POM_Utilities.OrganizationInformation_POMpage;
import POM_Utilities.Organizations_POMpage;

@Listeners(Listners_Utilities.Listners.class)
public class CreateOrgGroup extends BaseClass {

	// Organization Module 1st script

	@Test(groups =  "smoke" , enabled = true , retryAnalyzer = Listners_Utilities.IRetryAnalyserUtility.class)
	public void createNewOrgTest() throws InterruptedException, IOException {

	

		// *****************************************************************************************************************************//
		// Fetching Data From Excel Utility File

		Listners_Utilities.Listners.test.log(Status.INFO , "Fetching the data from Excel sheet");
		ExcelFileUtility ex = new ExcelFileUtility();
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 1, 2);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		Listners_Utilities.Listners.test.log(Status.INFO , "Opening the Browser");
		WebDriverUtility web = new WebDriverUtility(); // Open The Browser

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		OrganizationInformation_POMpage OrgInfoPage = new OrganizationInformation_POMpage(driver);
		CreatingNewOrganization_POMpage CrtOrgPage = new CreatingNewOrganization_POMpage(driver);
		Organizations_POMpage OrgPage = new Organizations_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// *******************************************************************************************************************************//
		// Login

		// loginPage.login(username, password);

		// *******************************************************************************************************************************//
		// Creating New Orgination and Saving

		Listners_Utilities.Listners.test.log(Status.INFO , "Creating New Orgination and Saving");
		HomePage.getOrgTab(); // Identify organization link and click
		OrgPage.getCreateOrg(); // Identify create plus icon n click
		CrtOrgPage.enterOrgName(org_Name); // click on org name textfield enter data
		CrtOrgPage.saveOrg(); // identify save button and click

		// *******************************************************************************************************************************//
		// Virify the Orginization Provided

		Listners_Utilities.Listners.test.log(Status.INFO , "Virify the Orginization Provided");
		WebElement orgName = OrgInfoPage.getheaderOrg(); // getting the webelement of Orginization Name
		String orgnalname = orgName.getText();	
	    Assert.assertEquals(org_Name, orgnalname);

		
		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		Thread.sleep(3000);
		
		Listners_Utilities.Listners.test.log(Status.INFO , "identify and click on del link");
		// identify and click on del link
		OrgPage.deleteOrg(driver, org_Name);

		// *******************************************************************************************************************************//
		// Handle delete pop up
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver);

	}

	// Organization Module 2nd script

	@Test(groups =  "smoke" , enabled = true , retryAnalyzer = Listners_Utilities.IRetryAnalyserUtility.class)
	public void createNewOrgTest_Excel() throws InterruptedException, IOException {


		// *****************************************************************************************************************************//
		// Fetching Data From Excel Utility File

		Listners_Utilities.Listners.test.log(Status.INFO , "Fetching the data from Excel sheet");
		ExcelFileUtility ex = new ExcelFileUtility();
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 4, 2);
		String phno = ex.fetchDataFromExcelFile("Org_Data", 4, 3);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		Listners_Utilities.Listners.test.log(Status.INFO , " Opening The Browser");
		WebDriverUtility web = new WebDriverUtility(); // Open The Browser
	
		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		OrganizationInformation_POMpage OrgInfoPage = new OrganizationInformation_POMpage(driver);
		CreatingNewOrganization_POMpage CrtOrgPage = new CreatingNewOrganization_POMpage(driver);
		Organizations_POMpage OrgPage = new Organizations_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// *******************************************************************************************************************************//
		// Login

		// loginPage.login(username, password);

		// *******************************************************************************************************************************//
		// Creating New Orgination and Saving

		Listners_Utilities.Listners.test.log(Status.INFO , " Creating New Orgination and Saving");
		HomePage.getOrgTab(); // Identify organization link and click
		OrgPage.getCreateOrg(); // Identify create plus icon n click
		CrtOrgPage.enterOrgName(org_Name); // click on org name textfield enter data
		CrtOrgPage.enterPhoneName(phno); // Enter the Phone Number
		CrtOrgPage.saveOrg(); // identify save button and click

		// *******************************************************************************************************************************//
		// Virify the Orginization Provided

		Listners_Utilities.Listners.test.log(Status.INFO , " Virify the Orginization Provided");
		WebElement orgName = OrgInfoPage.getheaderOrg(); // getting the webelement of Orginization Name	
		String orgnalname = orgName.getText();
		Assert.assertEquals(org_Name,orgnalname );


		WebElement phoneinfo = OrgInfoPage.getPhoneNum();		
		String actualorgphone = phoneinfo.getText();	
		Assert.assertEquals(phno,actualorgphone );

		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		// identify and click on del link
		
		Listners_Utilities.Listners.test.log(Status.INFO , " identify and click on del link");
		OrgPage.deleteOrg(driver, org_Name);

		// *******************************************************************************************************************************//
		// Handle delete pop up
		Thread.sleep(3000);
		web.HandleAlertPopupAndClickOK(driver);


		
	}

	@Test(groups =  "smoke" , retryAnalyzer = Listners_Utilities.IRetryAnalyserUtility.class)
	public void createNewOrgTest_Excel_3rd_script() throws InterruptedException, IOException {

		// *****************************************************************************************************************************//
		// Fetching Data From Property Utility File

	
		// *****************************************************************************************************************************//
		// Fetching Data From Excel Utility File
		

		Listners_Utilities.Listners.test.log(Status.INFO , "Fetching the data from Excel sheet");
		ExcelFileUtility ex = new ExcelFileUtility();
		String org_Name = ex.fetchDataFromExcelFile("Org_Data", 7, 2);
		String phno = ex.fetchDataFromExcelFile("Org_Data", 7, 3);
		String industry_drop = ex.fetchDataFromExcelFile("Org_Data", 7, 4);
		String type_drop = ex.fetchDataFromExcelFile("Org_Data", 7, 5);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		Listners_Utilities.Listners.test.log(Status.INFO , "Opening The Browser");
		WebDriverUtility web = new WebDriverUtility(); // Open The Browser


		// *******************************************************************************************************************************//
		phno = web.phoneFormat(phno);
		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		OrganizationInformation_POMpage OrgInfoPage = new OrganizationInformation_POMpage(driver);
		CreatingNewOrganization_POMpage CrtOrgPage = new CreatingNewOrganization_POMpage(driver);
		Organizations_POMpage OrgPage = new Organizations_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// *******************************************************************************************************************************//
		// Login

		// loginPage.login(username, password);

		// *******************************************************************************************************************************//
		// Creating New Orgination and Saving
		

		Listners_Utilities.Listners.test.log(Status.INFO , "Creating New Orgination and Saving");
		HomePage.getOrgTab(); // Identify organization link and click
		OrgPage.getCreateOrg(); // Identify create plus icon n click
		CrtOrgPage.enterOrgName(org_Name); // click on org name textfield enter data
		CrtOrgPage.enterPhoneName(phno); // Enter the Phone Number
		CrtOrgPage.selIndustry(driver, industry_drop); // Industry dropdown
		CrtOrgPage.selType(driver, type_drop); // type drop down
		CrtOrgPage.saveOrg(); // identify save button and click

		// *******************************************************************************************************************************//
		// Virify the Orginization Provided

		Listners_Utilities.Listners.test.log(Status.INFO , "Virify the Orginization Provided");
		WebElement orgName = OrgInfoPage.getheaderOrg(); // getting the webelement of Orginization Name
		String OrgName = orgName.getText();
		Assert.assertEquals(org_Name, OrgName);
		
		WebElement phoneinfo = OrgInfoPage.getPhoneNum();
		String actualphone = phoneinfo.getText();
		Assert.assertEquals(phno, actualphone);

		WebElement industryinfo = OrgInfoPage.getIndustry();		
		String orginfo = industryinfo.getText();
		Assert.assertEquals(industry_drop , orginfo);

		WebElement typeinfo = OrgInfoPage.getType();	
		String actualtype = typeinfo.getText();
		Assert.assertEquals(type_drop, actualtype);
		
		HomePage.getOrgTab(); // Identify organization link and click

		// *******************************************************************************************************************************//
		// identify and click on del link
		
		Listners_Utilities.Listners.test.log(Status.INFO , "identify and click on del link");
		OrgPage.deleteOrg(driver, org_Name);

		// *******************************************************************************************************************************//
		// Handle delete pop up

		web.HandleAlertPopupAndClickOK(driver);

	}

}
