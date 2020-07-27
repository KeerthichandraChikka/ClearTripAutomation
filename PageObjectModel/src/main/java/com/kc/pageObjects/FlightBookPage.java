package com.kc.pageObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kc.utility.DriverManager;


public class FlightBookPage extends BasePage {
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public  FlightBookPage() {
		
		driver = DriverManager.getDriver();
	}
	
	@FindBy(xpath = "(//a[@href='/flights'])[2]")
	WebElement flights; 
	
	@FindBy(id = "OneWay")
	WebElement oneway;
	
	@FindBy(id = "FromTag")
	WebElement from;
	
	@FindBy(id = "ToTag")
	WebElement to;
	
	@FindBy(id = "DepartDate")
	WebElement departDate;
	
	@FindBy(id ="Adults") WebElement adults;
	@FindBy(id = "Childrens") WebElement childs;
	@FindBy(id = "Infants") WebElement infants;
	
	@FindBy(id = "ui-datepicker-div") WebElement datePickerCalender;
	@FindBy(xpath = "//div[@id='ui-datepicker-div']/div[1]/div/div/span[1]") WebElement datePickerMonth;
	@FindBy(xpath = "(//div[@id='ui-datepicker-div']/div/div/div/span)[2]") WebElement datePickerYear;
	@FindAll({@FindBy(xpath = "(//table[@class='calendar'])[1]/tbody/tr/td[@data-handler='selectDay']")})
	public List<WebElement> days;
	
	
	@FindBy(xpath = "//div[@class='monthBlock first']/div/a") WebElement prev;
	@FindBy(xpath = "//div[@class='monthBlock last']/div/a ") WebElement next; 
	
	@FindBy(id = "SearchBtn") WebElement searchFlights;
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {
		
		return ExpectedConditions.visibilityOf(flights);
	}
	
	public FlightTicketPage enterTravelDetails() {
		
		System.out.println("Driver memory is: "+driver);
		
		Actions actions = new Actions(driver);
		actions.moveToElement(oneway).click().build().perform();

		wait = new WebDriverWait(driver,5);
		WebElement ele;
		
		from.clear();
		from.sendKeys("HYD");
		String fromelement = "(//ul[@id='ui-id-1']/li)[1]";
		ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fromelement)));
		ele.click();
		
		to.clear();
		to.sendKeys("PNQ");
		String toelement = "(//ul[@id='ui-id-2']/li)[1]";
		ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toelement)));
		ele.click();
		
		departDate.click();
		datePicker();
		
		Select adult = new Select(adults);
		adult.selectByVisibleText("1");
		
		Select child = new Select(childs);
		child.selectByVisibleText("0");
		
		Select infant = new Select(infants);
		infant.selectByVisibleText("0");
		
		actions.moveToElement(searchFlights).click().build().perform();
		
	//	searchFlights.click();
		
		return (FlightTicketPage) openPage(FlightTicketPage.class);
	}
	
	
	public void datePicker() {
		
		System.err.println("Date Picker method");
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("d-MMMM-yyyy");
		
		c.setTime(d);
		c.add(Calendar.DATE, 19);
		String output = sdf.format(c.getTime());
		
		String[] dates = output.split("-");
		String day = dates[0];
		String month = dates[1];
		String year = dates[2];
		
		
		System.out.println(day +" "+month+" "+year );
		System.out.println(datePickerYear.getText());
		System.out.println(datePickerMonth.getText());
		
		while(!datePickerYear.getText().equals(year)) {
			next.click();
		}
		
		System.out.println("Datepicker month is: "+datePickerMonth.getText());
		while(!datePickerMonth.getText().equals(month)) {
			next.click();
		}
		
		
		
		for(WebElement date : days) {
			System.out.println(date.getText());
			if(date.getText().equals(day)) {
				date.click();
				break;
			}	
		}
	}

}
