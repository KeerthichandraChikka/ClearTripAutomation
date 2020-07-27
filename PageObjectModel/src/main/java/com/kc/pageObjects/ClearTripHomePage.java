package com.kc.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.kc.utility.DriverManager;

public class ClearTripHomePage extends BasePage{
	
	public WebDriver driver;


	@FindBy(id = "userAccountLink" )
	WebElement yourTrips;
	
	@FindBy(id = "SignIn")
	WebElement signIn;
	
	
	public ClearTripHomePage open(String url) {
		driver = getDriver();
		driver.get(url);		
		return (ClearTripHomePage)openPage(ClearTripHomePage.class);
	}
	
	public LoginPage clickLoginButton() {
		
		yourTrips.click();
		signIn.click();
		
		return new LoginPage(getDriver());
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(yourTrips);
	}
	
	
	public WebDriver getDriver() {
		System.out.println("From clear trip home page getDriver() driver memory is: "+driver);
		return DriverManager.getDriver();
	}
	
}
