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

	//Step 1: To navigate to the application login page

	WebUI.navigateToUrl("https://engdev.hmedtcloud.com/?pg=Login ")

	//Step 2: To navigate to the application home page

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","PWD"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	String welcomeMsg =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('HomePage/welcomeMessage'))
	String expactedWelcomeMsg =CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","welcomeMsg")

	if(welcomeMsg.contains(expactedWelcomeMsg))
	{
		System.out.println("Welcome message is verified")
	}else{
		System.out.println("Welcome message is not verified")
	}

	//pre-condition for selecting stores on reports page
	WebDriver driver = DriverFactory.getWebDriver()
	
	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))

	//driver.findElement(By.xpath("(//div[@class='ctable']/table//tr/td[3][not(contains(text(),'Dual'))]/../td[8]/table//td//span/a)[1]/../../../../../../../td[1]/a")).click()
	
	driver.findElement(By.xpath("(//div[@class='ctable']/table//tr/td[3][contains(text(),'vijay2')]/../td//span)[1]")).click()
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomlabel'))

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomViewDetails'))

	String storenumber=CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/storeID'))

	System.out.println(" the store number is--"+storenumber.trim())
	

	//Step 3: To navigate to report page.
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('UsersPage/reportsLink'))

	WebUI.delay(GlobalVariable.LONG_DELAY)
	//Step 4: To select the store

	
	driver.findElement(By.xpath("//span[contains(text(),'"+storenumber.trim()+"')]")).click()

	//Step 5: To verify time measure.

	'Select time'
	WebUI.click(findTestObject('ReportsPage/timeSelection'))

	'Click on Day option'
	WebUI.click(findTestObject('ReportsPage/dayTimeOption'))

	WebUI.click(findTestObject('ReportsPage/summaryReportHeading'))

	'verify Day is displayed in TimeSelection dropdown'

	WebUI.verifyElementText(findTestObject('ReportsPage/dayTimeOption'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","dayTimeSelection"))

	'verify Day is displayed in criteria day selection'
	WebUI.verifyElementText(findTestObject('ReportsPage/criteriaTimeMeasureForDay'),CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","criteriaDayTimeSelection"))

	//Step 6: To verify date.

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/fromDateSelector'))

	WebUI.delay(GlobalVariable.MIN_DELAY)

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue.equals(month))
	{
		while(!monthTextValue.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}

	}

	String data = CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","StartDate_March")
	startDate="(//td[text()='"+data+"'])[1]"
	println startDate
	startdateEle=driver.findElement(By.xpath(startDate))
	startdateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	WebUI.click(findTestObject('ReportsPage/toDateSelector'))

	monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
	monthTextArray= monthText.split(" ")
	month = monthTextArray[0]

	String monthTextValue1=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","Month")

	if(!monthTextValue1.equals(month))
	{
		while(!monthTextValue1.equals(month))
		{
			WebUI.click(findTestObject('ReportsPage/previousDateSelector2'))
			monthText=WebUI.getText(findTestObject('ReportsPage/monthText2'))
			monthTextArray= monthText.split(" ")
			month = monthTextArray[0]

		}


	}

	String endDate=CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","EndDate_March")

	toDate="(//td[text()='"+endDate+"'])[2]"
	println toDate

	toDateEle=driver.findElement(By.xpath(toDate))
	toDateEle.click()

	WebUI.delay(GlobalVariable.MIN_DELAY)

	dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate1'),"value")

	String fromDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","fromDate_March")
	if(!dateAttr.equals(fromDateValue))
	{
		if(TCflag)
			TCflag=false
		println "From date is not selected properly"
		WebUI.takeScreenshot()
	}

	String dateAttr =WebUI.getAttribute(findTestObject('ReportsPage/selectedDate2'),"value")

	String toDateValue =CustomKeywords.'projectSpecific.Reusability.getTestData'("ReportsPage","toDate_March")
	if(!dateAttr.equals(toDateValue))
	{
		if(TCflag)
			TCflag=false
		println "to Date is not selected properly"
		WebUI.takeScreenshot()
	}

	//Step 7: To verify the time zone for the Day report in the report section by checking Print time section.

	'click GenerateReport button'
	WebUI.click(findTestObject('ReportsPage/generateReport'))

	WebUI.delay(GlobalVariable.LONG_DELAY)

	'Print Time is displayed in EST'

	String printTime =CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('SummarizedReportPage/printTime'))

	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("hh");
	String currentSystemTime=sdf.format(cal.getTime())
	System.out.println(currentSystemTime);

	if(printTime.contains(currentSystemTime)){

		System.out.println("Print Time is displayed the current local time ")

	}else{
		if(TCflag)
			TCflag=false
		System.out.println("Print Time is not displayed the current local time ")
		WebUI.takeScreenshot()
	}
	
	WebUI.delay(GlobalVariable.MIN_DELAY)
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/logoutLink'))

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
