package tests;

import base.TestBase;
import dataproviders.InvalidCreditCardProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pages.DashboardPage;
import pages.PaymentDetailsPage;
import pages.UpgradeAccountPage;

@DisplayName("Upgrading account to Team")
public class UpgradingAccountToTeamTest extends TestBase {

    private static final Log LOG = LogFactory.getLog(UpgradingAccountToTeamTest.class);

    SoftAssertions softAssertions = new SoftAssertions();
    DashboardPage dashboardPage;
    PaymentDetailsPage paymentDetailsPage;
    UpgradeAccountPage accountPage;

    @BeforeAll
    public void login() {
        dashboardPage = loginPage.login();
    }

    @BeforeEach
    public void openPaymentPage() {
        LOG.info("Open account page");
        accountPage = dashboardPage.clickUpgradeButton();
        LOG.info("Open payment page");
        paymentDetailsPage = accountPage.clickUpgradeTeamPlanButton();
    }

    @AfterEach
    public void refresh() {
        driver.navigate().refresh();
        LOG.info("Refresh the page");
    }

    @Test
    @DisplayName("'Total' field shows correct value with appropriate amount of 'seats'")
    public void checkTotalFieldShowsCorrectValue() {

        final String YEARLY_PRICE = "192";
        final String MONTHLY_PRICE = "20";
        final String YEAR_OPERATION_DESCRIPTION = "$8 × 2 members × 12 months";
        final String MONTH_OPERATION_DESCRIPTION = "$10 × 2 members";

        checkTheAmount(YEARLY_PRICE, YEAR_OPERATION_DESCRIPTION, paymentDetailsPage);

        paymentDetailsPage.setMonthBillingPeriod();
        checkTheAmount(MONTHLY_PRICE, MONTH_OPERATION_DESCRIPTION, paymentDetailsPage);

        paymentDetailsPage.setYearBillingPeriod();
        checkTheAmount(YEARLY_PRICE, YEAR_OPERATION_DESCRIPTION, paymentDetailsPage);

        softAssertions.assertAll();

        LOG.info(getClass().getSimpleName() + " passed");
    }

    @ParameterizedTest
    @DisplayName("Correct error message shows when a user tries to put incorrect billing data")
    @ArgumentsSource(InvalidCreditCardProvider.class)
    public void checkErrorMessageShowsWhenCreditCardIsNotValid(String cardNumber, String data, String cvc, String errorMessage) {

        paymentDetailsPage.setRandomAmountOfSeats();
        paymentDetailsPage.enterCreditCardDetails(cardNumber, data, cvc);
        paymentDetailsPage.clickPurchaseButton();

        LOG.info("Error message: " + errorMessage);
        LOG.info("Actual message: " + paymentDetailsPage.getCreditCardErrorMessage());
        softAssertions.assertThat(errorMessage).isEqualTo(paymentDetailsPage.getCreditCardErrorMessage());
        softAssertions.assertAll();

        LOG.info(getClass().getSimpleName() + " passed");
    }

    private void checkTheAmount(String price, String operationDescription, PaymentDetailsPage paymentDetailsPage) {

        softAssertions.assertThat(price).isEqualTo(paymentDetailsPage.getTotalAmount());
        softAssertions.assertThat(paymentDetailsPage.getFeatureDescription().contains(price)).isTrue();
        softAssertions.assertThat(operationDescription).isEqualTo(paymentDetailsPage.getOperationDescription());
    }
}
