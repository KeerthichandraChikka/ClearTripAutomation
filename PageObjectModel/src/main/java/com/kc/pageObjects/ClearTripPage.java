package com.kc.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClearTripPage extends BasePage {
	
	
	@FindBy(className = "cleartripLogo") WebElement cleartripLogo;
	@FindBy(xpath = "(//a[@id='flashSaleEl-air'])[1]") WebElement flights;
	@FindBy(xpath = "//a[@id='flashSaleEl-hotel']") WebElement hotels;
	@FindBy(id = "localsNav") WebElement activities;
	@FindBy(xpath = "(//li[@class='trainsApp'])[2]/a") WebElement trains;
	@FindBy(xpath = "(//a[@id='flashSaleEl-air'])[2]/preceding-sibling::a/span") WebElement flightDeals;
	
	
	

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(cleartripLogo);
	}
	
	
	public FlightBookPage gotoFlightPage() {
		
		return (FlightBookPage) openPage(FlightBookPage.class);
		
	}
	
	public HotelBookPage gotoHotelBook() {
		return (HotelBookPage) openPage(HotelBookPage.class);
	}
	
	

}
