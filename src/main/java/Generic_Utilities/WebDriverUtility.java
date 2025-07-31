package Generic_Utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	
	/**
	 * @Note : This method is to go to the URL
	 * @param driver
	 * @param url
	 */
	public void navigateToAnApplication(WebDriver driver, String url) {
		driver.get(url);
	}

	
	/**
	 * @Note : This method is to maximize the Browser
	 * @param driver
	 */
	public void maximizetheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	
	/**
	 * @Note : This method is to impliment implicitlywait
	 * @param driver
	 * @param time
	 */
	public void waituntilElementIsFound(WebDriver driver, String time) {
		long Timeouts = Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeouts));
	}

	
	/**
	 * @Note : This method is to Accept the Alert Popup.
	 * @param driver
	 */
	public void HandleAlertPopupAndClickOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	
	/**
	 * @Note : This method is to make cursor mouse hover over the Element.
	 * @param driver
	 * @param element
	 */
	public void mousehoverOnAnElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	
	/**
	 * @Note : This method is to Quit the Browser
	 * @param driver
	 */
	public void quitTheBrowser(WebDriver driver) {
		driver.quit();
	}

	
	/**
	 * @Note : This method is for Clicking DropDown using Value.
	 * @param driver
	 * @param DropdownElement
	 * @param Value
	 */
	public void SelectDropDown_UsingValue(WebDriver driver, WebElement DropdownElement, String Value) {
		Select s = new Select(DropdownElement);
		s.selectByValue(Value);
	}

	
	/**
	 * @Note : This method is used to get the Parent Window ID.
	 * @param driver
	 * @return String
	 */
	public String fetchParentWindowID(WebDriver driver) {
		String parentWebpage_ID = driver.getWindowHandle();
		return parentWebpage_ID;
	}

	
	/**
	 * @Note : This method is used to get all the Winndow IDs.
	 * @param driver
	 * @return String
	 */
	public Set<String> fetchMultipleWindowIDs(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		return windows;
	}

	/**
	 * @Note : This method is used to switch to child Window with the help of title.
	 * @param driver
	 * @param title
	 * @param windows
	 */
	public void switchToChildWindow_UsingTitle(WebDriver driver, String title, Set<String> windows) {
		for (String s : windows) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(title)) {
				break;
			}
		}
	}

	/**
	 * @Note : This method is used to switch to child Window with the help of URL.
	 * @param driver
	 * @param URL
	 */
	public void switchToChildWindow_UsingURL(WebDriver driver, String URL) {
		Set<String> windows = driver.getWindowHandles();
		for (String s : windows) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains(URL)) {
				break;
			}
		}
	}

	/**
	 * 
	 * @param driver
	 * @param parentWebpage_ID
	 */
	public void switchToParentWindow(WebDriver driver, String parentWebpage_ID) {
		driver.switchTo().window(parentWebpage_ID);
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitTill_ElementIsVisible(WebDriver driver , WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitTill_ElementIsClickable(WebDriver driver , WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitTill_TitleIsVisible(WebDriver driver , String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	/**
	 * 
	 * @param driver
	 * @param dd_Element
	 * @param index
	 */
	public void SelectDropdown_UsingIndex(WebDriver driver , WebElement dd_Element , int index) {
		Select s = new Select(dd_Element);
		s.selectByIndex(index);
	}
	
	/**
	 * 
	 * @param driver
	 * @param dd_Element
	 * @param text
	 */
	public void SelectDropdown_UsingVisibleText(WebDriver driver, WebElement dd_Element , String text) {
		Select s = new Select(dd_Element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * 
	 * @param driver
	 */
	public void HandleAlertPopupAndClickCancel(WebDriver driver) {
	    driver.switchTo().alert().dismiss();
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public String HandleAlertPopupAndFetchText(WebDriver driver) {
	    String text = driver.switchTo().alert().getText();
	    return text;
	}

	
	/**
	 * 
	 * @param driver
	 * @param text
	 */
	public void HandleAlertPopupAndPassTheText(WebDriver driver, String text) {
	    driver.switchTo().alert().sendKeys(text);
	}

	
	/**
	 * 
	 * @param driver
	 * @param index
	 */
	public void switchToFrameByIndex(WebDriver driver, int index) {
	    driver.switchTo().frame(index);
	}

	
	/**
	 * 
	 * @param driver
	 * @param id_name
	 */
	public void switchToFrameByID_Name(WebDriver driver, String id_name) {
	    driver.switchTo().frame(id_name);
	}

	
	/**
	 * @Note : This method is used to Switch to Parent Frame.
	 * @param driver
	 * @param frameele
	 */
	public void switchToFrameByWebelement(WebDriver driver, WebElement frameele) {
	    driver.switchTo().frame(frameele);
	}

	
	/**
	 * @Note : This method is used to Switch to Parent Frame.
	 * @param driver
	 */
	public void switchToMainWebpageFromFrame(WebDriver driver) {
	    driver.switchTo().defaultContent();
	}
	
	
	/**
	 * @Note : This method is used to clear the Text field
	 * @param driver
	 * @param textfield
	 */
	public void clearTextField(WebDriver driver, WebElement textfield) {
		textfield.clear();
	}
	
	public String phoneFormat(String phno) {
		if (phno.endsWith(".0")) {
	        phno = phno.replace(".0", "");
	    } else if (phno.contains("E")) {
	        phno = new java.math.BigDecimal(phno).toPlainString();
	    }
		return phno;
	}

}
