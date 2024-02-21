package com.SalesForce.Pages.Base;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SalesForce.Utilities.ExtentReportsUtility;




public class BasePage {
	
	public WebDriver driver=null;
	protected Wait<WebDriver> wait= null;
	protected Logger BasePagelog=LogManager.getLogger();
	protected ExtentReportsUtility extentReport= ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			BasePagelog.info("username data is entered in " + objectName + " textbox");
		} else {
			BasePagelog.info(objectName + " element is not displayed");
		}
	}
	
		public void clickElement(WebElement ele, String objectName) {
			if (ele.isEnabled()) {
				ele.click();
				BasePagelog.info(objectName + "button is clicked");
				
			} else {
			
				BasePagelog.info(objectName+" element is not enabled");
				
			}
		}
		public String getTextFromElement(WebElement ele, String objectName) {
			String data = ele.getText();
			BasePagelog.info("text is extracted from "+objectName);
			return data;
		}
		public void maximiseBrowser() {
			driver.manage().window().maximize();
			//BasePagelog.info("browser window has maximized");
		}

		public String getPageTitle() {
			return driver.getTitle();
		}

		public void refreshPage() {
			driver.navigate().refresh();
			BasePagelog.info("page is refreshed");

		}
		public void waitForAlertPresent(int time) {
			BasePagelog.info( "waited for alert to display ");
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.alertIsPresent());
		}

		public void switchToNewWindowFrom(String currentWindowHandle) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String handle : allWindowHandles) {
				if (!currentWindowHandle.equalsIgnoreCase(handle))
					driver.switchTo().window(handle);
			}
			BasePagelog.info("switched to new window");
		}
		public void waitUntilElementToBeClickable(By locator, String objName, WebDriverWait wait) {
			BasePagelog.info("waiting for an web element " + objName + " to be clickable");
			wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public void waitForVisibility(WebElement ele, int time, String objectName, WebDriverWait wait) {
			BasePagelog.info(objectName + " is waited for visibility ");
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		public void waitUntilPageLoads() {
			BasePagelog.info("waiting until page loads with 30 sec maximum");
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		}
		public void waitForVisibility(WebElement ele, int time, String objectName, long pollingtime) {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait
			.withTimeout(Duration.ofSeconds(time))
			.pollingEvery(Duration.ofSeconds(pollingtime))
			.ignoring(ElementNotInteractableException.class)
			.withMessage(objectName+" is not visible.fluent wait time expires");

			wait.until(ExpectedConditions.visibilityOf(ele));
			BasePagelog.info(objectName + " is waited for visibility using fluent wait");
		}

		public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName, WebDriverWait wait) {
			BasePagelog.info("waiting for an web element " + objName + " for its visibility");
			wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		}
		public void clearElement(WebElement ele, String ObjectName) {
			if (ele.isDisplayed()) {
				ele.clear();
				BasePagelog.info(ObjectName + " is cleared");
			} else {
				BasePagelog.info(ObjectName + " element is not displayed");
			}
		}

		

		// ****************handling alerts reusable methods*************************

		public Alert switchToAlert() {

			Alert alert = driver.switchTo().alert();
			BasePagelog.info("switched to alert");
			return alert;
		}

		public void AcceptAlert(Alert alert) {

			BasePagelog.info("Alert accepted");
			alert.accept();

		}

		public String getAlertText(Alert alert, String objectname) {
			BasePagelog.info("etracting text in the " + objectname + "alert");
			String text = alert.getText();
			BasePagelog.info("text is extracted from alert box is==" + text);
			return text;

		}

		public void dismisAlert() {

			Alert alert = switchToAlert();
			alert.dismiss();
			BasePagelog.info("Alert dismissed");

		}
		// ******************************alert ends**************************************

		// ******************************Action class reusable methods**************************************
		

		public void mouseOverOnElement(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			BasePagelog.info(" cursor moved to web element " + objName);
		}

		public void ContextClickOnElement(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.contextClick(ele).build().perform();
			BasePagelog.info("right click persormed on web element " + objName);
		}

		// ******************************Action class reusable method ends**************************************

		// ******************************Select class reusable method starts*************************************

		public void selectByTextData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByVisibleText(text);
			BasePagelog.info(objName + " selected " + text);

		}

		public void selectByIndexData(WebElement element, int index, String objName) {
			waitForVisibility(element, 5, objName);
			Select selectCity = new Select(element);
			selectCity.selectByIndex(index);
			BasePagelog.info(objName + " selected with index=" + index);

		}

		protected void waitForVisibility(WebElement element, int i, String objName) {
			// TODO Auto-generated method stub
			
		}
		public void selectByValueData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByValue(text);
			BasePagelog.info(objName + " selected ");
		}
		
		public WebElement selectFromListUsingText(List<WebElement> list, String text) {
			WebElement element = null;
			for (WebElement i : list) {
				if (i.getText().equalsIgnoreCase(text)) {
					BasePagelog.info("selected=" + i.getText());
					element = i;
					break;
				}

			}
			return element;

		}
		

		
}

	

	

