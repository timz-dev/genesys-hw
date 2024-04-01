import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {
    private static final Logger logger = LogManager.getLogger(FirstTest.class);
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        logger.info("WebDriver initialized.");
    }

    @Test
    public void exampleTest() {
        driver.get("https://www.google.com");
        WebElement acceptCookies = driver.findElement(By.cssSelector("#L2AGLb > div"));
        acceptCookies.click();
        WebElement input = driver.findElement(By.tagName("textarea"));
        input.sendKeys("hello there");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed.");
        }
    }
}
