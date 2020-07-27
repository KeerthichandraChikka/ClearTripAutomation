package com.kc.testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.kc.utility.Constants;
import com.kc.utility.DataProviders;
import com.kc.utility.DataUtil;
import com.kc.utility.ExcelReader;

public class AddCustomerTest {
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="bankManager")
	public void loginTest(HashMap<String,String> data){
		
		/*
		 * Suite
		 * TestCase
		 * Data
		 * 
		 */
		
		
		ExcelReader excel = new ExcelReader(Constants.MASTER_XL_PATH);
		DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"), excel);
	
	}
}
