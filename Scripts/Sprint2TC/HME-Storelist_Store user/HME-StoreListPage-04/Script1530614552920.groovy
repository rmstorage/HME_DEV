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

boolean TCflag=true
try{
	WebUI.navigateToUrl(GlobalVariable.devPublicCloudUrl)

	CustomKeywords.'projectSpecific.Reusability.login'(CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudUsername"),CustomKeywords.'projectSpecific.Reusability.getTestData'("HomePage","cloudPassword"))

	WebUI.delay(GlobalVariable.MED_DELAY)

	//Step1: To verify that user is able to navigate to store details page

	WebDriver driver = DriverFactory.getWebDriver()
	
	'Click on Stores Link'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('HomePage/storesLink'))
	WebUI.delay(GlobalVariable.MED_DELAY)

	WebUI.verifyElementPresent(findTestObject('StorePage/StoreListHeading'), 10)

	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/View_editbtn'))

	String defaultareaselected=WebUI.getAttribute(findTestObject('StorePage/defaultstoredetsails'), "aria-selected")

	if(defaultareaselected)
	{
		System.out.println("Store Details is set by default")
	}

	else{
		if(TCflag)
			TCflag=false
		System.out.println("Store Details is not set by default")
		WebUI.takeScreenshot()

	}

	//Step2: To navigate to ZOOM tab

	WebUI.verifyElementPresent(findTestObject('StorePage/zoomlabel'), 5)

	'Click on Zoom Label'
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomlabel'))

	//Step3: To verify the column headers displayed in the screen
	WebUI.verifyElementPresent(findTestObject('StorePage/registeredZoom'), 5)

	WebUI.verifyElementPresent(findTestObject('StorePage/systemVersion'), 5)

	WebUI.verifyElementPresent(findTestObject('StorePage/serialNumber'), 5)

	WebUI.verifyElementPresent(findTestObject('StorePage/systemStatus'), 5)

	//Step3: To verify the values displayed under each column header

	String systemVersion = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/versionValue'))

	String zoomSystemVersion = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/versionOnZoomTab'))

	if(systemVersion.equals(zoomSystemVersion))
	{
		System.out.println("System version value is displayed")
	}else{
		System.out.println("System version value is not displayed")
	}

	String systemStatusValue = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/systemStatusValue'))

	String zoomSystemStatus = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/systemStatusValueOnZoom'))

	if(systemStatusValue.equals(zoomSystemStatus))
	{
		System.out.println("System Status value is displayed")
	}else{

		System.out.println("System Status value is not displayed")
	}
	
	//Step5: To verify the links displayed in the screen
		
	String zoomSerialNoValue = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/serialNumberOnZoom'))
	
	WebUI.verifyElementPresent(findTestObject('StorePage/zoomViewDetails'), 5)
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomViewDetails'))
	
	String systemSettingSerialNoValue = CustomKeywords.'uiaction.CommonUIActions.getText'(findTestObject('StorePage/systemSettingSerialNo'))
	
	if(zoomSerialNoValue.equals(systemSettingSerialNoValue))
	{
		System.out.println("Serial Number Value is displayed")
	}else{

		System.out.println("Serial Number Value is not displayed")
	}
	
	//Step6: To verify the font, look, alignment for the page is the same according to requirement. 
	
	WebUI.back()
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/View_editbtn'))
	
	CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/zoomlabel'))
		
	WebElement text = driver.findElement(By.xpath("//span[text()='ZOOM']"))
	String fontFamily = text.getCssValue("font-family");
	System.out.println("Font Family -> "+fontFamily);
	WebUI.verifyMatch(fontFamily, CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","Zoomfontfamily"), false)
	
	String fonttxtAlign = text.getCssValue("text-align");
	System.out.println("Font Text Alignment -> "+fonttxtAlign);
	WebUI.verifyMatch(fonttxtAlign, CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","Zoomfontalign"), false)
	
	String fontSize = text.getCssValue("font-size");
	System.out.println("Font Size -> "+fontSize);
	WebUI.verifyMatch(fontSize, CustomKeywords.'projectSpecific.Reusability.getTestData'("StorePage","Zoomfontsize"), false)

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true
