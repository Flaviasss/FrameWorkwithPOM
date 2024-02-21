package com.SalesForce.Pages.Home;

import com.SalesForce.Pages.Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	@FindBy(xpath ="//*[@id=\"setupComponent\"]/div[1]/div/div[1]/div[2]/div/div[2]/h1/span") WebElement SalesForce;

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	public String getPageTitle() {
		
		waitForVisibility(SalesForce, 0,"Homepage text", 2);
		String data= getTextFromElement( SalesForce,"Homepage text");
		System.out.println("text extracted from Home page="+ data);
		return data;
	}
	

}
