import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

boolean TCflag=true
try{



	//Step1: To navigate to the application login page
	WebUI.navigateToUrl(GlobalVariable.SuperAdminUrl)

	//Step2:To navigate to the application home page
	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","publicadmin"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","Password"))
	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step3:To verify that logged in user name is displayed correctly

	WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage/loggedinUser'),10)

	String loggedInUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('Object Repository/HomePage/loggedinUser'))
	loggedInUser=loggedInUser.trim()

	String loggedinText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","loggedinText")

	if(loggedInUser.equals(loggedinText))
		System.out.println("Verified logged in user Text")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Could not verify logged in user Text"+WebUI.verifyMatch(loggedInUser,loggedinText, false))
	}


	String  welcomeUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('Object Repository/HomePage/welcomeUserText'))
	welcomeUser=welcomeUser.trim()
	String welcomeText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeText")

	if(welcomeUser.equals(welcomeText))
		System.out.println("Welcome user Text is displayed")
	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("Welcome user Text is not displayed"+WebUI.verifyMatch(welcomeUser,welcomeText, false))
			}

	//Step3: To verify that store admin name is displayed
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/userslink'))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String searchUserText2= CustomKeywords.'projectSpecific.Reusability.getTestData'("UsersPage","userSearchText2")

	CustomKeywords.'uiaction.CommonUIActions.enter'(findTestObject('UsersPage/searchTextBox'),searchUserText2)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/searchButton'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewUserLink1'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	
	String  viewAsUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('UsersPage/viewAsUserText'))
	viewAsUser=viewAsUser.trim()
	String viewAsUserText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","viewAsUserText")

	if(viewAsUser.equals(viewAsUserText))
		System.out.println("viewAsUserText  is displayed")
	else
	{

		if(TCflag)
			TCflag=false
		System.out.println("viewAsUserText is not displayed"+WebUI.verifyMatch(viewAsUser,viewAsUserText, false))
	}

	WebUI.delay(GlobalVariable.MED_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/viewAsUserText'))

	WebUI.delay(GlobalVariable.MED_DELAY)
	welcomeUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/welcomeMessage'))
	welcomeUser=welcomeUser.trim()
	String welcomeUserText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeMessage")

	if(welcomeUser.equals(welcomeUserText))
		System.out.println("welcomeUserText  is displayed")
	else
	{

		if(TCflag)
			TCflag=false
		System.out.println("welcomeUserText is not displayed"+WebUI.verifyMatch(welcomeUser,welcomeUserText, false))
	}


	WebUI.delay(278)

	//Step4 : To verify that application displays pop up message if public admin stays as store admin for more than 5 minutes

	WebUI.verifyElementPresent(findTestObject('HomePage/logOutDlgBox'),10)
	String continueAsUserText =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","continueAsUserText")
	String continueAsUser=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/continueAsUserText'))

	continueAsUserText=continueAsUserText.trim()
	continueAsUser=continueAsUser.trim()
	if(continueAsUser.equals(continueAsUserText))
		System.out.println(continueAsUserText+ "continueAsUserText  is displayed")

	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("continueAsUserText is not displayed "+WebUI.verifyMatch(continueAsUser,continueAsUserText, false))
	}

	//Step5 : To verify that public admin will be able to continue

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/continueAsUserText'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	
	WebUI.verifyElementNotVisible(findTestObject('HomePage/logOutDlgBox'),10)
	

	String currentlyViewedUser=CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","currentlyViewedUser")
	String currentlyViewedUserText=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/currentlyViewedUser'))
	currentlyViewedUser=currentlyViewedUser.trim()
	if(currentlyViewedUser.equals(currentlyViewedUserText))
		System.out.println(currentlyViewedUser+ "currentlyViewedUser  is displayed")

	else
	{
		if(TCflag)
			TCflag=false
		System.out.println("currentlyViewedUser is not displayed"+WebUI.verifyMatch(currentlyViewedUser,currentlyViewedUserText, false))
	}




	WebUI.delay(240)

	//Step6: To verify that public admin will be able to return as admin after the pop up message is displayed

	WebUI.verifyElementPresent(findTestObject('HomePage/logOutDlgBox'),10)

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step7: To verify that public admin is able to logout as store admin by clicking on X icon - Step 1

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutClose'))
	WebUI.delay(GlobalVariable.LONG_DELAY)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/currentlyUserlogOut'))

	WebUI.delay(GlobalVariable.MED_DELAY)

}catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true






