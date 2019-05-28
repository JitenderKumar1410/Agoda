package com.agoda.framework.base;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * baseStaticValue class contains all of the static variables at one place.
 * @author jitenderkumar01
 *
 */
public class baseStaticValue {

	public static FileInputStream fileInputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static DataFormatter formatter = new DataFormatter();

	public static String ExcelPath = ".//ImportantFiles//TestDataAndTestStatusSheet.xlsx";
	public static String SheetNumber = "Sheet1";

	public static List<String> errorList = new ArrayList<String>();
	public static List<String> oldPassword = new ArrayList<String>();
	public static final int expectedResultColumn = 2;
	public static final int actualResultColumn = 3;
	public static final int statusUpdateColumn = 4;
	public static final int passwordUpdateInfoColumn = 5;

	public static int rowNumber = 0;
	public static Boolean firstRun = true;
	
	public static final String passStatus = "PASS";
	public static final String failStatus = "FAIL";
	public static final int passwordLength = 18;
	public static final String lengthError = "Password lenght must have atleast 18 character !!";
	public static final String specialCharError = "Password must have atleast one special character !!";
	public static final String upperCharError = "Password must have atleast one uppercase character !!";
	public static final String lowerCharError = "Password must have atleast one lowercase character !!";
	public static final String numericError = "Password must have atleast one digit character !!";
	public static final String repeatSpecialCharError = "Password have more than 4 special character i.e ";
	public static final String repeatCharError = "Password have repeated character more than 4 times i.e ";
	public static final String halfNumericError = "50 % of password is a number !!";
	public static final String oldPasswordError = "Old Password is not exist !!";
	public static final String similarErrorMessage = "New password is similar to old password by more than 80%, Try new password";
	
}
