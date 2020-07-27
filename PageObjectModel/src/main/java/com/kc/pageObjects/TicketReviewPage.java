package com.kc.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kc.utility.DriverManager;

public class TicketReviewPage extends BasePage {
	
	public WebDriver driver;
	
	public TicketReviewPage() {
		driver = DriverManager.getDriver();
		
	}
	
	
	@FindBy(id = "itineraryOpen") WebElement paymentHome;
	@FindBy(xpath = "//div[@id ='bundlefare-fareBlock']/div/dl/dd/div/span[2]/label") WebElement insuranceRadio;
	@FindBy(xpath = "//div[@id='beforeBaggae']/span[2]/button") WebElement addBaggae;
	@FindBy(id = "modal_window") WebElement baggageFrame;
	@FindBy(xpath = "//section[@id='addonListContainer']/ul/li[2]/ul/li/a/span[text()='Excess Baggage of 3 KG']/following-sibling::span[2]/span[2]")
	WebElement baggageAddButton;
	@FindBy(xpath = "//input[@value='Done']") WebElement baggageDone;
	@FindBy(xpath = "//div[@id='beforeMeals']/div[2]/button") WebElement meals;
	@FindBy(xpath = "(//div[@id='beforeMeals'])[2]/div/div[2]/button") WebElement seatPreference;
	@FindBy(id = "insurance_box") WebElement insuranceCheck;
	@FindBy(id = "coupon") WebElement coupon;
	@FindBy(id = "check_saving") WebElement couponApply;
	@FindBy(id = "itineraryBtn") WebElement travellsButton;
	

	@FindBy(id = "AdultTitle1") WebElement nameTitle;
	@FindBy(xpath = "(//input[@id='AdultFname1'])[1]") WebElement travellerFirstname;
	@FindBy(xpath = "(//input[@id='AdultLname1'])[1]") WebElement travellerLastname;
	@FindBy(xpath = "(//input[@id='mobileNumber'])[1]") WebElement travellerMobileNumber;
	@FindBy(id = "travellerBtn") WebElement bookContinue;
	
	@FindBy(id = "CCTab") WebElement creditCard;
	@FindBy(id = "DCTab") WebElement bebitCard;
	@FindBy(id = "NBTab") WebElement netBanking;
	@FindBy(xpath = "//li[@formid='exwallet']") WebElement wallets;
	@FindBy(id = "UPTab") WebElement upi;
	
	
	@FindBy(id = "creditCardNumberDisp") WebElement ccNumber;
	@FindBy(xpath = "(//select[@id='CcExpirationMonth'])[2]") WebElement expMonth;
	@FindBy(id = "CcExpirationYear") WebElement expYear;
	@FindBy(xpath = "(//input[@id='BillName'])[2]") WebElement cardHolder;
	@FindBy(id = "cvvCode") WebElement cvv;
	@FindBy(id = "paymentSubmit") WebElement paymentSubmit;

	boolean insurance = true;
	boolean baggage = true;
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(paymentHome);
	}
	
	public void itinerary() {

		if(insurance) 
			insuranceRadio.click();
		if(baggage) {
			if(addBaggae.isDisplayed()) {
			addBaggae.click();
			driver.switchTo().frame(baggageFrame);
			Actions actions = new Actions(driver);
			actions.moveToElement(baggageAddButton).click().build().perform();
			baggageDone.click();
			driver.switchTo().defaultContent();
			}
		}
		
		travellsButton.click();
		
		
	}
	
	public void travellers() {
		
		Select title = new Select(nameTitle);
		title.selectByVisibleText("Mr");
		travellerFirstname.clear();
		travellerFirstname.sendKeys("Keerthichandra");
		travellerLastname.clear();
		travellerLastname.sendKeys("Chikka");
		travellerMobileNumber.clear();
		travellerMobileNumber.sendKeys("9160375868");
		
		bookContinue.click();
		
	}
	
	public void payment() {
		
		creditCard.click();
		ccNumber.click();
		ccNumber.clear();
		ccNumber.sendKeys("12345678899");
		
		Select exMonth = new Select(expMonth);
		exMonth.selectByVisibleText("01");

		WebElement exYear = driver.findElement(By.xpath("//div[@id='CCTab']/dl/dd[2]/select[2]"));
		new WebDriverWait(driver, 15).until(
		            ExpectedConditions.elementToBeClickable(exYear));
		exYear.click();
		Select expY = new Select(exYear);
		expY.selectByVisibleText("2021");
		exYear.sendKeys(Keys.TAB);
		
		cardHolder.clear();
		cardHolder.sendKeys("Keerthichandra");
		
		cvv.sendKeys("123");
		paymentSubmit.click();
	}

}
