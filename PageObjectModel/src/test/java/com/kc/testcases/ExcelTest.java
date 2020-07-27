package com.kc.testcases;

import com.kc.utility.Constants;
import com.kc.utility.ExcelReader;

public class ExcelTest {

	public static void main(String[] args) {
		
		
		ExcelReader excel = new ExcelReader(Constants.MASTER_XL_PATH);
		
		int totalRows = excel.getRowCount(Constants.TESTDATA_SHEET);
		
		System.out.println(totalRows);
		
		
		int testCaseRownum = 1;
		
		String testCaseName = "OpenAccountTest";
		
		for(testCaseRownum =1; testCaseRownum < totalRows; testCaseRownum++) {
			
			String data = excel.getCellData(Constants.TESTDATA_SHEET, 0, testCaseRownum);
			
			if(data.equalsIgnoreCase(testCaseName)) {
			break;
			}
		}
		
		System.out.println(testCaseRownum);
		
		// checking total rows in a testcase
		
		
		System.out.println("Checking test rows");
		int dataStartRowNum = testCaseRownum + 2;
		
		int testRows = 0;
		
		while(!(excel.getCellData(Constants.TESTDATA_SHEET, 0, (dataStartRowNum+testRows)).equals(""))) {
			
		//	if(excel.getCellData(Constants.SUITE_SHEET, 0, (dataStartRowNum+testRows)).equals("")) 
				
			
		testRows ++;
			
		}
		
		System.out.println("test rows are: "+testRows);
		
		
		int colCount = excel.getColCount(Constants.TESTDATA_SHEET, dataStartRowNum);
		
		System.out.println(colCount);
		
		
		
		System.out.println("Printing data");
		
		for(int i = dataStartRowNum; i< dataStartRowNum+testRows; i++) {
			
			for(int j=0; j<colCount; j++) {
				String data = excel.getCellData(Constants.TESTDATA_SHEET, j, i);
				System.out.println("At row: "+i+" at Col: "+j+" is: "+data);
			}
		}
		
		

	}

}
