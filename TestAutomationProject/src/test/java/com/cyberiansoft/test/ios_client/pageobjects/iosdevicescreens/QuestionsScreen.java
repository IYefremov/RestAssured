package com.cyberiansoft.test.ios_client.pageobjects.iosdevicescreens;

import com.cyberiansoft.test.ios_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class QuestionsScreen extends iOSHDBaseScreen {
	
	@iOSFindBy(xpath = "//UIAPopover[1]/UIANavigationBar[1]/UIAButton[@name=\"Close\"]")
    private IOSElement capturedonebtn;
	
	public QuestionsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void acceptForReminderNoDrilling() {
		Assert.assertTrue (appiumdriver.findElementByName("REMINDER NO DRILLING AND USE E-COAT").isDisplayed());
		clickAccept();
	}

	public void clickAccept() {
		appiumdriver.findElementByName("Accept").click();
	}
	
	public void clickCustomButtonEstimateConditions() {
		Assert.assertTrue (appiumdriver.findElementByName("Estimate Conditions").isDisplayed());
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

	public void setOwnerName(String ownername) throws InterruptedException {
		appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[1]/UIATableCell[2]/UIATextView[1] ").click();
		Helpers.keyboadrType(ownername + "\n");
		appiumdriver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@name=\"Hide keyboard\"]").click();
		
	}

	public void setOwnerAddress(String owneraddress) throws InterruptedException {
		appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[2]/UIATableCell[2]/UIATextView[1] ").click();
		Helpers.keyboadrType(owneraddress);
		appiumdriver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@name=\"Return\"]").click();
		appiumdriver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@name=\"Hide keyboard\"]").click();
	}

	public void setOwnerCity(String ownercity) {
		((IOSElement) appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[1]/UIATableCell[2]/UIATextView[1]")).setValue(ownercity + "\n");
		appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[1]/UIATableCell[2]/UIATextView[1]").sendKeys("\n");
		appiumdriver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@name=\"Hide keyboard\"]").click();
	}

	public void clearZip() {
		appiumdriver.findElementByXPath("//UIATableCell[@name=\"Owner Zip\"]/UIAButton[@name=\"Clear\"]").click();	
	}

	public void setOwnerZip(String ownerzip)  {
		appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[2]/UIATableCell[2]/UIATextView[1]").sendKeys(ownerzip + "\n");
		appiumdriver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@name=\"Hide keyboard\"]").click();
	}
	
	public void setOwnerInfo(String ownername, String owneraddress, String ownercity, String ownerstate,
			String ownercountry, String ownerzip) throws InterruptedException {
		swipeScreenRight();
		setOwnerName(ownername);
		setOwnerAddress(owneraddress);
		swipeScreenRight();
		setOwnerCity(ownercity);
		Thread.sleep(2000);
		setOwnerState(ownerstate);
		swipeScreenRight();
		setOwnerCountry(ownercountry);
		swipeScreenRight();
		setOwnerZip(ownerzip);
	}

	public void setOwnerState(String ownerstate)
			throws InterruptedException {
		Assert.assertTrue (appiumdriver.findElementByName("Owner State").isDisplayed());
		Helpers.scroolTo(ownerstate);
		appiumdriver.findElementByName(ownerstate).click();
		Helpers.scroolTo("Yukon");
	}

	public void setOwnerCountry(String ownercountry) {
		Assert.assertTrue (appiumdriver.findElementByName("Owners Country").isDisplayed());
		appiumdriver.findElementByName(ownercountry).click();
	}
	
	public void chooseAVISCode(String aviscode) {
		Assert.assertTrue (appiumdriver.findElementByName("Choose One AVIS Code").isDisplayed());
		appiumdriver.findElementByName(aviscode).click();
	}
	
	public void chooseConsignor(String consignor) {
		Assert.assertTrue (appiumdriver.findElementByName("Consignor").isDisplayed());
		Helpers.scroolTo(consignor);
		appiumdriver.findElementByName(consignor).click();
	}
	
	public void makeCaptureForQuestion(String question) throws InterruptedException {
		String elementname = question + "_Image_Cell";
		appiumdriver.findElementByXPath("//UIATableCell[@name=\"" + elementname  + "\"]").click();
		Helpers.makeCapture();
		capturedonebtn.click();
	}

	public void drawSignature() throws InterruptedException {
		appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[2]/UIATableCell[2]/UIAStaticText[1]").click();
		Helpers.drawQuestionsSignature();
	}
	
	public void selectTaxPoint(String taxpoint) {
		appiumdriver.findElementByXPath("//UIATableCell[@name=\"Tax_Point_1\"]").click();
		appiumdriver.findElementByName(taxpoint).click();
	}
	
	public String clickSaveWithAlert() {
		clickSaveButton();
		return Helpers.getAlertTextAndAccept();
	}
	
	public void setEngineCondition(String enginecondition) {
		Assert.assertTrue (appiumdriver.findElementByName("Engine Condition").isDisplayed());
		appiumdriver.findElementByName(enginecondition).click();
	}
	
	public void setJustOnePossibleAnswer(String justoneanswer) {
		Assert.assertTrue (appiumdriver.findElementByName("Just One Possible Answer").isDisplayed());
		appiumdriver.findElementByName(justoneanswer).click();
	}
	
	public void setMultipleAnswersCopy(String multipleanswer) {
		Assert.assertTrue (appiumdriver.findElementByName("Multiple Answers Copy").isDisplayed());
		appiumdriver.findElementByName(multipleanswer).click();
	}
	
	public void setFreeText(String freetext) {
		Assert.assertTrue (appiumdriver.findElementByName("Free Text").isDisplayed());
		((IOSElement) appiumdriver.findElementByName("Free Text_TextView")).setValue(freetext + "\n");
		appiumdriver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@name=\"Hide keyboard\"]").click();
	}
	
	public void setBetteryTerminalsAnswer(String _answer) {
		Assert.assertTrue (appiumdriver.findElementByName("Battery Terminals / Cables / Mountings").isDisplayed());
		appiumdriver.findElementByName(_answer).click();
		//appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[1]/UIATableCell[@name=\"" + _answer + "\"]").click();
	}
	
	public void setCheckConditionOfBatteryAnswer(String _answer) {
		Assert.assertTrue (appiumdriver.findElementByName("Check Condition of Battery (Storage Capacity Test)").isDisplayed());
		if (Helpers.elementExists(By.xpath("//UIAScrollView[1]/UIATableView[2]/UIATableCell[@name=\"" + _answer + "\"]"))) {
			appiumdriver.findElementByXPath("//UIAScrollView[1]/UIATableView[2]/UIATableCell[@name=\"" + _answer + "\"]").click();
		} else
			appiumdriver.findElementByName(_answer).click();
	}
	
	public void setSampleQuestion(String samplequestion) {
		Assert.assertTrue (appiumdriver.findElementByName("Sample Question").isDisplayed());
		appiumdriver.findElementByName(samplequestion).click();
	}
	
	public void selectAnswerForQuestion(String question, String answer) {
		Assert.assertTrue (appiumdriver.findElementByName(question).isDisplayed());
		appiumdriver.findElementByName(answer).click();
	}

}
