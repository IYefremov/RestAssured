package com.cyberiansoft.test.bo.pageobjects.webpages;

import com.cyberiansoft.test.bo.webelements.ComboBox;
import com.cyberiansoft.test.bo.webelements.DropDown;
import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import com.cyberiansoft.test.bo.webelements.WebTable;
import com.cyberiansoft.test.bo.webelements.impl.DropDownImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static com.cyberiansoft.test.bo.utils.WebElementsBot.*;

public class RepairLocationPhaseServicesTabWebPage extends BaseWebPage {
	
	@FindBy(id = "ctl00_Content_comboPhase_Input")
	private ComboBox phasecmb;
	
	@FindBy(id = "ctl00_Content_comboPhase_DropDown")
	private DropDown phasedd;

	@FindBy(id = "ctl00_Content_comboLocation_Input")
    private ComboBox locationcmb;

	@FindBy(id = "ctl00_Content_comboLocation_DropDown")
    private DropDown locationdd;
	
	@FindBy(id = "ctl00_Content_ddlOrderType_Input")
	private ComboBox wotypecmb;
	
	@FindBy(id = "ctl00_Content_ddlOrderType_DropDown")
	private DropDown wotypedd;
	
	@FindBy(id = "ctl00_Content_btnAssignSelected")
	private WebElement assignselectedservicesbtn;
	
	@FindBy(id = "ctl00_Content_gv_ctl00")
	private WebTable phaseservicestable;
	
	public RepairLocationPhaseServicesTabWebPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);	
	}
	
	public List<WebElement> getPhaseServicesTableRows() {
		return phaseservicestable.getTableRows();
	}
	
	public WebElement getTableRowWithPhaseService(String phaseservice) {
		List<WebElement> rows = getPhaseServicesTableRows();
		for (WebElement row : rows) {
			if (getTableRowPhaseServiceName(row).equals(phaseservice)) {
				return row;
			}
		} 
		return null;
	}
	
	public String getTableRowPhaseServiceName(WebElement row) {
		return row.findElement(By.xpath(".//td[4]")).getText();
	}
	
	public void selectAllServicesInTable() {
		List<WebElement> rows = getPhaseServicesTableRows();
		if (getBrowserType().equals("chrome")) {
			for (WebElement row : rows) {
				click(row.findElement(By.xpath(".//td[1]/input")));
			}
		} else if (getBrowserType().contains("edge")) {
			for (WebElement row : rows) {
                setAttribute(row.findElement(By.xpath(".//td[1]/input")), "checked", "checked");
			}
		} else {
			for (WebElement row : rows) {
				click(row.findElement(By.xpath(".//td[1]/label")));
			}
		}
	}
	
	public int getNumberOfSelectedServicesInTable() {
		List<WebElement> rows = getPhaseServicesTableRows();
		int checked = 0;
		if (getBrowserType().contains("edge")) {
            return (int) rows.stream().map(e -> e.getAttribute("checked")).count(); //todo check!!!
        } else {
            for (WebElement row : rows) {
                if (isCheckboxChecked(row.findElement(By.xpath("./td/input")))) {
                    checked++;
                }
            }
        }
		return checked;
	}
	
	public void selectServicePhaseValue(String phaseservice, String phase) {
		WebElement row = getTableRowWithPhaseService(phaseservice);
		if (row != null) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row);
			click(row.findElement(By.xpath(".//input[contains(@id, 'comboPhase_Input')]")));
		waitABit(300);
			new DropDownImpl(driver.findElement(By.xpath(".//*[contains(@id, 'comboPhase_DropDown')]"))).selectByVisibleText(phase);
		} else {
            Assert.fail("Can't find " + phaseservice + " phase service");
		}
		waitForLoading();
	}
	
	public List<WebElement> getNumberOfPhaseServicesWithSelectedPhaseValue(String phase) {
		return phaseservicestable.getWrappedElement().findElements(By.xpath(".//input[@value='" + phase + "']"));
	}
	
	public String getSelectedServicePhase(String phaseservice) {
		String phase = "";
		WebElement row = getTableRowWithPhaseService(phaseservice);
		if (row != null) {
			phase = getTableRowPhaseValue(row);
		} else {
            Assert.fail("Can't find " + phaseservice + " phase service");
		}
		return phase;
	}
	
	public String getTableRowPhaseValue(WebElement row) {
		String phase = "";
		phase = row.findElement(By.xpath(".//input[contains(@id, 'comboPhase_Input')]")).getAttribute("value");
		return phase;
	}

	public void selectLocation(String location) {
	    selectComboboxValue(locationcmb, locationdd, location);
    }

	public void selectPhase(String phase) {
		selectComboboxValue(phasecmb, phasedd, phase);
	}
	
	public void selectWOType(String wotype) {
		selectComboboxValue(wotypecmb, wotypedd, wotype);
		waitForLoading();
	}
	
	public void selectPhaseServiceInTable(String phaseservice) {
		WebElement row = getTableRowWithPhaseService(phaseservice);
		if (row != null) {
			click(row.findElement(By.xpath(".//input[contains(@id, 'chkService')]")));
		} else {
            Assert.fail("Can't find " + phaseservice + " phase service");
		}
	}
	
	public void clickAssignToSelectedservicesButton() {
		try{
			driver.switchTo().alert().accept();
		}catch(Exception e){}
		clickAndWait(assignselectedservicesbtn);
	}

}
