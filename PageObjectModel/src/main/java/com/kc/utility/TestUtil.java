package com.kc.utility;

public class TestUtil {

	public static boolean isSuiteRunnable(String suiteName, ExcelReader excel) {

		boolean result = false;

		int rows = excel.getRowCount(Constants.SUITE_SHEET);

		for( int i=1; i<rows ; i++) {
			String data = 	excel.getCellData(Constants.SUITE_SHEET, Constants.SUITENAME_COL, i);

			if(data.equalsIgnoreCase(suiteName)) {
				
				String runMode = excel.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, i);
				
					if(runMode.equalsIgnoreCase("Y")) {
						result = true;
					}
					else {
						result = false;
					}
			}
				
		}
		return result;	

	}

	public static boolean isTestRunnable(String testCaseName, ExcelReader excel) {
		
		boolean result = false;

		int rows = excel.getRowCount(Constants.TESTCASE_SHEET);

		runmode :
		for( int i=1; i<rows ; i++) {
			String data = 	excel.getCellData(Constants.TESTCASE_SHEET, Constants.TESTCASE_COL, i);

			if(data.equalsIgnoreCase(testCaseName)) {
				
				String runMode = excel.getCellData(Constants.TESTCASE_SHEET, Constants.RUNMODE_COL, i);
				System.out.println("Runmode at test util: "+runMode);
					if(runMode.equalsIgnoreCase("Y")) {
						result = true;
						break runmode;
					}
					else {
						result = false;
					}
			}
				
		}
		return result;	



	}

}
