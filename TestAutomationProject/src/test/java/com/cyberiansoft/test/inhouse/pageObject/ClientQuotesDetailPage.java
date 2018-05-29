package com.cyberiansoft.test.inhouse.pageObject;

import com.cyberiansoft.test.inhouse.config.InHouseConfigInfo;
import com.cyberiansoft.test.inhouse.utils.MailChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientQuotesDetailPage extends BasePage {

    private String userName;
    private String userPassword;

    @FindBy(className = "agreement-statuses")
    private WebElement agreementStatusesBlock;

    @FindBy(className = "btn-select-edition-discount")
    private WebElement discountBTN;

    @FindBy(xpath = "//button[@class='btn btn-sm blue btn-finalize-agreement']")
    private WebElement finalizeAgreementButton;

    @FindBy(xpath = "//button[@class='btn btn-sm blue btn-send-notification']")
    private WebElement sendNotificationButton;

    @FindBy(className = "form-control")
    private WebElement formControlList;

    @FindBy(xpath = "//button[@class='submit btn-save-select-edition-discount']")
    private WebElement submitDiscountButton;

    @FindBy(xpath = "//button[@class='submit btn-save-select-edition-feature-setup-fee']")
    private WebElement submitSetupFeeButton;

    @FindBy(xpath = "//tbody[@data-tbody-feature-group-id]")
    private List<WebElement> clientSupportTables;

    @FindBy(xpath = "//a[@class='btn-add-ischecked-addon-edition-feature addon-checked-value']")
    private WebElement yesAddItemToAgreement;

    @FindBy(className = "price")
    private WebElement repair360Free;

    @FindBy(xpath = "//table[@class='text-center table-price']//td[@data-price-per-month]")
    private WebElement pricePerMonth;

    @FindBy(xpath = "//table[@class='text-center table-price']//td[@data-setup-fee]")
    private WebElement totalSetUpFee;

    @FindBy(xpath = "//div[@id='finalize-validation-error-dialog']/div[@class='modal modal-primary']")
    private WebElement modalDialog;

    @FindBy(xpath = "//span[@class='notification-status']")
    private WebElement notificationStatus;

    @FindBy(xpath = "//span[text()='Select setup fee...']")
    private List<WebElement> emptySetupFeeSelectionList;

    @FindBy(xpath = "//div[@class='dropup open']")
    private WebElement dropUpOpen;

    @FindBy(xpath = "//div[@class='dropup open']//select[@class='form-control']")
    private WebElement dropUpOptions;

    @FindBy(xpath = "//span[contains (text(), 'Agreement status:')]/following-sibling::b")
    private WebElement agreementStatus;

    @FindBy(xpath = "//span[contains (text(), 'Paid status:')]/following-sibling::span")
    private WebElement paidStatus;

    @FindBy(xpath = "//span[contains (text(), 'Viewed letter:')]/following-sibling::span")
    private WebElement viewedLetterStatus;

    @FindBy(xpath = "//span[contains (text(), 'Viewed agreement:')]/following-sibling::span")
    private WebElement viewedAgreementStatus;

    public ClientQuotesDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        userName = InHouseConfigInfo.getInstance().getUserName();
        userPassword = InHouseConfigInfo.getInstance().getUserPassword();
    }

    public boolean checkAgreementStatuses(String agreement, String payment, String letterView, String agreementView) {
        wait.until(ExpectedConditions.visibilityOf(agreementStatusesBlock));
        return (agreementStatus.getText().equals(agreement) &&
                paidStatus.getText().equals(payment) &&
                viewedLetterStatus.getText().equals(letterView) &&
                viewedAgreementStatus.getText().equals(agreementView));
    }

    public ClientQuotesDetailPage clickDiscountButton() {
        discountBTN.click();
        wait.until(ExpectedConditions.visibilityOf(dropUpOpen));
        return PageFactory.initElements(driver, ClientQuotesDetailPage.class);
    }

    public ClientQuotesDetailPage clickFinalizeAgreementButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finalizeAgreementButton)).click();
        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {}
        return PageFactory.initElements(driver, ClientQuotesDetailPage.class);
    }

    public void handleAlertForFinalizeAgreementButton() {
        try {
            wait.until(ExpectedConditions.attributeToBe(modalDialog, "display", "block"));
            wait.until(ExpectedConditions.elementToBeClickable(modalDialog.findElement(By.className("close")))).click();
            wait.until(ExpectedConditions.attributeToBe(modalDialog, "display", "none"));
        } catch (Exception ignored) {}
    }

    public void sendNotification() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(sendNotificationButton)).click();
        } catch (Exception e) {
            clickWithJS(sendNotificationButton);
        }
        try {
            driver.switchTo().alert().accept();
            waitForLoading();
        } catch (Exception e) {
            handleAlertForFinalizeAgreementButton();
            Assert.fail("The modal dialog has been displayed after clicking the \"Send Notification\" button." + e);
        }
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(notificationStatus, "Sending..."));
            waitABit(10000);
            wait.until(ExpectedConditions.textToBePresentInElement(notificationStatus, "Notification successfully sent."));
        } catch (TimeoutException e) {
            waitABit(20000);
            try {
                wait.until(ExpectedConditions.textToBePresentInElement(notificationStatus, "Notification successfully sent."));
            } catch (TimeoutException ex) {
                Assert.fail("The \"Notification successfully sent\" has not been displayed!");
            }
        }
    }

    //todo move to another class
    public boolean checkEmails(String title) {
        boolean flag = false;
        waitABit(30000);
        for (int i = 0; i < 5; i++) {
            try {
                if (!MailChecker.searchSpamEmailAndGetMailMessage(userName, userPassword, title,
                        "noreply@repair360.net").isEmpty()) {
                    flag = true;
                    break;
                }
            } catch (NullPointerException ignored) {}
            waitABit(40000);
        }
        return flag;
    }

    public ArrayList<String> getLinks() throws IOException {
        String mailContent = MailChecker.getUserMailContentFromSpam();
        Pattern linkPattern = Pattern.compile("(<a[^>]+>.+?<\\/a>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher pageMatcher = linkPattern.matcher(mailContent);
        ArrayList<String> links = new ArrayList<>();
        while (pageMatcher.find()) {
            links.add(pageMatcher.group());
        }
        return links;
    }

    public String getAgreementApproveLink() {
//        String mailContent = null;
//        try {
//            mailContent = MailChecker.getUserMailContentFromSpam();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Document doc = Jsoup.parse(mailContent);
//        String text = doc.body().text();
//        System.out.println(text);
//        String[] allTexts = text.split(" ");
//        String result = "";
//        for (String str : allTexts) {
//            if (str.contains("https://goo.gl")) {
//                result = str;
//            }
//        }
//        return result;

        String mailmessage = "";
        for (int i = 0; i < 4; i++) {
            if (!com.cyberiansoft.test.ios_client.utils.MailChecker.searchSpamEmail(userName, userPassword, "Agreement", "noreply@repair360.net", "https://goo.gl")) {
                waitABit(60 * 500);
            } else {
                mailmessage = com.cyberiansoft.test.ios_client.utils.MailChecker.searchEmailAndGetMailMessage(userName, userPassword, "Agreement", "noreply@repair360.net");
                break;
            }
        }
        return mailmessage;
    }

    public String getMailContentFromSpam() throws IOException {
        return MailChecker.getUserMailContentFromSpam();
    }

    public void selectDiscount(String discount) {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(formControlList));
        Select selection = new Select(formControlList);
        selection.selectByVisibleText(discount);
        wait.until(s -> !selection.getAllSelectedOptions().isEmpty());
        wait.until(ExpectedConditions.elementToBeClickable(submitDiscountButton)).click();
        waitForLoading();
    }

    public boolean checkNewPrice(String price) {
        waitABit(2000);
        System.out.println("PRICE:");
        System.out.println(pricePerMonth.getText());
        System.out.println(price);
        return pricePerMonth.getText().equals(price);
    }

    public boolean checkSetupFee(String fee) {
        waitABit(2000);
        System.out.println("SetUpFee:");
        System.out.println(totalSetUpFee.getText());
        System.out.println(fee);
        return totalSetUpFee.getText().contains(fee);
    }

    public boolean checkPricePerMonth(String price) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(pricePerMonth, price));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddClientSupportItem(String clientSupportItem) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='" +
                            clientSupportItem + "']/following::td[1]//i[@class='icon cb-icon-check-empty']")))).click();
        } catch (Exception e) {
            e.printStackTrace();
            clickWithJS(driver.findElement(By.xpath("//span[text()='" +
                            clientSupportItem + "']/following::td[1]//i[@class='icon cb-icon-check-empty']")));
        }
        clickYesToAddItemToAgreement();
    }

    private void selectSetupFee(String clientSupportItem, String option) {
        waitABit(3000);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver
                    .findElement(By.xpath("//span[text()='" + clientSupportItem + "']/following::td[2]//span")))).click();
            wait.until(ExpectedConditions.elementToBeClickable(formControlList));
            new Select(formControlList).selectByVisibleText(option);
        } catch (Exception e) {
            try {
                new Select(formControlList).selectByVisibleText(option);
            } catch (Exception e1) {
                Assert.fail("The Setup fee for \"" + clientSupportItem + "\" has not been selected!" + e);
            }
        }
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitSetupFeeButton)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitForLoading();
    }

//    public void selectSetupFeeForAllClients() {
//        selectSetupFee("testFeature2_1 test mike", "setupfee1_1");
//        selectSetupFee("testFeature3 test mike", "setupfee3_1");
//        selectSetupFee("tf22222", "1_1");
//        selectSetupFee("Test private", "test name");
//        selectSetupFee("Create Invoices (single or multiple vehicles) (edit)", "1");
//        selectSetupFee("Copy to Agreement settings page only features with \"Publice view\" State (when creating Client Agreement).", "test_r_1");
//    }

    public void selectSetupFeeForAllClients() {
        try {
            int size = wait.until(ExpectedConditions.visibilityOfAllElements(emptySetupFeeSelectionList)).size();
            for (int i = 0; i < size; i++) {
                driver.switchTo().defaultContent();
                wait.until(ExpectedConditions.elementToBeClickable(emptySetupFeeSelectionList.get(i))).click();
                wait.until(ExpectedConditions.visibilityOf(dropUpOpen));
                Select selection = new Select(dropUpOptions);
                selection.selectByIndex(1);
                wait.until(s -> !selection.getAllSelectedOptions().isEmpty());
                wait.until(ExpectedConditions.elementToBeClickable(submitSetupFeeButton)).click();
                waitForLoading();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clickYesToAddItemToAgreement() {
        yesAddItemToAgreement.click();
        waitForLoading();
    }
}
