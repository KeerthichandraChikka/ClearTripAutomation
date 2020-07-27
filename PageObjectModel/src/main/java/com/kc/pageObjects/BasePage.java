package com.kc.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kc.utility.DriverManager;

public abstract class BasePage<T> {
	
	
	private WebDriver driver;
	
	public BasePage() {
		driver = DriverManager.getDriver();
		
	}
	

	public  T openPage(Class<T> clazz) {
		
		T page = null;
		AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
		page = PageFactory.initElements(driver, clazz);
		PageFactory.initElements(ajax, page);
		ExpectedCondition<?> pageLoadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(pageLoadCondition);
		
		return page;
	}
	
	
	private void waitForPageToLoad(ExpectedCondition pageLoadConidition ) {
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(pageLoadConidition);
	}
	
	
	protected abstract ExpectedCondition getPageLoadCondition();
	
}
