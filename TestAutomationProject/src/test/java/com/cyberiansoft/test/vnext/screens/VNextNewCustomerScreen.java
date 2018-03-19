package com.cyberiansoft.test.vnext.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import com.cyberiansoft.test.vnext.utils.VNextRetailCustomer;
import com.relevantcodes.extentreports.LogStatus;

public class VNextNewCustomerScreen extends VNextBaseScreen {
	
	@FindBy(xpath="//div[@class='page customer-details page-on-center']")
	private WebElement newcustomerscreen;
	
	@FindBy(xpath="//*[@action='save']")
	private WebElement savebtn;
	
	@FindBy(id="my-form")
	private WebElement newcustomerform;
	
	@FindBy(id="customerDetailsFirstName")
	private WebElement firstnamefld;
	
	@FindBy(id="customerDetailsLastName")
	private WebElement lastnamefld;
	
	@FindBy(id="customerDetailsCompanyName")
	private WebElement companynamefld;
	
	@FindBy(id="customerDetailsEmail")
	private WebElement emailfld;
	
	@FindBy(id="customerDetailsPhone")
	private WebElement phonefld;
	
	@FindBy(id="customerDetailsAddress")
	private WebElement addressfld;
	
	@FindBy(id="customerDetailsAddress2")
	private WebElement address2fld;
	
	@FindBy(id="customerDetailsCity")
	private WebElement cityfld;
	
	@FindBy(id="customerDetailsCountry")
	private WebElement countrycell;
	
	@FindBy(xpath="//div[@action='state']")
	private WebElement statecell;
	
	@FindBy(id="customerDetailsZip")
	private WebElement zipfld;
	
	////////////Countries
	@FindBy(xpath="//div[@data-page='countries']")
	private WebElement countriespage;
	
	////////////States
	@FindBy(xpath="//div[@data-page='states']")
	private WebElement statespage;
	
	public VNextNewCustomerScreen(SwipeableWebDriver appiumdriver) {
		super(appiumdriver);
		PageFactory.initElements(new ExtendedFieldDecorator(appiumdriver), this);	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(firstnamefld));
		waitABit(1000);
	}
	
	public void createNewCustomer(VNextRetailCustomer retailCustomer) {
		if (retailCustomer.getFirstName() != null )
			setCustomerFirstName(retailCustomer.getFirstName());
		if (retailCustomer.getLastName() != null )
			setCustomerLastName(retailCustomer.getLastName());
		if (retailCustomer.getCompany() != null )
			setCustomerCompanyName(retailCustomer.getCompany());
		if (retailCustomer.getMailAddress() != null)
			setCustomerEmail(retailCustomer.getMailAddress());
		if (retailCustomer.getCustomerPhone() != null)
			setCustomerPhone(retailCustomer.getCustomerPhone());
		if (retailCustomer.getCustomerAddress1() != null)
			setCustomerAddress(retailCustomer.getCustomerAddress1());
		if (retailCustomer.getCustomerAddress2() != null)
			setCustomerAddress2(retailCustomer.getCustomerAddress2());
		if (retailCustomer.getCustomerCity() != null)
			setCustomerCity(retailCustomer.getCustomerCity());
		if (retailCustomer.getCustomerCountry() != null)
			selectCustomerCountry(retailCustomer.getCustomerCountry());
		if (retailCustomer.getCustomerState() != null)
			selectCustomerState(retailCustomer.getCustomerState());
		if (retailCustomer.getCustomerZip() != null)
			setCustomerZIP(retailCustomer.getCustomerZip());
		clickSaveCustomerButton();
	}

	
	public void setCustomerFirstName(String firstname) {
		firstnamefld.clear();
		firstnamefld.sendKeys(firstname);
		log(LogStatus.INFO, "Set customer First Name: " + firstname);
	}
	
	public String getCustomerFirstName() {
		return firstnamefld.getAttribute("value");
	}
	
	public void setCustomerLastName(String lastname) {
		if (lastname.length() > 0) {
			lastnamefld.clear();
			lastnamefld.sendKeys(lastname);
			log(LogStatus.INFO, "Set customer Last Name: " + lastname);
		}
	}
	
	public String getCustomerLastName() {
		return lastnamefld.getAttribute("value");
	}
	
	public void setCustomerCompanyName(String companyname) {
		if (companyname.length() > 0) {
			companynamefld.clear();
			companynamefld.sendKeys(companyname);
			log(LogStatus.INFO, "Set customer Company Name: " + companyname);
		}
	}
	
	public String getCustomerCompanyName() {
		return companynamefld.getAttribute("value");
	}
	
	public void setCustomerEmail(String customeremail) {
		if (customeremail.length() > 0) {
			emailfld.clear();
			emailfld.sendKeys(customeremail);
			log(LogStatus.INFO, "Set customer Email: " + customeremail);
		}
	}
	
	public String getCustomerEmail() {
		return emailfld.getAttribute("value");
	}
	
	public void setCustomerPhone(String customerphone) {
		if (customerphone.length() > 0) {
			phonefld.clear();
			phonefld.sendKeys(customerphone);
			log(LogStatus.INFO, "Set customer Phone: " + customerphone);
		}
	}
	
	public String getCustomerPhone() {
		return phonefld.getAttribute("value");
	}
	
	public void setCustomerAddress(String customeraddress) {
		if (customeraddress.length() > 0) {
			addressfld.clear();
			addressfld.sendKeys(customeraddress);
			log(LogStatus.INFO, "Set customer Address: " + customeraddress);
		}
	}
	
	public void setCustomerAddress2(String customeraddress2) {
		if (customeraddress2.length() > 0) {
			address2fld.clear();
			address2fld.sendKeys(customeraddress2);
			log(LogStatus.INFO, "Set customer Address 2: " + customeraddress2);
		}
	}
	
	public String getCustomerAddress() {
		return addressfld.getAttribute("value");
	}
	
	public void setCustomerCity(String customercity) {
		if (customercity.length() > 0) {
			cityfld.clear();
			cityfld.sendKeys(customercity);
			log(LogStatus.INFO, "Set customer Address: " + customercity);
		}
	}
	
	public String getCustomerCity() {
		return cityfld.getAttribute("value");
	}
	
	public void setCustomerZIP(String customerzip) {
		if (customerzip.length() > 0) {
			zipfld.clear();
			zipfld.sendKeys(customerzip);
			log(LogStatus.INFO, "Set customer Address: " + customerzip);
		}
	}
	
	public String getCustomerZIP() {
		return zipfld.getAttribute("value");
	}
	
	public void selectCustomerCountry(String customercountry) {
		if (customercountry.length() > 0) {
			tap(countrycell);
			WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.visibilityOf(countriespage));
			tap(countriespage.findElement(By.xpath(".//*[@action='select-item' and contains(text(), '" + customercountry + "')]")));
			wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.visibilityOf(firstnamefld));
			log(LogStatus.INFO, "Select customer Country: " + customercountry);
		}
	}
	
	public String getCustomerCountry() {
		return countrycell.getAttribute("value");
	}
	
	public void selectCustomerState(String customerstate) {
		//waitABit(2000);
		if (customerstate.length() > 0) {
			WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.visibilityOf(statecell));
			JavascriptExecutor je = (JavascriptExecutor) appiumdriver;
			je.executeScript("arguments[0].scrollIntoView(true);",statecell);
			tap(statecell);
			wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.visibilityOf(statespage));
			tap(statespage.findElement(By.xpath(".//*[@action='select-item' and contains(text(), '" + customerstate + "')]")));
			wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.visibilityOf(firstnamefld));
			log(LogStatus.INFO, "Select customer State: " + customerstate);
		}
	}
	
	public String getCustomerState() {
		return appiumdriver.findElement(By.id("customerDetailsState")).getAttribute("value");
	}
	
	public void clickSaveCustomerButton() {
		tap(savebtn);
		log(LogStatus.INFO, "Click New customer Save button");
	}
	
	public VNextCustomersScreen clickBackButton() {
		clickScreenBackButton();
		log(LogStatus.INFO, "Click New Customer screen Back button");
		return new VNextCustomersScreen(appiumdriver);
	}

}
