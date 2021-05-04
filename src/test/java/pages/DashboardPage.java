package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends PageBase {

    private static final By UPGRADE_BUTTON = By.cssSelector("[data-autotest-id='dashboard-upgrade-button']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public UpgradeAccountPage clickUpgradeButton() {
        actionBot.click(UPGRADE_BUTTON);
        return new UpgradeAccountPage(driver);
    }

}
