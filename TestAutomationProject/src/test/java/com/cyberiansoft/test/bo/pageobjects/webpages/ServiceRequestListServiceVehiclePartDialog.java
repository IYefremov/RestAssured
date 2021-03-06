package com.cyberiansoft.test.bo.pageobjects.webpages;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ServiceRequestListServiceVehiclePartDialog extends BaseWebPage {

	@FindBy(xpath = "//div[@class='serviceVehiclePartPopup']")
	private WebElement servicesVehiclePartPopup;

	@FindBy(xpath = "//select[@class='dualListLeftPart']")
	private WebElement availableVehiclePart;

	@FindBy(xpath = "//select[@class='listBoxVP']")
	private WebElement assignedVehiclePart;

	@FindBy(xpath = "//select[@class='dualListLeftPart']/option")
	private List<WebElement> availableVehiclePartOptions;

	@FindBy(xpath = "//select[@class='listBoxVP']/option")
	private List<WebElement> assignedVehiclePartOptions;

	@FindBy(xpath = "//div[@class='serviceVehiclePartPopup']//input[2]")
	private WebElement moveToTheRightButton;

	@FindBy(xpath = "//div[@class='serviceVehiclePartPopup']//input[3]")
	private WebElement moveToTheLeftButton;

	@FindBy(xpath = "//div[@class='serviceVehiclePartPopup']//input[1]")
	private WebElement moveAllToTheRightButton;

	@FindBy(xpath = "//div[@class='serviceVehiclePartPopup']//input[4]")
	private WebElement moveAllToTheLeftButton;

	@FindBy(id = "serviceVehiclePartOk")
	private WebElement okButton;

	@FindBy(id = "serviceVehiclePartCancel")
	private WebElement cancelButton;

	public ServiceRequestListServiceVehiclePartDialog(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
		try {
			wait.until(ExpectedConditions.attributeContains(servicesVehiclePartPopup, "display", "block"));
		} catch (Exception e) {
			Assert.fail("The services vehicle part dialog has not been opened", e);
		}
	}

	public void selectRandomAvailableVehiclePartOption() {
		selectRandomVehiclePartOption(availableVehiclePart);
	}

	public void selectRandomAssignedVehiclePartOption() {
		selectRandomVehiclePartOption(assignedVehiclePart);
	}

	private void selectRandomVehiclePartOption(WebElement availableVehiclePart) {
		wait.until(ExpectedConditions.visibilityOf(availableVehiclePart));
		Select selection = new Select(availableVehiclePart);
		int size = selection.getOptions().size();
		selection.selectByIndex(RandomUtils.nextInt(0, size));
	}

	public void clickMoveToTheRight() {
		clickMoveToTheRight(moveToTheRightButton);
	}

	public void clickMoveToTheLeft() {
		clickMoveToTheLeft(moveToTheLeftButton);
	}

	public void clickMoveAllToTheRight() {
		clickMoveToTheRight(moveAllToTheRightButton);
	}

	public void clickMoveAllToTheLeft() {
		clickMoveToTheLeft(moveAllToTheLeftButton);
	}

	private void clickServiceVehiclePartButtonToClose(WebElement button) {
		wait.until(ExpectedConditions.elementToBeClickable(button)).click();
		wait.until(ExpectedConditions.attributeContains(servicesVehiclePartPopup, "display", "none"));
	}

	public void clickServiceVehiclePartOkButton() {
		clickServiceVehiclePartButtonToClose(okButton);
	}

	public void clickServiceVehiclePartCancelButton() {
		clickServiceVehiclePartButtonToClose(cancelButton);
	}

	private void clickMoveToTheRight(WebElement button) {
		int availableVehiclePartOptionsNumber = getAvailableVehiclePartOptions();
		Assert.assertTrue(availableVehiclePartOptionsNumber > 0, "The available vehicle part options list is empty");
		wait.until(ExpectedConditions.elementToBeClickable(button)).click();
		wait.until(e -> getAvailableVehiclePartOptions() != availableVehiclePartOptionsNumber);
	}

	private void clickMoveToTheLeft(WebElement button) {
		int assignedVehiclePartOptionsNumber = getAssignedVehiclePartOptions();
		Assert.assertTrue(assignedVehiclePartOptionsNumber > 0, "The assigned vehicle part options list is empty");
		wait.until(ExpectedConditions.elementToBeClickable(button)).click();
		wait.until(e -> getAssignedVehiclePartOptions() != assignedVehiclePartOptionsNumber);
	}

	public int getAvailableVehiclePartOptions() {
		return getNumberOfOptions(availableVehiclePart, availableVehiclePartOptions);
	}

	public int getAssignedVehiclePartOptions() {
		return getNumberOfOptions(assignedVehiclePart, assignedVehiclePartOptions);
	}

	private int getNumberOfOptions(WebElement vehiclePart, List<WebElement> vehiclePartOptions) {
		try {
			wait.until(ExpectedConditions.visibilityOf(vehiclePart));
			return wait.until(ExpectedConditions.visibilityOfAllElements(vehiclePartOptions)).size();
		} catch (Exception ignored) {
			return 0;
		}
	}
}