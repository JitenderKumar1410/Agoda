package com.agoda.framework.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.agoda.framework.base.baseStaticValue;

/**
 * ExcelUtil class contains all of the necessary operations of excel like get
 * and set value in excel column, read excel file and so on.
 * 
 * @author jitenderkumar01
 *
 */
public class ExcelUtil {

	/**
	 * ReadExcel is used to read the excel and provide the data in form of object.
	 * 
	 * @return Object[][]
	 * @throws IOException
	 */
	@DataProvider
	protected static Object[][] ReadExcel() throws IOException {

		baseStaticValue.fileInputStream = new FileInputStream(baseStaticValue.ExcelPath); // Excel sheet file location
		baseStaticValue.workbook = new XSSFWorkbook(baseStaticValue.fileInputStream); // get my workbook
		baseStaticValue.worksheet = baseStaticValue.workbook.getSheet(baseStaticValue.SheetNumber);// get my sheet from
																									// workbook
		baseStaticValue.row = baseStaticValue.worksheet.getRow(0); // get my Row which start from 0

		int RowNum = baseStaticValue.worksheet.getPhysicalNumberOfRows();// count my number of Rows
		int ColNum = baseStaticValue.row.getLastCellNum(); // get last ColNum

		Object Data[][] = new Object[RowNum - 1][ColNum]; // pass my count data in array

		for (int i = 0; i < RowNum - 1; i++) // Loop work for Rows
		{
			baseStaticValue.row = baseStaticValue.worksheet.getRow(i + 1);

			for (int j = 0; j < ColNum; j++) // Loop work for colNum
			{
				if (baseStaticValue.row == null)
					Data[i][j] = "";
				else {
					baseStaticValue.cell = baseStaticValue.row.getCell(j);
					if (baseStaticValue.cell == null)
						Data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = baseStaticValue.formatter.formatCellValue(baseStaticValue.cell);
						Data[i][j] = value; // This formatter get my all values as string i.e integer, float all type
											// data value
					}
				}
			}
		}
		return Data;
	}

	/**
	 * getCellData returns the value from excel.
	 * 
	 * @param RowNum
	 * @param ColNum
	 * @return String
	 */
	protected static String getCellData(int RowNum, int ColNum) {
		try {
			baseStaticValue.cell = baseStaticValue.worksheet.getRow(RowNum).getCell(ColNum);
			String cellData = baseStaticValue.formatter.formatCellValue(baseStaticValue.cell);
			return cellData;
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * setCellData will set the value at particular location excel.
	 * 
	 * @param value
	 * @param RowNum
	 * @param ColNum
	 */
	protected static void setCellData(String value, int RowNum, int ColNum) {
		try {
			baseStaticValue.row = baseStaticValue.worksheet.getRow(RowNum);
			baseStaticValue.cell = baseStaticValue.row.getCell(ColNum);
			if (baseStaticValue.cell == null) {
				baseStaticValue.cell = baseStaticValue.row.createCell(ColNum);
				baseStaticValue.cell.setCellValue(value);
			} else {
				baseStaticValue.cell.setCellValue(value);
			}
			FileOutputStream fileOut = new FileOutputStream(baseStaticValue.ExcelPath);
			baseStaticValue.workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			try {
				throw (e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
