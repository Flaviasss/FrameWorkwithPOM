package com.SalesForce.automationtests;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.SalesForce.Pages.Home.HomePage;
import com.SalesForce.Pages.Login.LoginPage;
import com.SalesForce.Utilities.Constants;
import com.SalesForce.Utilities.PropertiesUtility;
import com.SalesForce.base.BaseTest;



public class AutomationScripts extends BaseTest{
	
	public AutomationScripts() {
		super(driver);
	
	}

	protected Logger AutomationScriptslog=LogManager.getLogger();
	
	
	/* TC1:Navigate to SFDC */
	public void NavigationTOSFDC() throws InterruptedException {
		AutomationScriptslog.info("*******************Automation Started*********************");
		Thread.sleep(5000);
		 LoginPage loginPage= new LoginPage(driver);
		 loginPage.goToUrl(getPageTitle());
		 loginPage.setSearchContext(driver);
		 Thread.sleep(5000);
		 String  expectedTitle= "SalesForce";
		 String actualTitle= "SalesForce";


	     driver.get("https://tekarch151-dev-ed.develop.my.salesforce.com/");
	        Assert.assertEquals(actualTitle,  expectedTitle);
	        AutomationScriptslog.info("*******************Automation completed*********************");    
	        
		
	}
	
	/* TC2:Login to SFDC */
	@Test
	
	public void success_login() throws InterruptedException {
		
		AutomationScriptslog.info("*******************Automation Started*********************");
		String expected="Sales Force Home page";
		
		String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		extentReport.logTestInfo("username and password extracted from properties file ");
	
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enteruserName(userName);
		loginPage.enterPassword(passWord);
		driver=loginPage.clickLoginButton();
		
		HomePage homePage=new HomePage(driver);
		String actual=homePage.getPageTitle();
		
		Assert.assertEquals(actual, expected);
		AutomationScriptslog.info("*******************Automation completed*********************"); 
	}
	
	/* TC3: Test the remember username check box */
	 public void RememberUsername() throws InterruptedException {
		 AutomationScriptslog.info("*******************Automation Started*********************");
		 String expected= "SalesForce";
		 String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		 String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		 extentReport.logTestInfo("username and password extracted from properties file ");
		 
		 
			LoginPage loginPage= new LoginPage(driver);
			loginPage.enteruserName(userName);
			loginPage.enterPassword(passWord);
			loginPage.rememberMeCheckbox();
			driver=loginPage.clickLoginButton();
			loginPage.logout();
			
			driver.get("https://tekarch151-dev-ed.develop.my.salesforce.com/");
			String actual= "SalesForce";
			Assert.assertEquals(actual, expected);	
			AutomationScriptslog.info("*******************Automation completed*********************"); 
			
	 }
	 
	 /*TC4A :Test forgot password */
	public void ForgotPassword() throws InterruptedException {
		AutomationScriptslog.info("*******************Automation Started*********************");
		
		String expectedError= "Error: Password is invalid";
		
		String userName=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"username");
		extentReport.logTestInfo("username and password extracted from properties file ");
			
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enteruserName(userName);
		loginPage.enterPassword("");
		driver=loginPage.clickLoginButton();
		
		Alert a=loginPage.switchtoErrorAlert();
		
		String actualError=loginPage.extractTextFromElement(a);
		Assert.assertEquals(actualError, expectedError);
		AutomationScriptslog.info("*******************Automation completed*********************"); 
	}
	
	/* TC4B: Enter Wrong UsarName and Password */
	public void EnterWrongUsernameandPassword() throws InterruptedException {
		AutomationScriptslog.info("*******************Automation Started*********************");
		
		String expectedError= "Error: Wrong Username and Wrong Password";
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enteruserName("abc");
		loginPage.enterPassword("123");
		driver=loginPage.clickLoginButton();
		
		Alert a=loginPage.switchtoErrorAlert();
		
		String actualError=loginPage.extractTextFromElement(a);
		Assert.assertEquals(actualError, expectedError);
		AutomationScriptslog.info("*******************Automation completed*********************"); 
	}
	

	
}

