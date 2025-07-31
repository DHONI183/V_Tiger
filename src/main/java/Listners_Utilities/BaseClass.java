package Listners_Utilities;

	//package OrganizationModule.Framework;

	import java.io.IOException;
	import java.sql.SQLException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.testng.Reporter;
	import org.testng.annotations.*;
	import Generic_Utilities.*;
	import Generic_Utilities.PropertyFileUtility;
	import Generic_Utilities.WebDriverUtility;	
	import POM_Utilities.*;

	/**
	 * BaseClass - Contains common setup and teardown methods
	 * for all test classes, using TestNG annotations.
	 */
	public class BaseClass {

		
	    // Utility classes for database, property file, and WebDriver operations
		DatabaseFileUtility dbutil = new DatabaseFileUtility();
	    PropertyFileUtility prop = new PropertyFileUtility();
	    WebDriverUtility wutil = new WebDriverUtility();

	    
	    // Global WebDriver instance
	    public WebDriver driver = null;

	    public static WebDriver sDriver = null;
	    
	    /**
	     * Connect to the database before the test suite begins.
	     */
	    @BeforeSuite
	    public void getConnectionToDB() throws Exception {
	         dbutil.getDBConn(); // Uncomment to enable DB connection
	    }

	    
	    /**
	     * Executes before any test begins, used to configure parallel execution.
	     */
	    @BeforeTest
	    public void conParallelExe() {
	        Reporter.log("Configure parallel Execution", true);
	    }

	    
	    /**
	     * Launches the browser before any test class runs.
	     * Reads browser type from property file.
	     */
	    @Parameters("browser")
	    @BeforeClass
	    public void launchTheBrowser() throws IOException {
	        String BROWSER = prop.fetchDataFromPropFile("browser");

	        if (BROWSER.equals("chrome")) {
	            driver = new ChromeDriver();
	        } else if (BROWSER.equals("edge")) {
	            driver = new EdgeDriver();
	        } else {
	            driver = new ChromeDriver(); // Default to Chrome
	        }
	        
	        sDriver = driver;
	    }

	    
	    /**
	     * Logs into the application before each test method.
	     * Loads username, password, URL, and timeout from the property file.
	     */
	    @BeforeMethod
	    public void login() throws IOException {
	        String UN = prop.fetchDataFromPropFile("username");
	        String PSWD = prop.fetchDataFromPropFile("password");
	        String URL = prop.fetchDataFromPropFile("url");
	        String Time = prop.fetchDataFromPropFile("timeouts");

	        wutil.maximizetheWindow(driver);
	        wutil.waituntilElementIsFound(driver, Time);
	        wutil.navigateToAnApplication(driver, URL);

	        Login_POMpage loginPage = new Login_POMpage(driver);
	        loginPage.login(UN, PSWD);
	    }

	    
	    /**
	     * Logs out of the application after each test method.
	     */
	    @AfterMethod
	    public void logout() {
	    	Home_POMpage HomePage = new Home_POMpage(driver);
	        HomePage.logout(driver);
	    }

	    
	    /**
	     * Closes the browser after the entire test class finishes execution.
	     */
	    @AfterClass
	    public void closeTheBrowser() {
	        wutil.quitTheBrowser(driver);
	    }

	    
	    /**
	     * Executes after all test methods in the test run.
	     * Used for parallel configuration cleanup.
	     */
	    @AfterTest
	    public void closeParalelExe() {
	        Reporter.log("Close Configuration of parallel Execution", true);
	    }

	    
	    /**
	     * Closes the database connection after the test suite ends.
	     */
	    @AfterSuite
	    public void closeConnectionWithDB() throws SQLException {
	         dbutil.closeDatabaseConnection(); // Uncomment to enable DB close
	    }


}
