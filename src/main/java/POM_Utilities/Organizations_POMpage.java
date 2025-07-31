package POM_Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizations_POMpage {

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
    
	// Initialization
    public Organizations_POMpage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

	// Utilization
    public void getCreateOrg() {
        createOrg.click();		
    }
    
    public void deleteOrg(WebDriver driver, String orgName) {
        String xpath = "//a[text()='" + orgName + "']/ancestor::tr[@bgcolor='white']//a[text()='del']";
        WebElement deleteLink = driver.findElement(By.xpath(xpath));
        deleteLink.click();
    }
    
    

	// Business logic for logout
}
