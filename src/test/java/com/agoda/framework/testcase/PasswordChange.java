package com.agoda.framework.testcase;

import org.testng.annotations.Test;
import com.agoda.framework.util.SupportingFunctions;

/**
 * PasswordChange is extending SupportingFunctions class to use the functions of
 * it. It will run callerFunction method and check password is changed
 * successfully or not.
 * 
 * @author jitenderkumar01
 *
 */
public class PasswordChange extends SupportingFunctions {

	/**
	 * callerFunction method will call ChangePassword method of SupportingFunctions
	 * class and give information that password has changed successfully or not.
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param expectedErrorMessage
	 * @param actualResult
	 * @param passwordChangedStatus
	 * @param testStatus
	 * 
	 */
	@Test(dataProvider = "ReadExcel")
	public void callerFunction(String oldPassword, String newPassword, String expectedErrorMessage, String actualResult,
			String testStatus, String passwordChangedStatus) {

		ChangePassword(oldPassword, newPassword);

	}
}
