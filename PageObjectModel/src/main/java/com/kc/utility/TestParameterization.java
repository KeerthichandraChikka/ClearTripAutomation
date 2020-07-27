package com.kc.utility;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {
	
	
	@Test(dataProvider = "data")
	public void testData(HashMap<String,String> data) {
		
		System.out.println(data);
		
		System.out.println(data.get("RunMode"));
		
	}
	
	@DataProvider(name = "data")
	public Object[][] getData() {
		
		Object[][] data = null ; 
		Map<String, String> hashMap = null;
		
		
		ExcelReader excel = new ExcelReader(Constants.MASTER_XL_PATH);
		
		int totalRows = excel.getRowCount(Constants.TESTDATA_SHEET);
		
		System.out.println("Total No of rows; "+totalRows);
		
		
		int testCaseRownum = 1;
		
		String testCaseName = "OpenAccountTest";
		
		for(testCaseRownum =1; testCaseRownum < totalRows; testCaseRownum++) {
			
			String celldata = excel.getCellData(Constants.TESTDATA_SHEET, 0, testCaseRownum);
			
			if(celldata.equalsIgnoreCase(testCaseName)) {
			break;
			}
		}
		

		System.out.println("Testcase found at row: "+testCaseRownum);
		
		// checking total rows in a testcase
		
		
		System.out.println("Checking test rows");
		int dataStartRowNum = testCaseRownum + 2;
		
		int testRows = 0;
		
		while(!(excel.getCellData(Constants.TESTDATA_SHEET, 0, (dataStartRowNum+testRows)).equals(""))) {
			
		//	if(excel.getCellData(Constants.SUITE_SHEET, 0, (dataStartRowNum+testRows)).equals("")) 
				
			
		testRows ++;
			
		}
		
		System.out.println("test case rows are: "+testRows);
		int colCount = excel.getColCount(Constants.TESTDATA_SHEET, (dataStartRowNum-1));
		
		System.out.println(colCount);
		
		data = new Object[testRows][1];
		System.out.println("Object array size is "+ data.length);
		
		System.out.println("Printing data");
		
		
		System.out.println("DatastartRowNum is: "+(testCaseRownum+2) +"----" +" row iteration till row number: "+((dataStartRowNum-1)+testRows));
		
		
		int k = 0;
		for(int i = (testCaseRownum+2); i<= ((dataStartRowNum-1)+testRows); i++) {
				
			hashMap = new HashMap<String, String>();
			
			for(int j=0; j<colCount; j++) {
				
				hashMap.put(excel.getCellData(Constants.TESTDATA_SHEET, j, testCaseRownum+1),excel.getCellData(Constants.TESTDATA_SHEET, j, i));
			
			}
			data[k][0] = hashMap;
			++k;		
		}
		return data;
	}
	
	

}
