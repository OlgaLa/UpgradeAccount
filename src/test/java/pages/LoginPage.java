package pages;

import base.PageBase;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private static final By SIGNIN_BUTTON = By.cssSelector("[data-autotest-id='mr-link-signin-1']");
    private static final By SIGNIN_FORM_BUTTON = By.cssSelector("[data-autotest-id='mr-form-login-btn-signin-1']");
    private static final By EMAIL_FIELD = By.cssSelector("[data-autotest-id='mr-form-login-email-1']");
    private static final By PASSWORD_FIELD = By.cssSelector("[data-autotest-id='mr-form-login-password-1']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login() {
        actionBot.click(SIGNIN_BUTTON);
        actionBot.waitFor(SIGNIN_FORM_BUTTON);
        actionBot.sendKeys(EMAIL_FIELD, System.getenv("USER_NAME"));
        actionBot.sendKeys(PASSWORD_FIELD, System.getenv("PASSWORD"));
        actionBot.click(SIGNIN_FORM_BUTTON);

        return new DashboardPage(driver);
    }
}
