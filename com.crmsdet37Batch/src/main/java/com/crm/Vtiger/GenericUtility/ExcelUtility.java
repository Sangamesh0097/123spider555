package com.crm.Vtiger.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author hi
 *
 */
public class ExcelUtility {

	/**
	 * to fetch the data from excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, FileNotFoundException, IOException {
		
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelpath));
		
		Sheet sheet = workbook.getSheet(sheetName);
		
		Row row = sheet.getRow(rowNum);
		
		Cell cell = row.getCell(cellNum);
		DataFormatter format = new DataFormatter();
		String value = format.formatCellValue(cell);
		return value;
	}
	/**
	 * to insert data inside excel
	 * @param SheetName
	 * @param rowNum
	 * @param celNum
	 * @param data
	 * @throws Throwable
	 */

	public void insertIntoExcel(String SheetName, int rowNum, int cellNum, String data) throws Throwable
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelpath));
		Sheet sheet = workbook.getSheet(SheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fout = new FileOutputStream(IConstants.excelpath);
		workbook.write(fout);
	}
	/**
	 * to get the last used row number from excel
	 * @param SheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws Throwable 
	 * @throws FileNotFoundException 
	 */

	public int getLastRowNumFromExcel(String SheetName) throws EncryptedDocumentException, FileNotFoundException, Throwable
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelpath));
		Sheet sheet = workbook.getSheet(SheetName);
		int row = sheet.getLastRowNum();
		return row;
	}
		
	}

