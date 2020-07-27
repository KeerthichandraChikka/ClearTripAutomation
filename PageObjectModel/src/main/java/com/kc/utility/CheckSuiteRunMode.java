package com.kc.utility;

public class CheckSuiteRunMode {
	
	public static void main(String[] args) {
		
		String suiteName = "BankManager";
		
		boolean suiteResult = TestUtil.isSuiteRunnable(suiteName, new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/testdata/Suite.xlsx"));
		System.err.println(suiteResult);
		
		String testcase = "LoginTest";
		
		boolean testResult = TestUtil.isTestRunnable(testcase, new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/testdata/master.xlsx"));
		System.out.println("Test case run mode is: "+testResult);
	}
	

}
