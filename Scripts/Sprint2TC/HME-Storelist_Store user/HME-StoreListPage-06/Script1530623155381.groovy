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
	
	driver.findElement(By.xpath("(//span[contains(text(),'View/Edit')])[3]")).click()

	//CustomKeywords.'uiaction.CommonUIActions.click'(findTestObject('StorePage/View_editbtn'))

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

	//Step2: To verify the tabs shown in the screen
	
	WebUI.verifyElementPresent(findTestObject('StorePage/zoomlabel'), 5)
	
	WebUI.verifyElementPresent(findTestObject('StorePage/CIBLabel'), 5)
	
	//Step3 to 6: To verify that user is able to navigate to EOS tab(Pending)

		

}
catch(Exception e){
	e.printStackTrace()
	println "Exception of element not found"
	if(TCflag)
		TCflag=false
}

assert TCflag==true