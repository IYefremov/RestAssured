package com.cyberiansoft.test.bo.pageobjects.webpages;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.baseutils.CustomDateProvider;
import com.cyberiansoft.test.bo.webelements.*;
import com.cyberiansoft.test.dataclasses.ServiceData;
import com.cyberiansoft.test.enums.DateUtils;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.cyberiansoft.test.bo.utils.WebElementsBot.*;

public class InspectionsWebPage extends WebPageWithFilter {

	public final static String WOTABLE_DATE_COLUMN_NAME = "Date";

	@FindBy(id = "ctl00_ctl00_Content_Main_cpFilterer")
	private WebElement searchtab;

	@FindBy(xpath = "//a[text()='Search']")
	private WebElement searchbtn;

	// Search Panel
	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboCustomer_Input")
	private TextField searchcustomercmb;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboCustomer_DropDown")
	private DropDown searchcustomerdd;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_ddlTimeframe_Input")
	private ComboBox searchtimeframecmb;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_ddlTimeframe_DropDown")
	private DropDown searchtimeframedd;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboType_Input")
	private ComboBox searchtypecmb;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboType_DropDown")
	private DropDown searchtypedd;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboEmployee_Input")
	private TextField searchtechniciancmb;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboEmployee_DropDown")
	private DropDown searchtechniciandd;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboStatuses_Input")
	private ComboBox searchstatuscmb;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboStatuses_DropDown")
	private DropDown searchstatusdd;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_tbStock")
	private TextField searchstocknofld;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_tbRO")
	private TextField searchronofld;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_ddlWO")
	private TextField searchwocmb;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_tbVIN")
	private TextField searchvinfld;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_tbEstimationNum")
	private TextField inspectionnumberfld;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_comboView_Input")
	private WebElement searchviewcmb;

	@FindBy(xpath = "//*[@for='ctl00_ctl00_Content_Main_ctl03_filterer_cbArchived']")
	private WebElement searcharchivedchck;

	@FindBy(id = "ctl00_ctl00_Content_Main_ctl03_filterer_BtnFind")
	private WebElement findbtn;

	/////////////////

	@FindBy(id = "tbServices")
	private WebTable servicestable;

	@FindBy(xpath = "//button[@class='btn icon ok middle' and @value='1']")
	private WebElement serviceapprovebtn;

	@FindBy(xpath = "//button[@class='btn icon cancel middle' and @value='2']")
	private WebElement servicedeclinebtn;

	@FindBy(xpath = "//button[@class='btn icon ok middle' and @name='ap']")
	private WebElement approveallbtn;

	@FindBy(id = "btnGeneralApprove")
	private WebElement servicegeneralapprovebtn;

	@FindBy(id = "btnGeneralSimpleApprove")
	private WebElement approveandsubmitbtn;

	@FindBy(id = "ctl00_ctl00_Content_Main_gv_ctl00")
	private WebTable inspectionstable;

	@FindBy(id = "ctl00_ctl00_Content_Main_gv_ctl00_ctl04_btnDelete")
	private WebElement deleteinspectionbtn;

	@FindBy(xpath = "//table[@id='ctl00_ctl00_Content_Main_gv_ctl00']//a[@data-entitytype]")
	private List<WebElement> inspectionsList;

	@FindBy(xpath = "//table[@id='ctl00_ctl00_Content_Main_gv_ctl00']//a[@data-entitytype]/following::td[1]")
	private List<WebElement> inspectionsDataList;

	@FindBy(className = "updateProcess")
	private WebElement updateProcess;

	@FindBy(xpath = "//a[contains(text(), 'RO')]")
	//todo change the locator to verify only 'by RO' duplicates (after the inspectiontypes will be corrected)
	private WebElement duplicateByRO;

	@FindBy(xpath = "//a[contains(text(), 'by VIN')]")
	private WebElement duplicateByVIN;

	@FindBy(xpath = "//a[contains(text(), 'by VIN / RO#')]")
	private WebElement duplicateByVINandRO;

	@FindBy(xpath = "//input[contains(@id, 'filterer_dpFrom_dateInput') and contains (@type, 'text')]")
	private WebElement fromDateField;

	@FindBy(xpath = "//input[contains(@id, 'filterer_dpTo_dateInput') and contains (@type, 'text')]")
	private WebElement toDateField;

	@FindBy(xpath = "//input[contains(@id, 'filterer_dpFrom_dateInput_ClientState')]")
	private WebElement fromDateClientsField;

	@FindBy(xpath = "//input[contains(@id, 'filterer_dpTo_dateInput_ClientState')]")
	private WebElement toDateClientsField;

	public InspectionsWebPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
	}

	public void setTimeFrame(String from, String to) {
		wait.until(ExpectedConditions.elementToBeClickable(fromDateField)).clear();
		fromDateField.sendKeys(from);
		wait.until(ExpectedConditions.elementToBeClickable(toDateField)).clear();
		toDateField.sendKeys(to);
		try {
			wait.until(ExpectedConditions.attributeContains(fromDateClientsField, "value", from));
			wait.until(ExpectedConditions.attributeContains(toDateClientsField, "value", to));
		} catch (Exception ignored) {
		}
	}

	public boolean searchPanelIsExpanded() {
		wait.until(ExpectedConditions.visibilityOf(searchtab));
		return searchtab.getAttribute("class").contains("open");
	}

	public void makeSearchPanelVisible() {
		if (!searchPanelIsExpanded()) {
			click(searchbtn);
		}
		wait.until(ExpectedConditions.visibilityOf(searchcustomercmb.getWrappedElement()));
	}

	public void verifyInspectionsTableColumnsAreVisible() {
		wait.until(ExpectedConditions.visibilityOf(inspectionstable.getWrappedElement()));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("chkAllInspections")));
		Assert.assertTrue(inspectionstable.tableColumnExists("Status"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Inspection#"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Date"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Stock#"));
		Assert.assertTrue(inspectionstable.tableColumnExists("RO#"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Type"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Vehicle Info"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Mileage"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Customer"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Technician"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Advisor"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Amount"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Approved"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Archived"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Duplicates"));
		Assert.assertTrue(inspectionstable.tableColumnExists("Action"));
	}

	public void verifySearchFieldsAreVisible() {
		wait.until(ExpectedConditions.visibilityOf(searchcustomercmb.getWrappedElement()));
		Assert.assertTrue(searchcustomercmb.isDisplayed());
		Assert.assertTrue(searchtechniciancmb.isDisplayed());
		Assert.assertTrue(searchtypecmb.isDisplayed());
		Assert.assertTrue(searchstocknofld.isDisplayed());
		Assert.assertTrue(searchwocmb.isDisplayed());
		wait.until(ExpectedConditions.visibilityOf(inspectionnumberfld.getWrappedElement()));
		Assert.assertTrue(inspectionnumberfld.isDisplayed());
		Assert.assertTrue(searchviewcmb.isDisplayed());
		Assert.assertTrue(searchtimeframecmb.isDisplayed());
		Assert.assertTrue(searcharchivedchck.isDisplayed());
		Assert.assertTrue(searchstatuscmb.isDisplayed());
		wait.until(ExpectedConditions.visibilityOf(searchronofld.getWrappedElement()));
		Assert.assertTrue(searchronofld.isDisplayed());
		Assert.assertTrue(searchvinfld.isDisplayed());
	}

	public WebTable getInspectionsTable() {
		return inspectionstable;
	}

	public int getInspectionsTableRowCount() {
		waitABit(3000);
		return inspectionstable.getTableRowCount();
	}

	public List<WebElement> getInspectionsTableRows() {
		return inspectionstable.getTableRows();
	}

	public void setInspectionNumberSearchCriteria(String inspnumber) {
		clearAndType(inspectionnumberfld, inspnumber);
	}

	public void setStockSearchCriteria(String stock) {
		clearAndType(searchstocknofld, stock);
	}

	public void setROSearchCriteria(String ronum) {
		clearAndType(searchronofld, ronum);
	}

	public void selectSearchCustomer(String customer) {
		wait.until(ExpectedConditions.elementToBeClickable(searchcustomercmb.getWrappedElement())).click();
		searchcustomercmb.clearAndType(customer);
		waitABit(1000);
		try {
			wait.until(ExpectedConditions.visibilityOf(searchcustomerdd.getWrappedElement()));
			searchcustomerdd.selectByVisibleText(customer);
		} catch (Exception ignored) {
		}
	}

	public void selectSearchTimeframe(String timeframe) {
		selectComboboxValue(searchtimeframecmb, searchtimeframedd, timeframe);
	}

	public void selectSearchType(String _type) {
		selectComboboxValue(searchtypecmb, searchtypedd, _type);
	}

	public void selectSearchTechnician(String technician, String technicianfull) {
		selectComboboxValueWithTyping(searchtechniciancmb, searchtechniciandd, technician, technicianfull);
	}

	public void selectSearchStatus(String status) {
		selectComboboxValue(searchstatuscmb, searchstatusdd, status);
	}

	public boolean inspectionExists(String inspectionnumber) {
		waitABit(2000);
		wait.until(ExpectedConditions.visibilityOf(inspectionstable.getWrappedElement()));
		return inspectionstable.getWrappedElement()
				.findElements(By.xpath(".//tr/td/a[contains(text(), '" + inspectionnumber + "')]")).size() > 0;
	}

	public void clickDuplicateByROLink() {
		wait.until(ExpectedConditions.elementToBeClickable(duplicateByRO)).click();
		waitForNewTab();
	}

	public void clickDuplicateByVINLink() {
		wait.until(ExpectedConditions.elementToBeClickable(duplicateByVIN)).click();
		waitForNewTab();
	}

	public void clickDuplicateByVINAndROLink() {
		wait.until(ExpectedConditions.elementToBeClickable(duplicateByVINandRO)).click();
		waitForNewTab();
	}

	public void deleteFirstInspection() {
		click(deleteinspectionbtn);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		waitForLoading();
	}

	public void clickFindButton() {
		clickAndWait(findbtn);
		wait.until(ExpectedConditions.invisibilityOf(updateProcess));
	}

	public void assertInspectionPrice(String inspnumber, String expectedprice) {
		makeSearchPanelVisible();
		setInspectionNumberSearchCriteria(inspnumber);
		clickFindButton();
		Assert.assertEquals(expectedprice,
				inspectionstable.getWrappedElement().findElement(By.xpath(".//tr/td[14]")).getText());
	}

	public boolean isInspectionApproved(String inspnumber) {
		boolean approved = false;
		searchInspectionByNumber(inspnumber);
		WebElement row = getTableRowWithInspection(inspnumber);
		if (row != null) {
			approved = row.findElement(By.xpath(".//img[@title='Approved']")).isDisplayed();
		} else {
			Assert.fail("Can't find " + inspnumber + " inspection");
		}
		return approved;
	}

	public void searchInspectionByNumber(String inspection) {
		makeSearchPanelVisible();
		setInspectionNumberSearchCriteria(inspection);
		clickFindButton();
		waitForLoading();
	}

	public int getTableRowWithInspectionsNumbers() {
		return inspectionstable.getTableColumnIndex("Inspection#");
	}

	public int getTableRowWithInspectionsApproved() {
		return inspectionstable.getTableColumnIndex("Approved");
	}

	public int getTableRowWithInspectionsReason() {
		return inspectionstable.getTableColumnIndex("Reason");
	}

	public int getTableRowWithInspectionsStatus() {
		return inspectionstable.getTableColumnIndex("Status");
	}

	public WebElement getTableRowWithInspection(String inspnumber) {
		List<WebElement> rows = getInspectionsTableRows();
		for (WebElement row : rows) {
			if (row.findElement(By.xpath(".//td[" + getTableRowWithInspectionsNumbers() + "]/a")).getText()
					.equals(inspnumber)) {
				return row;
			}
		}
		return null;
	}

	public void clickInspectionLink(String inspnumber) {
		WebElement row = getTableRowWithInspection(inspnumber);
		if (row != null) {
			row.findElement(By.xpath(".//a[contains(text(), '" + inspnumber + "')]")).click();
		} else {
			Assert.fail("Can't find " + inspnumber + " inspection");
		}
	}

	public void clickInspectionApproveMarker(String inspnumber) {
		WebElement row = getTableRowWithInspection(inspnumber);
		if (row != null) {
			clickApproveMarkerTableRow(row);
			acceptAlert();
		} else {
			Assert.fail("Can't find " + inspnumber + " inspection");
		}
	}

	public void declineInspectionByNumber(String inspnumber) {
		searchInspectionByNumber(inspnumber);
		clickInspectionApproveMarker(inspnumber);
		waitForNewTab();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String parent = it.next();
			String newwin = it.next();
			driver.switchTo().window(newwin);
			// Thread.sleep(5000);
			// driver.findElement(By.id("btnDecline")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDecline"))).click();
            // perform actions on new window
			driver.close();
			driver.switchTo().window(parent);
		}

		// Thread.sleep(3000);
	}

	public void approveInspectionByNumber(String inspnumber) {
		makeSearchPanelVisible();
		selectSearchTimeframe("Custom");
		setTimeFrame(CustomDateProvider.getCurrentDateInShortFormat(), CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		setInspectionNumberSearchCriteria(inspnumber);
		clickFindButton();
		waitForLoading();
		clickInspectionApproveMarker(inspnumber);
		waitForNewTab();
		// driver.findElement(By.xpath("//button[contains(text(),'Approve')]"));
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String parent = it.next();
			String newwin = it.next();
			driver.switchTo().window(newwin);
			// Thread.sleep(10000);
			driver.findElement(By.xpath("//button[@class='btn icon ok']")).click();
			// Thread.sleep(1000);
			driver.findElement(By.id("btnApprove")).click();
			// Thread.sleep(3000);
			// perform actions on new window
			driver.close();
			driver.switchTo().window(parent);
		}

		// Thread.sleep(3000);
	}

	public void approveInspectionLinebylineApprovalByNumber(String inspnumber, String serviceapprove,
															String servicedecline) {
		searchInspectionByNumber(inspnumber);
		clickInspectionApproveMarker(inspnumber);
		waitForNewTab();
		// driver.findElement(By.xpath("//button[contains(text(),'Approve')]"));
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String parent = it.next();
			String newwin = it.next();
			driver.switchTo().window(newwin);
			// Thread.sleep(5000);
			serviceApprove(serviceapprove);
			serviceDecline(servicedecline);
			servicegeneralapprovebtn.click();
			waitABit(5000);
			// perform actions on new window
			driver.close();
			driver.switchTo().window(parent);
		}

		waitABit(3000);
	}

	public void approveInspectionLinebylineApprovalByNumber(String inspnumber, List<ServiceData> servicesData) {
		searchInspectionByNumber(inspnumber);
		clickInspectionApproveMarker(inspnumber);
		waitForNewTab();
		// driver.findElement(By.xpath("//button[contains(text(),'Approve')]"));
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String parent = it.next();
			String newwin = it.next();
			driver.switchTo().window(newwin);
			for (ServiceData serviceData : servicesData) {
				switch (serviceData.getServiceStatus()) {
					case APPROVED:
						serviceApprove(serviceData.getServiceName());
						break;
					case DECLINED:
						serviceDecline(serviceData.getServiceName());
						break;
				}
			}
			servicegeneralapprovebtn.click();
			waitABit(5000);
			// perform actions on new window
			driver.close();
			driver.switchTo().window(parent);
		}

		waitABit(3000);
	}

	public String getInspectionApprovedTotal(String inspnumber) {
		String totalapproved = "";
		clickInspectionLink(inspnumber);
		waitForNewTab();
		// driver.findElement(By.xpath("//button[contains(text(),'Approve')]"));
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String parent = it.next();
			String newwin = it.next();
			driver.switchTo().window(newwin);
			BaseUtils.waitABit(2000);

			WaitUtils.waitUntilElementIsClickable(driver.findElement(By.xpath("//tr[@class='custom-total-row']/td[2]/div")));
			WebElement totalrow = driver.findElements(By.xpath("//tr[@class='custom-total-row']/td[2]/div")).get(driver.findElements(By.xpath("//tr[@class='custom-total-row']/td[2]/div")).size() - 2);
			totalapproved = totalrow.getText();
			driver.close();
			driver.switchTo().window(parent);
		}
		return totalapproved;
	}

	public void verifyVINIsPresentForInspection(String inspnumber, String VIN) {
		WebElement row = getTableRowWithInspection(inspnumber);
		Actions move = new Actions(driver);
		move.moveToElement(row.findElement(By.xpath(".//span[contains(@id, 'lblVin')]"))).perform();
		Assert.assertTrue(driver.findElement(
				By.xpath("//div[contains(@id, 'RadToolTipWrapper')]/table/tbody/tr[2]/td[2]")).getText()
				.contains(VIN));
	}

	public String getInspectionAmountApproved(String inspnumber) {
		String amaounapproved = "";
		WebElement row = getTableRowWithInspection(inspnumber);
		if (row != null) {
			amaounapproved = row.findElement(By.xpath(".//td[" + getTableRowWithInspectionsApproved() + "]")).getText();
		} else {
			Assert.fail("Can't find " + inspnumber + " inspection");
		}
		return amaounapproved;
	}

	public String getInspectionReason(String inspnumber) {
		String reason = "";
		WebElement row = getTableRowWithInspection(inspnumber);
		if (row != null) {
			reason = row.findElement(By.xpath(".//td[" + getTableRowWithInspectionsReason() + "]")).getText();
		} else {
			Assert.fail("Can't find " + inspnumber + " inspection");
		}
		return reason;
	}

	public String getInspectionStatus(String inspnumber) {
		String status = "";
		WebElement row = getTableRowWithInspection(inspnumber);
		if (row != null) {
			status = row.findElement(By.xpath(".//td[" + getTableRowWithInspectionsStatus() + "]/img"))
					.getAttribute("title");
		} else {
			Assert.fail("Can't find " + inspnumber + " inspection");
		}
		return status;
	}

	public boolean isServicePresentInInspectioncontentTable(String servicesname) {
		WebElement servicestable = driver.findElement(By.id("contentTable"));
		return servicestable.findElements(By.xpath("//table/tbody/tr/td[contains(text(), '" + servicesname + "')]"))
				.size() > 0;
	}

	public void serviceApprove(String serviceapprove) {
		selectService(serviceapprove);
		click(serviceapprovebtn);
	}

	public void serviceDecline(String servicedecline) {
		selectService(servicedecline);
		click(servicedeclinebtn);
	}

	public void selectService(String servicename) {

		List<WebElement> allRows = servicestable.getWrappedElement().findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
			if (row.getText().contains(servicename)) {
				row.findElement(By.name("cbService")).click();
				return;
			}
		}
	}

	public WebElement clickSelectButtonForInspection(String inspectionnumber) {
		WebElement row = getTableRowWithInspection(inspectionnumber);
		if (row != null) {
			Actions act = new Actions(driver);
			act.moveToElement(row.findElement(By.xpath(".//span[text()='Select']"))).click().build().perform();
			waitABit(300);
			act.click(row.findElement(By.xpath(".//span[text()='Select']"))).build().perform();
		}
		return row;
	}

	public void clickInspectionSelectExpandableMenu(String inspectionnumber, String menuitem) {
		WebElement row = clickSelectButtonForInspection(inspectionnumber);
		if (row != null) {
			wait.until(ExpectedConditions.visibilityOf(row.findElement(By.xpath(".//div[@class='rmSlide']"))));
			Actions act = new Actions(driver);
			if (!getTableRowWithInspection(inspectionnumber).findElement(By.xpath(".//span[text()='" + menuitem + "']"))
					.isDisplayed()) {
				act.moveToElement(getTableRowWithInspection(inspectionnumber)
						.findElement(By.xpath(".//a[@class='rmBottomArrow']"))).perform();
			}
			wait.until(ExpectedConditions.elementToBeClickable(getTableRowWithInspection(inspectionnumber)
					.findElement(By.xpath(".//span[text()='" + menuitem + "']"))));
			act.click(getTableRowWithInspection(inspectionnumber)
					.findElement(By.xpath(".//span[text()='" + menuitem + "']"))).perform();
		} else {
			Assert.fail("Can't find " + inspectionnumber + " incpection");
		}
	}

	public boolean sendInspectionEmail(String inspectionnumber, String email) {
		try {
			clickInspectionSelectExpandableMenu(inspectionnumber, "Send Email");
			driver.findElement(By.id("ctl00_ctl00_Content_Main_popupEmailRecipients")).clear();
			driver.findElement(By.id("ctl00_ctl00_Content_Main_popupEmailRecipients")).sendKeys(email);
			clickAndWait(driver.findElement(By.id("ctl00_ctl00_Content_Main_btnSendEmail")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void clickSendCustomEmail(String inspectionnumber) {
		String mainWindowHandle = driver.getWindowHandle();
		clickInspectionSelectExpandableMenu(inspectionnumber, "Send Custom Email");
		waitForNewTab();
		for (String activeHandle : driver.getWindowHandles()) {
			if (!activeHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(activeHandle);
			}
		}
	}

	public void clickEditInspection(String inspectionnumber) {
		String mainWindowHandle = driver.getWindowHandle();
		clickInspectionSelectExpandableMenu(inspectionnumber, "Edit");
		waitForNewTab();
		for (String activeHandle : driver.getWindowHandles()) {
			if (!activeHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(activeHandle);
			}
		}
//		waitForLoading();
	}

	public String getFirstInspectionNumber() {
		return getFirstInspection(inspectionsList);
	}

	public String getFirstInspectionDate() {
		return getFirstInspection(inspectionsDataList);
	}

	private String getFirstInspection(List<WebElement> inspectionsDataList) {
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElements(inspectionsDataList)).get(0).getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getChangedInspectionDate(int day) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DateUtils.FULL_DATE_FORMAT.getFormat());
		LocalDate localDate = LocalDate.parse(getFirstInspectionDate(), dateFormat.withZone(ZoneId.of(DateUtils.ZONE_ID.getFormat())));
		localDate = localDate.minusMonths(1);
		return localDate.withDayOfMonth(day).format(dateFormat);
	}

	public String getInspectionsWindowHandle() {
		return driver.getWindowHandle();
	}

	public void clickInspectionMediaAction(String inspectionnumber) {
		String mainWindowHandle = driver.getWindowHandle();
		clickInspectionSelectExpandableMenu(inspectionnumber, "Media");
		waitForNewTab();
		for (String activeHandle : driver.getWindowHandles()) {
			if (!activeHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(activeHandle);
			}
		}
	}
}