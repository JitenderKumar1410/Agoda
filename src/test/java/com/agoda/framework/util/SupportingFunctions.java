package com.agoda.framework.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import com.agoda.framework.base.baseStaticValue;
import com.agoda.framework.util.ExcelUtil;

/**
 * SupportingFunctions class contains all of the supporting functions.
 * 
 * @author jitenderkumar01
 *
 */
public class SupportingFunctions extends ExcelUtil {

	/**
	 * ChangePassword will check the existence of old password, then new password
	 * requirements, then check similarity between passwords.
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return Boolean
	 */
	protected static Boolean ChangePassword(String oldPassword, String newPassword) {
		Boolean Flag = false;
		baseStaticValue.rowNumber++;
		baseStaticValue.errorList.clear();

		if (checkOldPassword(oldPassword)) {
			if (newPasswordRequirementCheck(newPassword)) {
				if (!(checkSimilarityInPassword(oldPassword, newPassword))) {
					ExcelUtil.setCellData(baseStaticValue.passStatus, baseStaticValue.rowNumber,
							baseStaticValue.statusUpdateColumn);
					ExcelUtil.setCellData("TRUE", baseStaticValue.rowNumber, baseStaticValue.passwordUpdateInfoColumn);
					Flag = true;
					return Flag;
				} else {
					assertErrorAndUpdateExcel();
				}
			} else {
				assertErrorAndUpdateExcel();
			}
		} else {
			assertErrorAndUpdateExcel();
		}

		return Flag;

	}

	/**
	 * assertErrorAndUpdateExcel method assert and update the excel sheet
	 */
	private static void assertErrorAndUpdateExcel() {
		String error = baseStaticValue.errorList.toString().replace('[', ' ');
		String actualError = error.replace(']', ' ').trim();
		if (actualError
				.equals(ExcelUtil.getCellData(baseStaticValue.rowNumber, baseStaticValue.expectedResultColumn))) {
			ExcelUtil.setCellData(baseStaticValue.passStatus, baseStaticValue.rowNumber,
					baseStaticValue.statusUpdateColumn);
			ExcelUtil.setCellData(actualError, baseStaticValue.rowNumber, baseStaticValue.actualResultColumn);
			ExcelUtil.setCellData("FALSE", baseStaticValue.rowNumber, baseStaticValue.passwordUpdateInfoColumn);
		} else {
			ExcelUtil.setCellData(baseStaticValue.failStatus, baseStaticValue.rowNumber,
					baseStaticValue.statusUpdateColumn);
			ExcelUtil.setCellData(actualError, baseStaticValue.rowNumber, baseStaticValue.actualResultColumn);
			ExcelUtil.setCellData("FALSE", baseStaticValue.rowNumber, baseStaticValue.passwordUpdateInfoColumn);
		}
		Assert.assertEquals(actualError,
				ExcelUtil.getCellData(baseStaticValue.rowNumber, baseStaticValue.expectedResultColumn));

	}

	/**
	 * newPasswordRequirementCheck method will check all of the requirements are
	 * fulfill or not
	 * 
	 * @param newPassword
	 * @return boolean
	 */
	private static boolean newPasswordRequirementCheck(String newPassword) {
		Pattern specailCharPatten = Pattern.compile("[!@#$&*]");
		Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
		Pattern lowerCasePatten = Pattern.compile("[a-z ]");
		Pattern numericCasePatten = Pattern.compile("[0-9 ]");

		boolean flag = true;

		if (!(newPassword.length() == 0)) {
			if (newPassword.length() < baseStaticValue.passwordLength) {
				baseStaticValue.errorList.add(baseStaticValue.lengthError);
				flag = false;
			}

			if (!specailCharPatten.matcher(newPassword).find()) {
				baseStaticValue.errorList.add(baseStaticValue.specialCharError);
				flag = false;
			}

			if (!UpperCasePatten.matcher(newPassword).find()) {
				baseStaticValue.errorList.add(baseStaticValue.upperCharError);
				flag = false;
			}

			if (!lowerCasePatten.matcher(newPassword).find()) {
				baseStaticValue.errorList.add(baseStaticValue.lowerCharError);
				flag = false;
			}

			if (!numericCasePatten.matcher(newPassword).find()) {
				baseStaticValue.errorList.add(baseStaticValue.numericError);
				flag = false;
			}

			if (!countRepeatedCharacterOccurrences(newPassword)) {
				flag = false;
			}

			if (!checkNumericPassword(newPassword)) {
				flag = false;
			}
		} else {
			baseStaticValue.errorList.add("Please enter new password");
			flag = false;
		}

		return flag;
	}

	/**
	 * Check password should not contain more than 50 percent numeric value
	 * 
	 * @param password
	 * @return Boolean
	 */
	private static Boolean checkNumericPassword(String password) {
		int j = 0;
		Boolean checkNumericflag = true;
		char[] c = password.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (Character.getType(c[i]) == 9) {
				j++;
			}
		}
		if (password.length() / 2 <= j) {
			baseStaticValue.errorList.add(baseStaticValue.halfNumericError);
			checkNumericflag = false;
		}

		return checkNumericflag;
	}

	/**
	 * countRepeatedCharacterOccurrences method Will check the occurrences of
	 * repeated character and special character
	 * 
	 * @param newPassword
	 * @return Boolean
	 */
	private static Boolean countRepeatedCharacterOccurrences(String newPassword) {

		Boolean repeatFlag = true;

		// create Map of Character-Integer
		Map<Character, Integer> mapOfRepeatedChar = new HashMap<Character, Integer>();
		// convert String into character-array using toCharArray() method
		char[] chArray = newPassword.toCharArray();
		// iterate through char[] array
		for (char ch : chArray) {
			if (ch != ' ') {
				// check Map contains particular character
				if (mapOfRepeatedChar.containsKey(ch)) {
					// If contains, increase count value by 1
					mapOfRepeatedChar.put(ch, mapOfRepeatedChar.get(ch) + 1);
				} else {
					// otherwise, make a new entry
					mapOfRepeatedChar.put(ch, 1);
				}
			}
		}
		// check for special character
		for (char ch : chArray) {
			if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '&' || ch == '*') {
				if (mapOfRepeatedChar.get(ch) > 4) {
					baseStaticValue.errorList.add(baseStaticValue.repeatSpecialCharError + ch);
					repeatFlag = false;
					break;
				}

			}
		}

		// check for characters except special character
		for (char ch : chArray) {
			if (!(ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '&' || ch == '*')) {
				if (mapOfRepeatedChar.get(ch) > 4) {
					baseStaticValue.errorList.add(baseStaticValue.repeatCharError + ch);
					repeatFlag = false;
					break;
				}
			}
		}

		return repeatFlag;

	}

	/**
	 * checkOldPassword will check old password is exist or not
	 * 
	 * @param oldPassword
	 * @return Boolean
	 */
	private static Boolean checkOldPassword(String oldPassword) {
		if (baseStaticValue.firstRun) {
			Collections.addAll(baseStaticValue.oldPassword, "An7$asdfghjkiolpjya", "Bn7$asdfghjkiolpjyb",
					"An7$asdfghjkiolpjya", "Cn7$asdfghjkiolpjyc", "Dn7$asdfghjkiolpjyd");
			baseStaticValue.firstRun = false;
		}
		if (!(oldPassword.length() == 0)) {
			if (!(baseStaticValue.oldPassword.contains(oldPassword))) {
				baseStaticValue.errorList.add(baseStaticValue.oldPasswordError);
				return false;
			}
		} else {
			baseStaticValue.errorList.add("Please enter old password");
			return false;
		}
		return true;

	}

	/**
	 * checkSimilarityInPassword method checks the similarity between old and new
	 * password should not be more than 80 percent
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return Boolean
	 */
	private static Boolean checkSimilarityInPassword(String oldPassword, String newPassword) {

		double similarity = StringUtils.getJaroWinklerDistance(oldPassword, newPassword);
		if (similarity > 0.8) {
			baseStaticValue.errorList.add(baseStaticValue.similarErrorMessage);
			return true;
		}
		return false;
	}
}
