package com.kc.testcases;

import org.testng.annotations.Test;

import com.kc.pageObjects.ClearTripHomePage;
import com.kc.pageObjects.LoginPage;
import com.kc.utility.DataProviders;


public class TestCase2 extends BaseTest{

	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void doLogin(String username, String password, String browser) throws InterruptedException {
		
		openBrowser(browser);
		

		System.out.println( "From testcase 1 username is: "+ username +"password is: "+password);
		
		ClearTripHomePage homepage = new ClearTripHomePage();
		LoginPage login = homepage.clickLoginButton();
		login.doLogin(username, password);
		closeBrowser();
	}
}
