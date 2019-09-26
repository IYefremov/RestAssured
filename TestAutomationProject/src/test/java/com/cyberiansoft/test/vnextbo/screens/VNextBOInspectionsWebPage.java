package com.cyberiansoft.test.vnextbo.screens;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
public class VNextBOInspectionsWebPage extends VNextBOBaseWebPage {
	
	@FindBy(xpath = "//ul[@data-automation-id='inspectionsList']")
	private WebElement inspectionsList;
	
	@FindBy(xpath = "//div[@class='entity-details__content' and @data-bind='visible: isDetailsVisible']")
	private WebElement inspectionDetailsPanel;
	
	@FindBy(xpath = "//table[@data-automation-id='inspectionsDetailsServicesList']")
	private WebElement inspectionServicesList;
	
	@FindBy(xpath = "//ul[@data-automation-id='inspectionsDetailsDamagesList']")
	private WebElement imageLegend;
	
	@FindBy(xpath = "//button[@data-automation-id='inspectionsDetailsPrintButton']")
	private WebElement printInspectionIcon;
	
	@FindBy(xpath = "//button[@data-automation-id='inspectionsDetailsApproveButton']")
	private WebElement approveInspectionIcon;
	
	@FindBy(id = "inspectiontypes-search")
	private WebElement searchInspectionsPanel;
	
	@FindBy(id = "advSearchEstimation-freeText")
	private WebElement searchFld;
	
	@FindBy(xpath = "//*[@data-bind='text: filterInfoString']")
	private WebElement filterInfoText;
	
	@FindBy(id = "advSearchEstimation-search")
	private WebElement searchFilterBtn;
	
	@FindBy(xpath = "//i[contains(@data-bind, 'click: clear,')]")
	private WebElement clearFilterBtn;

	@FindBy(xpath = "//button[contains(text(), 'Approve and Complete')]")
	private WebElement approveAndCompleteButton;

	@FindBy(xpath = "//div[contains(@class, 'status__label')]")
	private List<WebElement> inspectionStatusLabels;

	@FindBy(xpath = "//a[@data-bind='click: showTermsAndConditions']")
	private WebElement termsAndConditionsLink;

	@FindBy(xpath = "//a[@data-bind='click: showPrivacyPolicy']")
	private WebElement privacyPolicyLink;

	@FindBy(xpath = "//iframe[@name='intercom-messenger-frame']")
	private WebElement intercomMessengerFrame;

	@FindBy(xpath = "//div[contains(@class,'intercom-messenger-new-conversation')]")
	private WebElement intercomNewConversionSpace;

	@FindBy(xpath = "//iframe[@name='intercom-launcher-frame']")
	private WebElement intercomLauncherFrame;

	@FindBy(xpath = "//div[contains(@class, 'intercom-launcher')]")
	private WebElement openCloseIntercomButton;

	public VNextBOInspectionsWebPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOf(inspectionsList));
	}

	public boolean isInspectionsListDisplayed() {
		return isElementDisplayed(inspectionsList);
	}

	public boolean isInspectionDetailsPanelDisplayed() {
		return isElementDisplayed(inspectionDetailsPanel);
	}

	public boolean isSearchFieldDisplayed() {
		return isElementDisplayed(searchFld);
	}

	public boolean isTermsAndConditionsLinkDisplayed() {
		return isElementDisplayed(termsAndConditionsLink);
	}

	public boolean isPrivacyPolicyLinkDisplayed() {
		return isElementDisplayed(privacyPolicyLink);
	}

	public boolean isIntercomButtonDisplayed() {
		driver.switchTo().frame(intercomLauncherFrame);
		boolean isIntercomButtonDisplayed = isElementDisplayed(openCloseIntercomButton);
		driver.switchTo().defaultContent();
		return isIntercomButtonDisplayed;
	}

	public void clickTermsAndConditionsLink() {
		termsAndConditionsLink.click();;
	}

	public void clickPrivacyPolicyLink() {
		privacyPolicyLink.click();;
	}

	public void openIntercomMessenger() {
		driver.switchTo().frame(intercomLauncherFrame);
		openCloseIntercomButton.click();
		driver.switchTo().defaultContent();
	}

	public boolean isIntercomMessengerOpened() {
		driver.switchTo().frame(intercomMessengerFrame);
		boolean isMessengerOpened =  isElementDisplayed(intercomNewConversionSpace);
		driver.switchTo().defaultContent();
		return isMessengerOpened;
	}

	public void closeIntercom() {
		driver.switchTo().frame(intercomLauncherFrame);
		openCloseIntercomButton.click();
		driver.switchTo().defaultContent();
	}

	public void selectInspectionInTheList(String inspnumber) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='entity-list__item__description']/div/b[text()='" + inspnumber + "']"))).click();
		//inspectionsList.findElement(By.xpath(".//div[@class='entity-list__item__description']/div/b[text()='" + inspnumber + "']")).click();
		waitABit(4000);
	}
	
	private WebElement getInspectionCell(String inspnumber) {
		WebElement inspcell = null;
		List<WebElement> inspcells = inspectionsList.findElements(By.xpath("./li[@role='option']"));
		for (WebElement cell : inspcells)
			if (cell.findElement(By.xpath(".//div[@class='entity-list__item__description']/div/b")).getText().trim().equals(inspnumber)) {
				inspcell = cell;
				break;
			}
		return inspcell;
	}
	
	public String getInspectionStatus(String inspnumber) {
		String inspstatus = "";
		WebElement inspcell = getInspectionCell(inspnumber);
		if (inspcell != null)
			inspstatus = inspcell.findElement(By.xpath(".//div[@class='entity-list__item__status__label']")).getText().trim(); 
		else
			Assert.assertTrue(false, "Can't find inpection: " + inspnumber);
		return inspstatus;
	}
		
	public boolean isServicePresentForSelectedInspection(String servicename) {
		return inspectionServicesList.findElements(By.xpath("./tbody/tr/td[text()='" + servicename + "']")).size() > 0;
	}
	
	public boolean isServiceNotesIconDisplayed(String servicename) {
		WebElement sepviserow = inspectionServicesList.findElement(By.xpath("./tbody/tr/td[text()='" + servicename + "']/.."));
		return sepviserow.findElement(By.xpath("./td[@class='notes__service-table--centered']/i[@title='Notes']")).isDisplayed();
	}
	
	public boolean isMatrixServiceExists(String matrixservicename) {
		WebElement matrixsepviserow = inspectionServicesList.findElement(By.xpath(".//tr[@class='entity-details__matrix']"));
		return matrixsepviserow.findElement(By.xpath("./td[contains(text(), '" +  matrixservicename + "')]")).isDisplayed();
	}

	public List<WebElement> getAllMatrixServicesRows(String matrixservicename) {
		return inspectionServicesList.findElements(By.xpath(".//tr[@class='entity-details__matrix']"));
	}

	
	public boolean isImageExistsForMatrixServiceNotes(WebElement matrixsepviserow) {
		boolean exists = false;
		matrixsepviserow.findElement(By.xpath("./td[@class='notes__service-table--centered']/i[@title='Notes']")).click();
		WebElement notesmodaldlg = new WebDriverWait(driver, 30)
		  .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notesViewer"))));
		exists = notesmodaldlg.findElement(By.xpath("//div[@class='image-notes__preview--modal']")).isDisplayed();
		new WebDriverWait(driver, 30)
				  .until(ExpectedConditions.elementToBeClickable(notesmodaldlg.findElement(By.xpath(".//button[@class='close']")))).click();
		waitABit(500);
		return exists;	
	}
	
	public void clickServiceNotesIcon(String servicename) {
		WebElement sepviserow = inspectionServicesList.findElement(By.xpath("./tbody/tr/td[text()='" + servicename + "']/.."));
		sepviserow.findElement(By.xpath("./td[@class='notes__service-table--centered']/i[@title='Notes']")).click();
	}
	
	public boolean isImageExistsForServiceNote(String servicename) {
		boolean exists;
		clickServiceNotesIcon(servicename);
		WebElement notesmodaldlg = new WebDriverWait(driver, 30)
		  .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notesViewer"))));
		exists = notesmodaldlg.findElement(By.xpath("//div[@class='image-notes__preview--modal']")).isDisplayed();
		new WebDriverWait(driver, 30)
				  .until(ExpectedConditions.elementToBeClickable(notesmodaldlg.findElement(By.xpath(".//button[@class='close']")))).click();
		waitABit(500);
		return exists;
	}
	
	public VNextBOInspectionsWebPage clickInspectionApproveButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(approveInspectionIcon)).click();
            waitForLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return this;
	}
	
	public boolean isInspectionApproveButtonVisible() {
		return approveInspectionIcon.isDisplayed();
	}
	
	public void approveInspection(String approveNotes) {
		String parent = driver.getWindowHandle();
		clickInspectionApproveButton();
		VNextBOConfirmationDialog confirmdialog = new VNextBOConfirmationDialog();
		confirmdialog.clickYesButton();
		waitForNewTab();
		String newwin = "";
		for(String window:driver.getWindowHandles()){
			if(!window.equals(parent)){
				newwin = window;
			}
		}
		driver.switchTo().window(newwin);
		driver.findElement(By.xpath("//p/button[@type='submit' and @class='btn icon ok']")).click();
		waitLong.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("txtAreaNotes"))));
		driver.findElement(By.name("txtAreaNotes")).sendKeys(approveNotes);
		driver.findElement(By.xpath("//button[@id='btnApprove']")).click();
		waitABit(5000);
		driver.close();
		driver.switchTo().window(parent);
		driver.navigate().refresh();
	}

	public void approveInspection() {
		String parent = driver.getWindowHandle();
		clickInspectionApproveButton();
		new VNextBOConfirmationDialog().clickYesButton();
		waitForNewTab();
		String newWindow = "";
		for(String window: driver.getWindowHandles()){
			if(!window.equals(parent)){
				newWindow = window;
			}
		}
		driver.switchTo().window(newWindow);
		waitForLoading();
		driver.close();
		driver.switchTo().window(parent);
		driver.navigate().refresh();
	}
	
	public void declineInspection(String declineNotes) {
		String parent = driver.getWindowHandle();
		clickInspectionApproveButton();
		VNextBOConfirmationDialog confirmdialog = new VNextBOConfirmationDialog();
		confirmdialog.clickYesButton();
		waitForNewTab();
		String newwin = "";
		for(String window:driver.getWindowHandles()){
			if(!window.equals(parent)){
				newwin = window;
			}
		}
		driver.switchTo().window(newwin);
		driver.findElement(By.xpath("//p/button[@type='submit' and @class='btn icon cancel']")).click();
		new WebDriverWait(driver, 60)
		  .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("txtDeclineNotes"))));
		/*List<WebElement> serviceschkboxes = driver.findElements(By.name("cbService"));
		for (WebElement serviceschkbox : serviceschkboxes)
			serviceschkbox.click();
		driver.findElement(By.xpath("//button[@id='btnCancel']")).click();*/
		driver.findElement(By.name("txtDeclineNotes")).sendKeys(declineNotes);
		driver.findElement(By.xpath("//button[@class='btn icon cancel' and @id='btnDecline']")).click();
		waitABit(5000);
		driver.close();
		driver.switchTo().window(parent);
		driver.navigate().refresh();
	}
	
	public boolean isImageLegendContainsBreakageIcon(String brackageicontype) {
		return imageLegend.findElements(By.xpath("./li[contains(text(), '" + brackageicontype + "')]")).size() > 0;
	}
	
	public String getSelectedInspectionCustomerName() {
		return inspectionDetailsPanel.findElement(By.xpath(".//span[@data-bind='text:clientName']")).getText();
	}
	
	public String getSelectedInspectionTotalAmauntValue() {
		return inspectionDetailsPanel.findElement(By.xpath(".//th[@data-bind='text: amount']")).getText();
	}
	
	public VNextBOInspectionInfoWebPage clickSelectedInspectionPrintIcon() {
		String mainWindowHandle = driver.getWindowHandle();
		printInspectionIcon.click();
		waitForNewTab();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		for (String activeHandle : driver.getWindowHandles()) {
			if (!activeHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(activeHandle);
			}
		}
		return PageFactory.initElements(
			driver, VNextBOInspectionInfoWebPage.class);
	}
	
	public VNextBOAdvancedSearchInspectionDialog openAdvancedSearchPanel() {
		clickExpandAdvancedSearchPanel();
		if (driver.findElement(By.xpath("//div[@data-bind='click: showDefaultAdvancedSearch']")).isDisplayed())
			driver.findElement(By.xpath("//div[@data-bind='click: showDefaultAdvancedSearch']")).click();
		return PageFactory.initElements(
				driver, VNextBOAdvancedSearchInspectionDialog.class);
	}
	
	public void clickExpandAdvancedSearchPanel() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("advSearchEstimation-caret")))).click();
	}
	
	public boolean isSavedAdvancedSearchFilterExists(String filterName) {
		boolean exists = false;
		if (driver.findElements(By.id("advSearchEstimation-savedSearchList")).size() > 0)
			exists = driver.findElement(By.id("advSearchEstimation-savedSearchList"))
			.findElements(By.xpath(".//div/span[text()='" + filterName + "']")).size() > 0;
		return exists;
	}
	
	public String getCustomSearchInfoTextValue() {
		return filterInfoText.getText();
	}
	
	public void deleteSavedAdvancedSearchFilter(String filterName) {		
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openSavedAdvancedSearchFilter(filterName);		
		advancedserchdialog.deleteSavedSearchFilter();
	}
	
	public VNextBOAdvancedSearchInspectionDialog openSavedAdvancedSearchFilter(String filterName) {
		waitABit(2000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("advSearchEstimation-savedSearchList"))
			.findElement(By.xpath(".//div/span[text()='" + filterName + "']/../i"))).perform();
		
		driver.findElement(By.id("advSearchEstimation-savedSearchList"))
			.findElement(By.xpath(".//div/span[text()='" + filterName + "']/../i")).click();
		return PageFactory.initElements(
				driver, VNextBOAdvancedSearchInspectionDialog.class);
	}
	
	public void advancedSearchInspectionByCustomer(String customername) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();
		advancedserchdialog.selectAdvancedSearchByCustomer(customername);
		advancedserchdialog.clickSearchButton();
	}
	
	
	public void advancedSearchInspectionByStatusAndInspectionNumber(String inspNumber, String statussearch) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();
		advancedserchdialog.setAdvancedSearchInspectionByStatusAndInspectionNumber(inspNumber, statussearch);
		advancedserchdialog.clickSearchButton();
	}
	
	public void advancedSearchInspectionByStatus(String statussearch) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();
		advancedserchdialog.selectAdvancedSearchByStatus(statussearch);
		advancedserchdialog.clickSearchButton();
	}
	
	public void advancedSearchInspectionByStockNumber(String stocknumber) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();
		advancedserchdialog.setAdvancedSearchByStockNumber(stocknumber);
		advancedserchdialog.clickSearchButton();
	}
	
	public void advancedSearchInspectionByPONumber(String ponumber) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();
		advancedserchdialog.setAdvancedSearchByPONumber(ponumber);
		advancedserchdialog.clickSearchButton();
	}
	
	public void advancedSearchInspectionByRONumber(String ronumber) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();
		advancedserchdialog.setAdvancedSearchByRONumber(ronumber);
		advancedserchdialog.clickSearchButton();
	}
	
	public void advancedSearchInspectionByVIN(String VIN) {
		VNextBOAdvancedSearchInspectionDialog advancedserchdialog = openAdvancedSearchPanel();		
		advancedserchdialog.setAdvencedSearchVINValue(VIN);
		advancedserchdialog.clickSearchButton();
	}
	
	public void searchInspectionByText(String searchtext) {
		setSearchFieldValue(searchtext);
		clickSearchFilterButton();
	}
	
	public String getSearchFieldValue() {
		return searchFld.getAttribute("value");
	}
	
	public void setSearchFieldValue(String searchtext) {
		searchFld.clear();
		searchFld.sendKeys(searchtext);
	}
	
	public void clickSearchFilterButton() {
		searchFilterBtn.click();
		wait.until(ExpectedConditions.visibilityOf(clearFilterBtn));
	}
	
	public void clickClearFilterIcon() {
		clearFilterBtn.click();
		wait.until(ExpectedConditions.invisibilityOf(clearFilterBtn));
	}

	public String getFirstInspectionStatus() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(inspectionStatusLabels.get(0))).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
