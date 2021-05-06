package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allowed-ips");

        final String headlessProperty = System.getProperty("headless");
        boolean useHeadlessMode = headlessProperty.isEmpty() || Boolean.parseBoolean(headlessProperty);

        if (useHeadlessMode) {
            options.addArguments("--headless");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(System.getenv("BASE_URL"));
        loginPage = new LoginPage(driver);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
