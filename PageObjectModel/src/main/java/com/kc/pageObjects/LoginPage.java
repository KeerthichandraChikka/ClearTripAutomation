package com.kc.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

	private WebDriver driver;

	@FindBy(id = "email")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "signInButton")
	WebElement loginButton;
	
	@FindBy(id = "userAccountNav")
	By userAccountNav;


	public LoginPage(WebDriver driver) {
		
		
		System.out.println("Login page constructor driver memory is: " + driver);

		this.driver = driver;
		System.out.println("driver memory is: "+this.driver);
		PageFactory.initElements(driver, this);
	}


	public ClearTripPage doLogin(String userName, String passWord) throws InterruptedException {


		driver.switchTo().frame("modal_window");
		System.out.println("Switched to login frame");

		username.clear();
		username.sendKeys(userName);

		password.clear();
		password.sendKeys(passWord);
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		List<WebElement>  frames = driver.findElements(By.xpath("//iframe[@id='modal_window']"));
		System.out.println(frames.size());
		
		if(frames.size()!=0) {
			
			Thread.sleep(3000);
			
		}
		
		driver.switchTo().defaultContent();
		
		
		
		
		return (ClearTripPage) openPage(ClearTripPage.class);
		

	}


	@Override
	protected ExpectedCondition getPageLoadCondition() {
		driver.switchTo().frame("modal_window");
		return ExpectedConditions.visibilityOf(username);
	}

}
