package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.wizardscreens;

import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class QuestionsScreen extends BaseWizardScreen {
	
	public QuestionsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void acceptForReminderNoDrilling() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("REMINDER NO DRILLING AND USE E-COAT")));
		clickAccept();
	}

	public void clickAccept() {
		appiumdriver.findElementByName("Accept").click();
	}
	
	public void clickCustomButtonEstimateConditions() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Estimate Conditions")));
	}

	public void selectOtherQuestions() {
		clickCustomButtonEstimateConditions();
		appiumdriver.findElementByName("Other").click();
	}

	public void selectOutsideQuestions() {
		clickCustomButtonEstimateConditions();
		appiumdriver.findElementByName("Outside").click();
	}

	public void selectProperQuestions() {
		clickCustomButtonEstimateConditions();
		appiumdriver.findElementByName("Proper").click();
	}

	public void setOwnerName(String ownername) {
		appiumdriver.findElementByAccessibilityId("Owner Name_TextView_Cell").click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(ownername);
		((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
		appiumdriver.hideKeyboard();
		
	}

	public void setOwnerAddress(String owneraddress) {
		appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[@name='Owner Address_TextView_Cell']").click();
		//appiumdriver.findElementByAccessibilityId("Owner Address_TextView_Cell").click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(owneraddress);
		((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
		appiumdriver.hideKeyboard();
	}

	public void setOwnerCity(String ownercity) {
		((IOSElement) appiumdriver.findElementByAccessibilityId("Owner City_TextView_Cell")).click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(ownercity);
		((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
		appiumdriver.hideKeyboard();
	}

	public void clearZip() {
		((IOSElement) appiumdriver.findElementsByAccessibilityId("Clear").get(1)).click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeCell[@name=\"Owner Zip\"]/XCUIElementTypeButton[@name=\"Clear\"]").click();	
	}

	public void setOwnerZip(String ownerzip)  {
		((IOSElement) appiumdriver.findElementByAccessibilityId("Owner Zip_TextView_Cell")).click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(ownerzip);
		((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
		appiumdriver.hideKeyboard();
	}
	
	public void setOwnerInfo(String ownername, String owneraddress, String ownercity, String ownerstate,
			String ownercountry, String ownerzip) throws InterruptedException {
		swipeScreenRight();
		setOwnerName(ownername);
		setOwnerAddress(owneraddress);
		swipeScreenRight();
		setOwnerCity(ownercity);
		setOwnerState(ownerstate);
		swipeScreenRight();
		setOwnerCountry(ownercountry);
		swipeScreenRight();
		setOwnerZip(ownerzip);
	}

	public void setOwnerState(String ownerstate) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Owner State")));
		if (!appiumdriver.findElementByName(ownerstate).isDisplayed())
			scrollToElement(ownerstate);
		appiumdriver.findElementByName(ownerstate).click();
	}

	public void setOwnerCountry(String ownercountry) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Owners Country")));
		appiumdriver.findElementByName(ownercountry).click();
	}
	
	public InvoiceInfoScreen chooseAVISCode(String aviscode) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Choose One AVIS Code")));
		appiumdriver.findElementByName(aviscode).click();
		return new InvoiceInfoScreen(appiumdriver);
	}
	
	public void chooseConsignor(String consignor) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Consignor")));
		Helpers.scroolTo(consignor);
		appiumdriver.findElementByName(consignor).click();
	}
	
	public void makeCaptureForQuestion(String question) throws InterruptedException {
		String elementname = question + "_Image_Cell";
		appiumdriver.findElementByXPath("//UIATableCell[@name=\"" + elementname  + "\"]").click();
		Helpers.makeCapture();
		appiumdriver.findElementByName("Done").click();
	}

	public void drawSignature() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(appiumdriver,10);
		WebElement signature = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Signature_Handwriting")));
		//WebElement signature = appiumdriver.findElementByAccessibilityId("Tap here to sign");
		int x = signature.getLocation().getX()/2;

		int y = signature.getLocation().getY()/2;
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(signature, x, y) .perform();
		
		
		
		Helpers.waitABit(500);
		
		//int yy = element.getLocation().getX();

		//int xx = element.getLocation().getY();
		MobileElement element = (MobileElement) appiumdriver.findElementByAccessibilityId("Signature_Handwriting_Cell");
		int xx = element.getLocation().getX();

		int yy = element.getLocation().getY();

		action = new TouchAction(appiumdriver);
		//action.press(xx + 100,yy + 100).waitAction(duration).moveTo(xx + 200, yy + 200).release().perform();
		action.press(xx + 100,yy + 100).waitAction(Duration.ofSeconds(3)).moveTo(xx + 200, yy + 200).release().perform();
		
	
		action = new TouchAction(appiumdriver);
		//action.press(xx - 10,yy + 50).waitAction(3000).moveTo(xx + 200, yy + 200).release().perform();
		//action.tap(xx - 20, yy + 10).perform();
		action.tap(element, 30, element.getSize().getHeight() - 20).perform();
		
	}
	
	public void selectTaxPoint(String taxpoint) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Tax_Point_1")));
		wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(appiumdriver.findElementByAccessibilityId(taxpoint))).click();
	}
	
	public String clickSaveWithAlert() {
		appiumdriver.findElementByAccessibilityId("Save").click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name='Save']").click();
		return Helpers.getAlertTextAndAccept();
	}
	
	public void setEngineCondition(String enginecondition) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Engine Condition")));
		appiumdriver.findElementByName(enginecondition).click();
	}
	
	public void setJustOnePossibleAnswer(String justoneanswer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Just One Possible Answer")));
		appiumdriver.findElementByName(justoneanswer).click();
	}
	
	public void setMultipleAnswersCopy(String multipleanswer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Multiple Answers Copy")));
		appiumdriver.findElementByName(multipleanswer).click();
	}
	
	public void setFreeText(String freetext) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Free Text")));
		((IOSElement) appiumdriver.findElementByName("Free Text_TextView")).setValue(freetext + "\n");
		((IOSDriver) appiumdriver).hideKeyboard();
	}
	
	public void setBetteryTerminalsAnswer(String _answer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Battery Terminals / Cables / Mountings")));
		appiumdriver.findElementByName(_answer).click();
		//appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[1]/UIATableCell[@name=\"" + _answer + "\"]").click();
	}
	
	public void setCheckConditionOfBatteryAnswer(String _answer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Check Condition of Battery (Storage Capacity Test)")));
		appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + 
				_answer + "']").click();
	}
	
	public void setSampleQuestion(String samplequestion) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Sample Question")));
		appiumdriver.findElementByName(samplequestion).click();
	}
	
	public void selectAnswerForQuestion(String question, String answer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(question)));
		appiumdriver.findElementByName(answer).click();
	}
	
	public void setToYesFinalQuestion() {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[2]")).click();
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[2]")).click();
	}
	
	public void setToYesCompleteQuestion() {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[4]")).click();
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[4]")).click();
	}
	
	public void answerAllIsGoodQuestion() {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeTable/XCUIElementTypeCell[2]")).click();
	}
	
	public void answerTrafficLight1Question() {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeTable/XCUIElementTypeCell[2]")).click();
	}

}