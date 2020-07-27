package com.kc.practice;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Webtable_Values_Excel {

	public WebDriver driver = null;
	String[][] tableVal;
	int rowCount, columnCount;

	@BeforeSuite

	public void init() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/web-table-element.php");
	}

	@Test
	public void webtable_read() {

		List<WebElement> rows = driver.findElements(By.xpath("//div[@id='leftcontainer']//table/tbody/tr"));
		List<WebElement> cols = driver.findElements(By.xpath("//div[@id='leftcontainer']//table/thead/tr/th"));

		rowCount = rows.size();
		columnCount = cols.size();

		System.out.println("Rowcount is: " + rowCount + "; Col Count: " + columnCount);

		tableVal = new String[rowCount][columnCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 1; j <= columnCount; j++) {

				if (i == 1) {
					// get headers
					String header = driver.findElement(By.xpath("//table[@class='dataTable']/thead/tr/th[" + j + "]"))
							.getText();
					tableVal[i - 1][j - 1] = header;
					System.out.println("Header value at " + i + " row:  and " + j + " coloumn is: " + header);
				} else {

					String cellData = driver
							.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[" + i + "]/td[" + j + "]"))
							.getText();
					tableVal[i-1][j-1] = cellData;
					System.out.println(driver
							.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[" + i + "]/td[" + j + "]"))
							.getText());

				}
			} // for j

		} // for i

		// FileInputStream fis = null;
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Wirte_Webtable_Data");

		int rowNum = 0;
		System.out.println("Creating excel");

		for (Object[] datatype : tableVal) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : datatype) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
 					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(
					System.getProperty("user.dir") + "/src/test/resources/testdata/Write_Excel.xlsx");
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Done");

	} // webtable_read().

	@AfterSuite
	public void terminate() {

		driver.close();
	}
	
	

}
