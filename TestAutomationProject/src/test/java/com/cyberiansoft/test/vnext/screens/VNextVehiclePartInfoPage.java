package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.dataclasses.ServiceData;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextSelectedServicesScreen;
import com.cyberiansoft.test.vnext.utils.PricesUtils;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class VNextVehiclePartInfoPage extends VNextBaseScreen {

	@FindBy(xpath = "//div[@data-page='matrix-info']")
	private WebElement vehiclepartinfoscreen;

	@FindBy(xpath = "//div[@action='size']")
	private WebElement vehiclepartsizeselect;

	@FindBy(xpath = "//div[@action='severity']")
	private WebElement vehiclepartseverityselect;

	@FindBy(xpath = "//div[@input='price']")
	private WebElement vehiclepartpricefld;

	@FindBy(xpath = "//*[@data-autotests-id='all-services']")
	private WebElement additionalavailableserviceslist;

	@FindBy(xpath = "//div[@action='notes']")
	private WebElement notesbutton;

	@FindBy(xpath = "//*[@action='save']")
	private WebElement savebtn;

	public VNextVehiclePartInfoPage(AppiumDriver<MobileElement> appiumdriver) {
		super(appiumdriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(vehiclepartinfoscreen));
	}

	public void selectVehiclePartSize(String vehiclepartsize) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@action='size']")));
		tap(vehiclepartsizeselect);
		tap(appiumdriver.findElement(By.xpath("//*[@action='select-item']/div/div[contains(text(), '" + vehiclepartsize + "')]")));
	}

	public void selectVehiclePartSeverity(String vehiclepartseverity) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@action='severity']")));
		tap(vehiclepartseverityselect);
		tap(appiumdriver.findElement(By.xpath("//*[@action='select-item']/div/div[contains(text(), '" + vehiclepartseverity + "')]")));
	}

	public void selectVehiclePartAdditionalService(String additionalservicename) {
		WebElement addservs = getVehiclePartAdditionalServiceCell(additionalservicename);
		String servicePrice = "";
		if (addservs != null) {
			servicePrice = addservs.findElement(By.xpath(".//div[@class='checkbox-item-subtitle checkbox-item-price']")).getText().trim();
			tap(WaitUtils.waitUntilElementIsClickable(addservs.findElement(By.xpath(".//input[@action='select-item']"))));
			WaitUtils.waitUntilElementInvisible(By.xpath("//div[@class='notifier-contaier']"));
			if (PricesUtils.isServicePriceEqualsZero(servicePrice)) {
				VNextServiceDetailsScreen serviceDetailsScreen = new VNextServiceDetailsScreen(appiumdriver);
				serviceDetailsScreen.clickServiceDetailsDoneButton();
			}
		} else
			Assert.assertTrue(false, "Can't find additional servicve: " + additionalservicename);
	}

	public VNextServiceDetailsScreen openVehiclePartAdditionalServiceDetails(String additionalservicename) {
		WebElement addservs = getVehiclePartAdditionalServiceCell(additionalservicename);
		if (addservs != null)
			tap(addservs.findElement(By.xpath(".//div[@class='checkbox-item-title']")));
		else
			Assert.assertTrue(false, "Can't find additional servicve: " + additionalservicename);
		return new VNextServiceDetailsScreen(appiumdriver);
	}

	public void openVehiclePartLaborServiceDetails(String additionalservicename) {
		WebElement webElement = WaitUtils.waitUntilElementIsClickable(By.xpath("//div[@class='checkbox-item-title' and text()='" + additionalservicename + "']"));
		tap(webElement);
	}

	public List<String> getListOfAdditionalServices() {
		List<String> additionalServices = new ArrayList<>();
		List<WebElement> addservs1 = getAvailableServicesList();
		for (WebElement additinalservice : addservs1) {
			additionalServices.add(additinalservice.findElement(By.xpath(".//div[@class='checkbox-item-title']")).getText());
		}
		return additionalServices;
	}

	private List<WebElement> getAvailableServicesList() {
		return additionalavailableserviceslist.findElements(By.xpath(".//*[@action='open-details']"));
	}

	private String getServiceListItemName(WebElement srvlistitem) {
		return srvlistitem.findElement(By.xpath(".//div[@class='checkbox-item-title']")).getText().trim();
	}

	public VNextServiceDetailsScreen openServiceDetailsScreen(String servicename) {
		tap(additionalavailableserviceslist.findElement(By.xpath(".//div[@class='checkbox-item-title' and text()='" + servicename + "']")));
		return new VNextServiceDetailsScreen(appiumdriver);
	}

	public WebElement getVehiclePartAdditionalServiceCell(String additionalservicename) {
		WebElement addsrvc = null;
		List<WebElement> addservs = getAvailableServicesList();
		for (WebElement additinalservice : addservs) {
			if (additinalservice.findElement(By.xpath(".//div[@class='checkbox-item-title']")).getText().equals(additionalservicename)) {
				addsrvc = additinalservice;
				break;
			}
		}
		return addsrvc;
	}

	public String getMatrixServiceTotalPriceValue() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(vehiclepartinfoscreen.findElement(By.xpath(".//span[@class='money-wrapper']"))));
		return vehiclepartinfoscreen.findElement(By.xpath(".//span[@class='money-wrapper']")).getText();
	}

	public void clickSaveVehiclePartInfo() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(savebtn));
		tap(savebtn);
	}

	public VNextNotesScreen clickMatrixServiceNotesOption() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(notesbutton));
		tap(notesbutton);
		return new VNextNotesScreen();
	}

	public void switchToSelectedServicesView() {
		tap(appiumdriver.findElement(By.xpath(".//*[@action='selected']")));
		WebDriverWait wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@action='selected' and @class='button active']")));
		wait = new WebDriverWait(appiumdriver, 5);
	}

	private WebElement getSelectedServicesList() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		return wait.until(ExpectedConditions.visibilityOf(appiumdriver.findElement(By.xpath(".//div[@data-autotests-id='all-services']"))));
	}

	private List<WebElement> getServicesListItems() {
		return getSelectedServicesList().findElements(By.xpath(".//*[@action='edit-item']"));
	}

	private WebElement getSelectedServiceCell(String servicename) {
		WebElement serviceListItem = null;
		List<WebElement> services = getServicesListItems();
		for (WebElement srv : services)
			if (getServiceListItemName(srv).equals(servicename)) {
				serviceListItem = srv;
				break;
			}
		return serviceListItem;
	}

	public void expandSelectedServiceDetails(String serviceName) {
		WebElement servicecell = getSelectedServiceCell(serviceName);
		tap(servicecell);
	}

	public String getSelectedServicePriceValue(String servicename) {
		String serviceprice = "";
		WebElement servicerow = getSelectedServiceCell(servicename);
		if (servicerow != null) {
			serviceprice = servicerow.findElement(By.xpath(".//*[@data-name='Amount']")).getAttribute("value").trim();
		} else
			Assert.assertTrue(false, "Can't find service: " + servicename);
		return serviceprice;
	}

	public String getSelectedServiceQuantityValue(String servicename) {
		String serviceprice = "";
		WebElement servicerow = getSelectedServiceCell(servicename);
		if (servicerow != null) {
			serviceprice = servicerow.findElement(By.xpath(".//*[@data-name='QuantityFloat']")).getAttribute("value").trim();
		} else
			Assert.assertTrue(false, "Can't find service: " + servicename);
		return serviceprice;
	}

	public String getSelectedServiceNotesValue(String servicename) {
		String serviceprice = "";
		WebElement servicerow = getSelectedServiceCell(servicename);
		if (servicerow != null) {
			serviceprice = servicerow.findElement(By.xpath(".//*[@data-name='Notes.desc']")).getAttribute("value").trim();
		} else
			Assert.assertTrue(false, "Can't find service: " + servicename);
		return serviceprice;
	}
}
