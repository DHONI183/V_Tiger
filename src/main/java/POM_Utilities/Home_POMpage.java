package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Generic_Utilities.WebDriverUtility;

public class Home_POMpage {

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

    // Initialization
    public Home_POMpage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Utilization
    public String getHeader() {
        return header.getText();
    }

    public void getOrgTab() {
        orgTab.click();
    }

    public void getConTab() {
        conTab.click();
    }

    public WebElement getAdmin() {
        return admin;
    }

    public void getSignout() {
        signout.click();
    }

    // Business logic for logout
    public void logout(WebDriver driver) {
        WebDriverUtility wutil = new WebDriverUtility();
        wutil.mousehoverOnAnElement(driver, admin);
        signout.click();
    }
}
