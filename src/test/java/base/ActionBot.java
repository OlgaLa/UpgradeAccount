package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class ActionBot {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionBot(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void sendKeys(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String dropdownRandomClick(By locator) {
        List<WebElement> options = driver.findElements(locator);
        Random random = new Random();
        int index = random.nextInt(options.size());
        String text = options.get(index).getText();
        wait.until(ExpectedConditions.visibilityOf(options.get(index)));
        options.get(index).click();
        return text;
    }

    public void switchToFrame(By locator) {
        WebElement element = driver.findElement(locator);
        driver.switchTo().frame(element);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void waitFor(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
