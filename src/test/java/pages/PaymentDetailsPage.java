package pages;

import base.PageBase;
import dataproviders.CreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentDetailsPage extends PageBase {

    private static final By PAYMENT_MODAL = By.cssSelector("payment-modal-container");
    private static final By TOTAL_AMOUNT = By.cssSelector("[data-autotest-id='checkout-sum']");
    private static final By BILLING_PERIOD_MONTHLY = By.cssSelector("[data-autotest-id='interval-button-month']");
    private static final By BILLING_PERIOD_YEARLY = By.cssSelector("[data-autotest-id='interval-button-year']");
    private static final By FEATURE_DESCRIPTION = By.cssSelector(".information-column__feature-description");
    private static final By OPERATION_DESCRIPTION = By.cssSelector("div[class*='checkoutSum__operationDescription']");
    private static final By TEAM_SIZE_DROPDOWN = By.cssSelector("[data-autotest-id='team-size-select']");
    private static final By TEAM_MEMBERS_OPTIONS = By.cssSelector("div.rtb-select__options>div>button.rtb-select__option");
    private static final By PURCHASE_BUTTON = By.cssSelector("[data-autotest-id='billing-actions__purchase-button']");
    private static final By CREDIT_CARD_NUMBER = By.cssSelector("[data-elements-stable-field-name='cardNumber']");
    private static final By CREDIT_CARD_EXPIRY_DATE = By.cssSelector("[data-elements-stable-field-name='cardExpiry']");
    private static final By CREDIT_CARD_CVC = By.cssSelector("[data-elements-stable-field-name='cardCvc']");
    private static final By CREDIT_CARD_ERROR_MESSAGE = By.cssSelector("[data-autotest-id='stripe-token-card-input__card-error']");
    private static final By CREDIT_CARD_FRAME = By.cssSelector("iframe[title='Secure card payment input frame']");

    public PaymentDetailsPage(WebDriver driver) {
        super(driver);
        actionBot.waitFor(PAYMENT_MODAL);
    }

    public void setMonthBillingPeriod() {
        actionBot.click(BILLING_PERIOD_MONTHLY);
    }

    public void setYearBillingPeriod() {
        actionBot.click(BILLING_PERIOD_YEARLY);
    }

    public String getTotalAmount() {
        actionBot.waitFor(TOTAL_AMOUNT);
        return actionBot.getText(TOTAL_AMOUNT);
    }

    public String getFeatureDescription() {
        actionBot.waitFor(FEATURE_DESCRIPTION);
        return actionBot.getText(FEATURE_DESCRIPTION);
    }

    public String getOperationDescription() {
        actionBot.waitFor(OPERATION_DESCRIPTION);
        return actionBot.getText(OPERATION_DESCRIPTION);
    }

    public void setRandomAmountOfSeats() {
        actionBot.waitFor(TEAM_SIZE_DROPDOWN);
        actionBot.click(TEAM_SIZE_DROPDOWN);
        actionBot.dropdownRandomClick(TEAM_MEMBERS_OPTIONS);
    }

    public void enterCreditCardDetails(CreditCard creditCard) {
        actionBot.switchToFrame(CREDIT_CARD_FRAME);
        actionBot.sendKeys(CREDIT_CARD_NUMBER, creditCard.getCardNumber());
        actionBot.sendKeys(CREDIT_CARD_EXPIRY_DATE, creditCard.getExpiryDate());
        actionBot.sendKeys(CREDIT_CARD_CVC, creditCard.getCvv());
        actionBot.switchToDefaultContent();
    }

    public void clickPurchaseButton() {
        actionBot.waitFor(PURCHASE_BUTTON);
        actionBot.click(PURCHASE_BUTTON);
    }

    public String getCreditCardErrorMessage() {
        actionBot.waitFor(CREDIT_CARD_ERROR_MESSAGE);
        return actionBot.getText(CREDIT_CARD_ERROR_MESSAGE);
    }
}
