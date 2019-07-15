package com.cyberiansoft.test.vnextbo.screens;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VNextBOCompanyInfoWebPage extends VNextBOBaseWebPage {

    @FindBy(id = "companyInfo-companyName")
    private WebElement companyNameInputField;

    @FindBy(id = "companyInfo-address1")
    private WebElement address1InputField;

    @FindBy(id = "companyInfo-address2")
    private WebElement address2InputField;

    @FindBy(id = "companyInfo-city")
    private WebElement cityInputField;

    @FindBy(id = "companyInfo-country-list")
    private WebElement companyDropDown;

    @FindBy(id = "companyInfo-zip")
    private WebElement zipInputField;

    @FindBy(xpath = "//div[@class='amt-widget-phone-input']/input")
    private WebElement phoneInputField;

    @FindBy(id = "companyInfo-email")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@aria-owns='companyInfo-country_listbox']")
    private WebElement countryInputField;

    @FindBy(xpath = "//span[@aria-controls='companyInfo-country_listbox']")
    private WebElement countryArrow;

    @FindBy(id = "companyInfo-country-list")
    private WebElement countryDropDown;

    @FindBy(xpath = "//div[@id='companyInfo-phone-wrapper']//input[@class='k-input']")
    private WebElement phoneCodeInputField;

    @FindBy(xpath = "//div[@class='amt-widget-phone-codes']//span[@aria-label='select']")
    private WebElement phoneCodeArrow;

    @FindBy(id = "companyInfo-country-list")
    private WebElement phoneCodeDropDown;

    @FindBy(xpath = "//input[@aria-owns='companyInfo-state_listbox']")
    private WebElement stateProvinceInputField;

    @FindBy(xpath = "//span[@aria-controls='companyInfo-state_listbox']")
    private WebElement stateProvinceArrow;

    @FindBy(id = "companyInfo-state-list")
    private WebElement stateProvinceDropDown;

    @FindBy(xpath = "//ul[@id='companyInfo-country_listbox']/li")
    private List<WebElement> countryListBoxOptions;

    @FindBy(xpath = "//ul[@id='companyInfo-state_listbox']/li")
    private List<WebElement> stateProvinceListBoxOptions;

    @FindBy(xpath = "//ul[@data-role='staticlist']//div[@class='amt-widget-phone-codes-item-code']")
    private List<WebElement> phoneCodeListBoxOptions;

    @FindBy(xpath = "//section[@id='company-info-view']//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='notification__message']/b[contains(text(), 'Success')]")
    private WebElement successMessage;

    public VNextBOCompanyInfoWebPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }

    public VNextBOCompanyInfoWebPage setCompanyName(String companyName) {
        setData(companyNameInputField, companyName);
        return this;
    }

    public VNextBOCompanyInfoWebPage setAddressLine1(String addressLine1) {
        setData(address1InputField, addressLine1);
        return this;
    }

    public VNextBOCompanyInfoWebPage setAddressLine2(String addressLine2) {
        setData(address2InputField, addressLine2);
        return this;
    }

    public VNextBOCompanyInfoWebPage setCity(String city) {
        setData(cityInputField, city);
        return this;
    }

    public VNextBOCompanyInfoWebPage setZip(String zip) {
        setData(zipInputField, zip);
        return this;
    }

    public VNextBOCompanyInfoWebPage setPhoneCode(String phoneCode) {
        clickElement(phoneCodeArrow);
        selectPhoneCode(phoneCode);
        return this;
    }

    public VNextBOCompanyInfoWebPage setPhone(String phone) {
        setData(phoneInputField, phone);
        return this;
    }

    public VNextBOCompanyInfoWebPage setEmail(String email) {
        setData(emailInputField, email);
        return this;
    }

    public VNextBOCompanyInfoWebPage setCountry(String country) {
        clickElement(countryArrow);
        selectCountry(country);
        return this;
    }

    public VNextBOCompanyInfoWebPage setStateProvince(String stateProvince) {
        clickElement(stateProvinceArrow);
        selectStateProvince(stateProvince);
        return this;
    }

    private VNextBOCompanyInfoWebPage selectCountry(String country) {
        selectOptionInDropDown(countryDropDown, countryListBoxOptions, country);
        return this;
    }

    private VNextBOCompanyInfoWebPage selectPhoneCode(String phoneCode) {
        selectOptionInDropDown(phoneCodeDropDown, phoneCodeListBoxOptions, phoneCode, true);
        return this;
    }

    private VNextBOCompanyInfoWebPage selectStateProvince(String state) {
        selectOptionInDropDown(stateProvinceDropDown, stateProvinceListBoxOptions, state);
        return this;
    }

    public VNextBOCompanyInfoWebPage clickSaveButton() {
        clickElement(saveButton);
        return this;
    }

    public boolean isSuccessNotificationDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public String getCompanyValue() {
        return getInputFieldValue(companyNameInputField);
    }

    public String getAddressLine1Value() {
        return getInputFieldValue(address1InputField);
    }

    public String getAddressLine2Value() {
        return getInputFieldValue(address2InputField);
    }

    public String getCityValue() {
        return getInputFieldValue(cityInputField);
    }

    public String getCountryValue() {
        return getInputFieldValue(countryInputField);
    }

    public String getStateProvinceValue() {
        return getInputFieldValue(stateProvinceInputField);
    }

    public String getZipValue() {
        return getInputFieldValue(zipInputField);
    }

    public String getPhoneCodeValue() {
        return getInputFieldValue(phoneCodeInputField);
    }

    public String getPhoneValue() {
        return getInputFieldValue(phoneInputField);
    }

    public String getEmailValue() {
        return getInputFieldValue(emailInputField);
    }
}
