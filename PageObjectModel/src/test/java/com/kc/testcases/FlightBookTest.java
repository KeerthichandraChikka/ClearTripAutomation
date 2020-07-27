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

public class FlightBookTest extends BaseTest {
	
	
	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void flightBookTest(HashMap<String, String> data) throws InterruptedException {
		
		openBrowser(data.get("browser"));
		ExcelReader excel = new ExcelReader(Constants.MASTER_XL_PATH);
		DataUtil.checkExecution("master", "FlightBookTest", data.get("RunMode"), excel);
		System.out.println("Runmodes checked successfully");
		
		ClearTripHomePage homepage = new ClearTripHomePage().open("https://www.cleartrip.com/");
		LoginPage login = homepage.clickLoginButton();
		String username = data.get("username");
		String password = data.get("password");
		ClearTripPage appPage = login.doLogin(username, password);
		
		//Thread.sleep(5000);
		FlightBookPage fbp = appPage.gotoFlightPage();
		FlightTicketPage ftp = fbp.enterTravelDetails();
		TicketReviewPage trp = ftp.bookTicket();
		trp.itinerary();
		trp.travellers();
		trp.payment();
		
		closeBrowser();
		
	}
	
	
	
	
	
	

}
