package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.baseutils.BaseUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class VNextBaseScreen {

	AppiumDriver<MobileElement> appiumdriver;
	
	@FindBy(xpath="//*[@data-autotests-id='change-screen-popover']")
	private WebElement changescrenpopover;

	public VNextBaseScreen(AppiumDriver<MobileElement> driver) {
		this.appiumdriver = driver;
	}
	
	public void tap(WebElement element) {

		BaseUtils.waitABit(300);

		element.click();
		//new TouchActions(appiumdriver).singleTap(element).perform();
		/*Action tapAction = new SingleTapAction(appiumdriver.getTouch(),  (org.openqa.selenium.interactions.internal.Locatable) element);
		CompositeAction action = new CompositeAction();
		action.addAction(tapAction).perform();*/
		
		/*if (VNextAppiumDriverBuilder.getPlatformName().toLowerCase().equals("android")) {
			new TouchActions(appiumdriver).singleTap(element).perform();
			
		} else {
			int xx = element.getLocation().getX() + element.getSize().getWidth()/2;
			int yy = element.getLocation().getY() + element.getSize().getHeight()/2;
			
			new TouchAction(appiumdriver).tap(xx, yy).perform();
		}		
		waitABit(300);*/
		
	}
	
	public void setValue(WebElement element, String value) {
		tap(element);
		element.clear();
		appiumdriver.getKeyboard().sendKeys(value);
		appiumdriver.hideKeyboard();
		//element.sendKeys(value);
	}
	
	public void tapListElement(WebElement scrollablelist, String value) {			
		WebElement elem = scrollablelist.findElement(By.xpath(".//div[text()='" + value + "']"));	
		JavascriptExecutor je = (JavascriptExecutor) appiumdriver;
		je.executeScript("arguments[0].scrollIntoView(true);",elem);
		tap(scrollablelist.findElement(By.xpath(".//div[text()='" + value + "']")));
	}
	
	public void swipeScreenLeft() {	
		if (appiumdriver instanceof JavascriptExecutor)
		    ((JavascriptExecutor)appiumdriver).executeScript("$('.page-content').trigger('swipeleft')");
		BaseUtils.waitABit(1000);
	}
	
	public void swipeScreensLeft(int screensnumber) {		
		for (int i = 0; i < screensnumber; i++) {
			swipeScreenLeft();
			if (checkHelpPopupPresence())
				if (appiumdriver.findElementByXPath("//div[@class='help-button' and text()='OK, got it']").isDisplayed())
					tap(appiumdriver.findElementByXPath("//div[@class='help-button' and text()='OK, got it']"));
		}
	}
	
	public void swipeScreenRight() {
		
		if (appiumdriver instanceof JavascriptExecutor)
		    ((JavascriptExecutor)appiumdriver).executeScript("$('.page-content').trigger('swiperight');");		
		BaseUtils.waitABit(2000);
	}
	
	public void clickScreenBackButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@action='back']")));
		wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@action='back']")));
		tap(appiumdriver.findElementByXPath("//*[@action='back']"));
		//log(LogStatus.INFO, "Tap Back screen Back button");
	}
	
	public void clickScreenForwardButton() {
		tap(appiumdriver.findElementByXPath("//*[@action='forward']"));
		//log(LogStatus.INFO, "Tap Back screen Forward button");
	}
	
	public void swipeScreensRight(int screensnumber) {
		for (int i = 0; i < screensnumber; i++) 
			swipeScreenRight();	
	}	

	protected boolean checkHelpPopupPresence() {
		appiumdriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		boolean exists = false;
		try {
			exists = appiumdriver.findElementsByXPath("//div[@class='help-button' and text()='OK, got it']").size() > 0;
		} catch (NoSuchElementException ignored) {
			exists = false;
		}
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return exists;
	}
	
	public void changeScreen(String screenName) {
		clickScreenTitleCaption();	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.visibilityOf(changescrenpopover));
		tap(changescrenpopover.findElement(By.xpath(".//span[text()='" + screenName + "']")));
		BaseUtils.waitABit(1000);
	}
	
	public boolean isScreenPresentInChangeScreenPopoverList(String screenName) {
		return changescrenpopover.findElements(By.xpath(".//span[text()='" + screenName + "']")).size() > 0;
	}
	
	public void clickScreenTitleCaption() {
		tap(appiumdriver.findElement(By.xpath("//span[@class='page-title']")));
	}
	
	public boolean elementExists(String xpath) {
		boolean exists = false;
		appiumdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		exists =  appiumdriver.findElementsByXPath(xpath).size() > 0;
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return exists;
	}
}
