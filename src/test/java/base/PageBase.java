package base;

import org.openqa.selenium.WebDriver;

public abstract class PageBase extends TestBase{

    protected WebDriver driver;
    protected ActionBot actionBot;

    public PageBase(WebDriver driver) {
        actionBot = new ActionBot(driver);
        this.driver = driver;
    }

}
