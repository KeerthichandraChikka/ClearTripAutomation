package com.kc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public  String filePath;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;



	public ExcelReader(String filePath) {
		this.filePath = filePath;
		try {

			fis = new FileInputStream(new File(filePath));
			//Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(fis);
			//	FileInputStream fis = new FileInputStream(filePath);
			//	workbook = new XSSFWorkbook(fis);
			//sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}



	public int getRowCount(String sheetName) {

		System.out.println( sheetName);
		int rowCount = 0;	
		sheet = workbook.getSheet(sheetName);
		rowCount = sheet.getLastRowNum();
		rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows are: "+rowCount);

		return rowCount;
	}

	public  int getColCount(String sheetName) {
		int colCount = 0;
		sheet = workbook.getSheet(sheetName);
		colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Number of columns are: "+colCount);
		return colCount;
	}

	public int getColCount(String sheetName, int rowNum) {

		System.out.println("Row Number: "+rowNum);

		int colCount =0;

		sheet = workbook.getSheet(sheetName);
		colCount = sheet.getRow(rowNum).getPhysicalNumberOfCells();
		return colCount;

	}



	public  String getCellDataString(int rowNum, int colNum) {
		String cellData = null;

		cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();


		return cellData;
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum){
		try{
			if(rowNum <=0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if(index==-1)
				return "";


			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(colNum);
			if(cell==null)
				return "";

			CellType type = cell.getCellType();

			if(type==null)
				return "";

			if(type == CellType.STRING)
				return cell.getStringCellValue();
			else if(type ==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){

				String cellText  = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					double d = cell.getNumericCellValue();

					Calendar cal =Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText =
							(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
							cal.get(Calendar.MONTH)+1 + "/" + 
							cellText;

				}
				return cellText;

			}
			//return cellText;
			else if(cell.getCellType()==CellType.BLANK)
				return ""; 
			else 
				return String.valueOf(cell.getBooleanCellValue());

		}


		catch(Exception e){

			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}

	}





	public String getCellData(String sheetName, String colName, int rowNum){
		System.out.println("Workbook is "+workbook+" Sheet Name is: "+sheetName +" colName is "+colName+" rowNum is "+rowNum );
		try{
			if(rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			if(index == -1)
				return "";

			sheet = workbook.getSheet(sheetName);
			int col_Num = -1;

			row = sheet.getRow(0); // first row (header row)
			System.out.println("First row is reading");

			for(int i=0; i <=row.getLastCellNum()-1; i++){
				System.out.println("ColName: "+row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					col_Num = i; // TCID column number
					System.out.println(colName+" column is found");
					System.out.println("Column Number is: "+col_Num);
					break;
				}
			}
			if(col_Num == -1)
				return "";

			sheet = workbook.getSheet(sheetName);
			System.out.println("Reading row: "+(rowNum-1));
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";

			cell = row.getCell(col_Num);
			CellType type = cell.getCellType();

			if(type==null)
				return "";

			if(type == CellType.STRING)
			return cell.getStringCellValue();

			else if(type ==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){

				String cellText  = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					double d = cell.getNumericCellValue();

					Calendar cal =Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText =
							(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
							cal.get(Calendar.MONTH)+1 + "/" + 
							cellText;

				}
				return cellText;

			}else if(cell.getCellType()==CellType.BLANK)
				return ""; 
			else 
				return String.valueOf(cell.getBooleanCellValue());

		}
		catch(Exception e){

			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}

}
