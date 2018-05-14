package com.cyberiansoft.test.inhouse.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class ClientQuotesPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-sm blue btn-add-potential-client']")
    private WebElement addClientBTN;

    @FindBy(xpath = "//input[@id='ClientName']")
    private List<WebElement> newClientName;

    @FindBy(id = "ClientNickname")
    private WebElement newClientNickName;

    @FindBy(id = "ClientAddress")
    private WebElement newClientAddress;

    @FindBy(id = "ClientAddress2")
    private WebElement newClientAddress2;

    @FindBy(id = "ClientZip")
    private WebElement newClientZip;

    @FindBy(id = "ClientCountry")
    private WebElement newClientCountry;

    @FindBy(id = "ClientState")
    private WebElement newClientState;

    @FindBy(id = "ClientCity")
    private WebElement newClientCity;

    @FindBy(id = "ClientPhone")
    private WebElement newClientBusinessPhone;

    @FindBy(id = "ContactPhone")
    private WebElement newClientCellPhone;

    @FindBy(id = "ContactFirstName")
    private WebElement newClientFirstName;

    @FindBy(id = "ContactLastName")
    private WebElement newClientLastName;

    @FindBy(id = "ContactTitle")
    private WebElement newClientTitle;

    @FindBy(id = "ContactEmail")
    private WebElement newClientEmail;

    @FindBy(xpath = "//button[@class='btn btn-outline btn-submit']")
    private WebElement confirmNewClient;

    @FindBy(id = "searchString")
    private WebElement searchField;

    @FindBy(id = "btnSearch")
    private WebElement searchBTN;

    @FindBy(id = "table-potential-client_processing")
    private WebElement processingBar;

    @FindBy(xpath = "//button[@class='btn btn-outline btn-submit']")
    private List<WebElement> updateClientBTN;

    @FindBy(id = "ProposalName")
    private List<WebElement> agreementName;

    @FindBy(id = "EditionID")
    private WebElement editionMenu;

    @FindBy(xpath = "//button[@class='btn btn-outline btn-submit']")
    private List<WebElement> addClientProposalBTN;

    @FindBy(xpath = "//div[@class='callout callout-info']/button")
    private WebElement closeNotificationBTN;

    public ClientQuotesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickAddClientBTN() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn btn-sm blue btn-add-potential-client']")));
        addClientBTN.click();
    }

    public void setNewClientName(String name) {
        newClientName.get(0).sendKeys(name);
    }

    public void clearAndSetNewClientName(String name) {
        wait.until(ExpectedConditions.visibilityOf(newClientName.get(1))).clear();
        newClientName.get(1).sendKeys(name);
    }

    public void setNewClientNickName(String name) {
        newClientNickName.sendKeys(name);
    }

    public void setNewClientAddress(String address) {
        newClientAddress.sendKeys(address);
    }

    public void setNewClientAddress2(String address2) {
        newClientAddress2.sendKeys(address2);
    }

    public void setNewClientZip(String zip) {
        newClientZip.sendKeys(zip);
    }

    public void setNewClientCountry(String country) {
        newClientCountry.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()='" + country + "']"))).click();
    }

    public void setNewClientState(String state) {
        newClientState.sendKeys(state);
    }

    public void setNewClientCity(String city) {
        newClientCity.sendKeys(city);
    }

    public void setNewClientBusinessPhone(String businessPhone) {
        newClientBusinessPhone.sendKeys(businessPhone);
    }

    public void setNewClientCellPhone(String cellPhone) {
        newClientCellPhone.sendKeys(cellPhone);
    }

    public void setNewClientFirstName(String firstName) {
        newClientFirstName.sendKeys(firstName);
    }

    public void setNewClientLastName(String lastName) {
        newClientLastName.sendKeys(lastName);
    }

    public void setNewClientTitle(String title) {
        newClientTitle.sendKeys(title);
    }

    public void setNewClientEmail(String email) {
        newClientEmail.sendKeys(email);
    }

    public void fillNewClientProfile(String name, String nickname, String address, String address2, String zip,
                                     String country, String state, String city, String businessPhone, String cellPhone, String firstName,
                                     String lastName, String title, String email) {
        setNewClientName(name);
        setNewClientNickName(nickname);
        setNewClientAddress(address);
        setNewClientAddress2(address2);
        setNewClientZip(zip);
        setNewClientCountry(country);
        // setNewClientState(state);
        setNewClientCity(city);
        setNewClientBusinessPhone(businessPhone);
        setNewClientCellPhone(cellPhone);
        setNewClientFirstName(firstName);
        setNewClientLastName(lastName);
        setNewClientTitle(title);
        setNewClientEmail(email);

    }


    public void clickConfirmNewClientBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmNewClient)).click();
    }


    public boolean verifyUserWasCreated(String verifyParameter) {
        searchUser(verifyParameter);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + verifyParameter + "']")));
            return true;
        } catch (TimeoutException e) {
            searchUser(verifyParameter);
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + verifyParameter + "']")));
                return true;
            } catch (TimeoutException ignored) {
                return false;
            }
        }
    }

    public void searchUser(String searchValue) {
        try {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchString")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("searchString")));
        searchField.clear();
        searchField.sendKeys(searchValue);
        wait.until(ExpectedConditions.elementToBeClickable(searchBTN)).click();
        wait.until(ExpectedConditions.invisibilityOf(processingBar));
        } catch (Exception ignored) {}

//        try {
//            wait.until(ExpectedConditions.visibilityOf(searchField));
////            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchString")));
////            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("searchString")));
//        } catch (TimeoutException e) {
//            waitABit(3000);
//        }
//        searchField.clear();
//        searchField.sendKeys(searchValue);
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(searchBTN)).click();
//        } catch (Exception e) {
//            clickElementWithJS(searchBTN);
//        }
//        try {
//            wait.until(ExpectedConditions.invisibilityOf(processingBar));
//        } catch (Exception e) {
//            waitABit(1000);
//        }
    }

    public void deleteUser(String deleteParameter) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + deleteParameter + "']"))).
                    findElement(By.xpath("..")).findElement(By.xpath("//a[@class='btn-delete btn-delete-potential-client']"));
            while(element.isDisplayed()) {
                element.click();
                driver.switchTo().alert().accept();
                wait.until(ExpectedConditions.visibilityOf(closeNotificationBTN)).click();
            }
        } catch (Exception ignored) {}
    }


    public void editClient(String editParemeter) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + editParemeter + "']"))).
                findElement(By.xpath("..")).findElement(By.xpath("//a[@class='btn-update btn-update-potential-client']")).click();
    }

    public void clickUpdateClientBTN() {
        wait.until(ExpectedConditions.elementToBeClickable(updateClientBTN.get(1))).click();
    }

    public void clickAddAgreementBTN(String agreementIdentifier) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + agreementIdentifier + "']"))).
                findElement(By.xpath("..")).findElement(By.xpath("//a[@class='btn-add btn-add-client-proposal']")).click();
    }

    public void setAgreementName(String name) {
        agreementName.get(0).sendKeys(name);
    }

    public void selectEdition(String edition) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        editionMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()='" + edition + "']"))).click();
        js.executeScript("arguments[0].click();", updateClientBTN.get(2));
    }

    public void setAgreement(String agreement, String team) {
        setAgreementName(agreement);
        selectEdition(team);
        saveAgreement();
    }

    public void saveAgreement() {
        wait.until(ExpectedConditions.elementToBeClickable(updateClientBTN.get(2))).click();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", updateClientBTN.get(2));
//        Thread.sleep(500);
    }

    public void expandAgreementList(String identifier) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + identifier + "']"))).
                    findElement(By.xpath("..")).findElement(By.xpath("//td[@class=' details-control']")).click();
        } catch (Exception e) {
            searchUser(identifier); //todo delete?
        }
    }

    public void clickEditAgreement(String s) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + s + "']"))).
                    findElement(By.xpath("..")).findElement(By.xpath("//a[@class='btn-row btn-update-client-proposal']")).click();
            //td[text()='First agreement'] todo check this locator!!!
        } catch (Exception e) {
            Assert.fail("The \"Edit Agreement\" button has not been clicked!");
        }
    }

    public boolean abilityToChangeAgreementEdition(String newName) {
        try {
            selectEdition(newName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean abilityToChangeAgreementName(String s) {
        try {
            Thread.sleep(1000);
            agreementName.get(1).clear();
            agreementName.get(1).sendKeys(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkAgreementByName(String s) {
        try {
            waitABit(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + s + "']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }

    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void updateAgreement() {
        wait.until(ExpectedConditions.elementToBeClickable(updateClientBTN.get(3))).click();
    }

    public ClientQuotesDetailPage clickSetupAgreementBTN(String agreementIdentifier) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("table-potential-client_processing")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='" + agreementIdentifier + "']"))).
                findElement(By.xpath("..")).findElement(By.xpath("//a[@class='btn-row btn-setup-client-proposal']")).click();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='agreement-statuses']")));
            return PageFactory.initElements(driver, ClientQuotesDetailPage.class);
        } catch (TimeoutException e) {
            return null;
        }
    }
}
