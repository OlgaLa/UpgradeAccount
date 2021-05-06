package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpgradeAccountPage extends PageBase {

    private static final By UPGRADE_TEAM_PLAN_BUTTON = By.cssSelector("[data-autotest-id='team-plan__select-btn']");
    private static final By PRICING_MODEL = By.cssSelector("[data-autotest-id='pricing-modal']");


    public UpgradeAccountPage(WebDriver driver) {
        super(driver);
        actionBot.waitFor(PRICING_MODEL);
    }

    public PaymentDetailsPage clickUpgradeTeamPlanButton() {
        actionBot.waitFor(UPGRADE_TEAM_PLAN_BUTTON);
        actionBot.click(UPGRADE_TEAM_PLAN_BUTTON);
        return new PaymentDetailsPage(driver);
    }
}
