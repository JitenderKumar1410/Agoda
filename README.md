# Automated test for change password functionality

# Tasks
Please complete the point below
1. Code for change password function
2. Implement automate test for the created function, test cases with test data provide in each case
3. The verify password with system and similar check function should be a mock which return True/False

**What all dependencies used for this task?**
-----
 * JAVA
 * TestNG, Apache-poi, commons-lang3
 * MAVEN
 
We give the initial version of tests in order to save your time on extracting locators. 

**What all i have included in this project?**
----
*Created one com.agoda.framework.base package which includes:*
*baseStaticValue Class:* Contains all of the static content of the project at one place.

*Created one com.agoda.framework.testcase package which includes:*
*passwordChange Class:* contains the caller function which calls changePassword function to be executed with set of test data by using testNG.

*Created one com.agoda.framework.util package which includes:*
*ExcelUtil Class:* Contains all of methods to deal with excel sheet like set data, get data and so on.

*SupportingFunctions:* This is the main class which contains the logic of all of the required checks.

# Description of test cases to check the functionality of change password

**Different test cases to check the expected error messages while changing password**

*First two TC's:* Old or new password is missing.
*Third TC:* Old password doesn't exist and passes correct new password.
*Fourth TC:* Old password doesn't exist and passes incorrect new password.

*Fifth to Thirteen TC's:* Old password exists but new password is not meeting the requirements. (Only on requirement is missing)
*Eighth TC:* Password is having a special character but not one from the requirement of special character.

*Thirteen to Eighteen TC's:* Old password exists but new password is not meeting the requirements. (More than one requirement is missing)

*Nineteen and Twenty TC's:* Old password and new password is correct but similarity is more than 80 percent.

*Twenty one and Twenty two TC:* Old password and new password is similar but in new password there is one required parameter missing.

**Now come to positive TC's:**

*Twenty third to Twenty sixth TC:* All well, so no error message in actual and expected column. Password change status column will be updated as TRUE.

**Some Scenarios in which the expected result is mentioned is wrong(Intentionally failed scenarios)**

*Twenty seven to Thirty TC's:* Expected result is mentioned wrong so TC should testStatus FAIL and Password change status column will be updated as FALSE.

# Information about test data
We have one ImportantFiles folder under the project, which contains the excel sheet name as "TestDataAndTestStatusSheet.xlsx".

This files contains OldPassword, NewPassword, ExpectedErrorMessage, ActualResult, TestStatus, PasswordChangedStatus.

Actual Result reflects the actual outcome of the test case
TestStatus reflects expected and actual matches or not
PasswordChangedStatus reflects password has changed successfully or not
*All of above three fields will be updated at run time at the time of execution*

# TEST REPORT

emailable-report.html is placed inside the test-output folder, This reports generate each time at run time according to the test run.

# What all setup required to run the test cases

you need to take checkout of this project and run command "mvn clean install" to get all of the required dependencies (Should have maven in system)

Now, just import this project in eclipse and run the project. or you can run using maven commands as well.


