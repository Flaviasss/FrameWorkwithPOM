package com.SalesForce.base;

import java.io.File;



import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
// getTitle, switchToAlert acceptAlert DismissAlert getTextFromAlert
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.SalesForce.Pages.Base.BasePage;
import com.SalesForce.Utilities.Constants;
import com.SalesForce.Utilities.ExtentReportsUtility;
import com.SalesForce.Utilities.PropertiesUtility;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;	


	public class BaseTest extends BasePage{
		
		public BaseTest(WebDriver driver) {
			super(null);
			
		}

		

		protected static WebDriver driver = null;
		protected ExtentReportsUtility extentReport= ExtentReportsUtility.getInstance();
		
		protected Logger  BaseTestlog=LogManager.getLogger();
		
		@BeforeMethod
		@Parameters("browserName")
		public void SetupBeforeMethod(@Optional("edge") String name) {
			BaseTestlog.info("...............Before method SetupBeforeMethod started......");
			launchBrowser(name);
			String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"url");
			goToUrl(url);
			
		}
		 @AfterMethod
		public void TeardownAfterMethod() {
			 closeBrowser();
			 BaseTestlog.info("............TeardownAfterMethod Executed");
			 
		 }
		 
		
		

		public void launchBrowser(String browserName) {

			switch (browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				BaseTestlog.info("browser instance chrome created.");
				driver.manage().window().maximize();
				BaseTestlog.info("window is maximized");
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				break;

			case "opera":
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				driver.manage().window().maximize();
				break;

			case "safari":
				// Safari does not require a separate driver setup, as it is included with macOS
				driver = new SafariDriver();
				break;

			default:
				BaseTestlog.info("Unsupported browser: " + browserName);
			}

			// return driver;
		}
		
		public void goToUrl(String url) {
			driver.get(url);
			BaseTestlog.info(url + "is entered");
			
		}
		public void closeBrowser() {
			driver.close();
			BaseTestlog.info("browser instance closed");
			driver=null;
		}
		
		public void quitBrowser() {
			driver.quit();
			BaseTestlog.info("all browser closed");
			driver=null;
			
		}
		
		
		public void takescreenshot(String filepath) {
			 TakesScreenshot screenCapture=(TakesScreenshot)driver;
			 File src=screenCapture.getScreenshotAs(OutputType.FILE);
			 File destination=new File(filepath);
			 try {
				Files.copy(src, destination);
				BaseTestlog.info("captured the screen");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				BaseTestlog.error("Unable to capture the screen", e);
				
			}
		}
		
		public void takescreenshot(WebElement element,String filepath) {
			 File src=element.getScreenshotAs(OutputType.FILE);
			 File destination=new File(filepath);
			 try {
				Files.copy(src, destination);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				BaseTestlog.info("captured the screen");
				
			}
		}
	}


