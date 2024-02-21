package com.SalesForce.Pages.Login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.SalesForce.base.BaseTest;

public class LoginPage extends BaseTest {
	
	
	@FindBy(xpath="//input[@name='username']")WebElement username;
	@FindBy(xpath="//input[@name='pw']")WebElement password;
	@FindBy(xpath="//input[@id='Login']")WebElement loginButton;
	@FindBy(xpath="//input[@class='r4 fl mr8']")WebElement rememberMeCheckbox ;
	@FindBy(className="logout") WebElement logout;
	@FindBy(xpath="a//*[@id='forgot_password_link']")WebElement forgotpassword;
	@FindBy(id="un")WebElement forgotpasswordusername;
    @FindBy(id="continue")WebElement proceed;


	@FindBy(xpath="//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]")WebElement ViewProfile;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	
	public void enteruserName(String data){
		enterText(username,"sowjanya@tekarch.com","username");
		extentReport.logTestInfo("username is Enter in the username field");
		
	}
	
	public void enterPassword(String data){
		enterText(password,"Sudeep@17!","password");
		extentReport.logTestInfo("Password is Enter in the password field");
		
	}
	
	public  WebDriver clickLoginButton(){
		
		clickElement(loginButton, "loginbutton");
		return driver;
		
		
	}
	

		public void rememberMeCheckbox() {
		rememberMeCheckbox.click();  
		}
		
		public void logout() {
			logout.click();
		}

		 
		public void selectForgotPassword(String strforgotpassword) {	   
		forgotpassword.click();
		}	
		
		public void forgotPasswordUserName(String strforgotpasswordusername) {	   
		forgotpasswordusername.sendKeys(strforgotpasswordusername); 
		}	
		    
			public void clickonContinueButton(String strproceed) {	   
			proceed.click();  	
			
		}
			


	
	public String getTitleofThePage() {
		
		return getPageTitle();
		
	}
	
	public Alert switchtoErrorAlert() {
		
		return switchToAlert();
	}
	public Alert switchToAlert() {

		Alert alert = driver.switchTo().alert();
		BaseTestlog.info("switched to alert");
		return alert;
	}
	
	public String extraTextFromElement(Alert a) {
		
		return getAlertText(a, "Login Error Alret");
		
	}
		
	public void acceptErrorAlret(Alert a) {
		
		AcceptAlert(a);
		
	}


	public String extractTextFromElement(Alert a) {
		// TODO Auto-generated method stub
		return null;
	}


	public void setSearchContext(WebDriver driver) {
		
		
	}
	
	 }


