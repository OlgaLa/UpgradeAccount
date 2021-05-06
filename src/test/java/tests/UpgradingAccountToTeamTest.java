package tests;

import base.TestBase;
import dataproviders.CreditCard;
import dataproviders.InvalidCreditCardProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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

        final String EXPECTED_YEARLY_PRICE = "192";
        final String EXPECTED_MONTHLY_PRICE = "20";
        final String EXPECTED_YEAR_OPERATION_DESCRIPTION = "$8 × 2 members × 12 months";
        final String EXPECTED_MONTH_OPERATION_DESCRIPTION = "$10 × 2 members";

        assertPriceAndDescription(paymentDetailsPage, EXPECTED_YEARLY_PRICE, EXPECTED_YEAR_OPERATION_DESCRIPTION);

        paymentDetailsPage.setMonthBillingPeriod();
        assertPriceAndDescription(paymentDetailsPage, EXPECTED_MONTHLY_PRICE, EXPECTED_MONTH_OPERATION_DESCRIPTION);

        paymentDetailsPage.setYearBillingPeriod();
        assertPriceAndDescription(paymentDetailsPage, EXPECTED_YEARLY_PRICE, EXPECTED_YEAR_OPERATION_DESCRIPTION);

        softAssertions.assertAll();

        LOG.info(getClass().getSimpleName() + " passed");
    }


    @ParameterizedTest
    @DisplayName("Correct error message shows when a user tries to put incorrect billing data")
    @ArgumentsSource(InvalidCreditCardProvider.class)
    public void checkErrorMessageShowsWhenCreditCardIsNotValid(CreditCard creditCard, String errorMessage) {

        paymentDetailsPage.setRandomAmountOfSeats();
        paymentDetailsPage.enterCreditCardDetails(creditCard);
        paymentDetailsPage.clickPurchaseButton();

        LOG.info("Error message: " + errorMessage);
        LOG.info("Actual message: " + paymentDetailsPage.getCreditCardErrorMessage());

        softAssertions.assertThat(paymentDetailsPage.getCreditCardErrorMessage()).isEqualTo(errorMessage);
        softAssertions.assertAll();

        LOG.info(getClass().getSimpleName() + " passed");
    }

    private void assertPriceAndDescription(PaymentDetailsPage paymentDetailsPage, String expectedPrice, String expectedOperationDescription) {

        softAssertions.assertThat(paymentDetailsPage.getTotalAmount()).isEqualTo(expectedPrice);
        softAssertions.assertThat(paymentDetailsPage.getFeatureDescription().contains(expectedPrice)).isTrue();
        softAssertions.assertThat(paymentDetailsPage.getOperationDescription()).isEqualTo(expectedOperationDescription);
    }
}
