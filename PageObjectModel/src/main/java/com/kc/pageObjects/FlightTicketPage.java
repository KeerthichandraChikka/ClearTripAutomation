package com.kc.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlightTicketPage extends BasePage {
	
	@FindBy (xpath = "(//form[@class='filtersForm'])[2]/div/div[6]") WebElement airlines;
	@FindBy (xpath = "(//form[@id='flightForm'])[2]/section[2]/div[4]/div/nav/ul/li[1]/table/tbody[2]/tr[2]/td[last()]/button") WebElement book;

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(airlines);
	}
	
	public TicketReviewPage bookTicket() {
		book.click();
		
		return (TicketReviewPage) openPage(TicketReviewPage.class);
		
	}

}
