package com.kc.testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.kc.pageObjects.ClearTripHomePage;
import com.kc.pageObjects.ClearTripPage;
import com.kc.pageObjects.FlightBookPage;
import com.kc.pageObjects.FlightTicketPage;
import com.kc.pageObjects.LoginPage;
import com.kc.pageObjects.TicketReviewPage;
import com.kc.utility.Constants;
import com.kc.utility.DataProviders;
import com.kc.utility.DataUtil;
import com.kc.utility.ExcelReader;

public class LoginTest extends BaseTest{
	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void loginTest(HashMap<String, String> data) throws InterruptedException {
		
		System.out.println("HashMap data is: "+data);
		System.out.println("Browser name is: "+data.get("browser")+" ,"+ " Username is: "+data.get("username")+", "+" Password is: "+data.get("password"));
		openBrowser(data.get("browser"));
		ExcelReader excel = new ExcelReader(Constants.MASTER_XL_PATH);
		DataUtil.checkExecution("master", "LoginTest", data.get("RunMode"), excel);
		System.out.println("Runmodes checked successfully");
		
		ClearTripHomePage homepage = new ClearTripHomePage().open("https://www.cleartrip.com/");
		LoginPage login = homepage.clickLoginButton();
		login.doLogin(data.get("username"), data.get("password"));
	
	//	closeBrowser();
	}
	
}
