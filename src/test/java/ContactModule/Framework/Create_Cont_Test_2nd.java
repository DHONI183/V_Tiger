package ContactModule.Framework;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import Generic_Utilities.ExcelFileUtility;
import Generic_Utilities.PropertyFileUtility;
import Generic_Utilities.WebDriverUtility;
import POM_Utilities.ContactInformation_POMpage;
import POM_Utilities.Contacts_POMpage;
import POM_Utilities.CreatingNewContact_POMpage;
import POM_Utilities.Home_POMpage;
import POM_Utilities.Login_POMpage;

public class Create_Cont_Test_2nd {

	@Test(groups = {"regression"})
	public void createContactModule_2nd() throws IOException, InterruptedException {

		// *****************************************************************************************************************************//
		// Fetching Data From Property Utility File

		PropertyFileUtility p = new PropertyFileUtility();
		String url = p.fetchDataFromPropFile("url");
		String timeouts = p.fetchDataFromPropFile("timeouts");
		String username = p.fetchDataFromPropFile("username");
		String password = p.fetchDataFromPropFile("password");

		// *****************************************************************************************************************************//
		// Fetching Data From Excel Utility File

		ExcelFileUtility b = new ExcelFileUtility();
	//	String conName = b.fetchDataFromExcelFile("Contact_Data", 1, 2);
		String lastName = b.fetchDataFromExcelFile("Contact_Data", 1, 3);
	//	String gender = b.fetchDataFromExcelFile("Contact_Data", 1, 4);

		// *****************************************************************************************************************************//
		// Opening The Browser and navigating to the Application

		WebDriverUtility web = new WebDriverUtility(); // Open The Browser
		WebDriver driver = new ChromeDriver(); // Maximize the Browser
		web.maximizetheWindow(driver); // Implicit wait
		web.waituntilElementIsFound(driver, timeouts); // Navigate to an application
		web.navigateToAnApplication(driver, url);

		// *******************************************************************************************************************************//
		// Creating JavaObject for Every Required Class

		ContactInformation_POMpage ContInfoPage = new ContactInformation_POMpage(driver);
		CreatingNewContact_POMpage CrtContPage = new CreatingNewContact_POMpage(driver);
		Contacts_POMpage ContPage = new Contacts_POMpage(driver);
		Login_POMpage loginPage = new Login_POMpage(driver);
		Home_POMpage HomePage = new Home_POMpage(driver);

		// *******************************************************************************************************************************//
		// Login

		loginPage.login(username, password);

		// *******************************************************************************************************************************//
		// Get into Contact Page then new Contact Page

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

		WebElement last_name = ContInfoPage.getlastName();
		ContInfoPage.verifyCont(last_name, lastName, "Correct", "Wrong");

		// *****************************************************************************************************************************//
		// Open the Contact Page

		HomePage.getConTab();

		// *****************************************************************************************************************************//
		// identify Contact and delete that contact.

		ContPage.deleteCont(driver, lastName);

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
