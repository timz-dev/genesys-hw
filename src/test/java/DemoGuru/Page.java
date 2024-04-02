package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;

public class Page {
    WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollToBottom () {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToElement(WebElement element, int timeoutInSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int maxScrollAttempts = 10;
        int scrollAttempts = 0;
        long startTime = new Date().getTime();
        while (!element.isDisplayed() && scrollAttempts < maxScrollAttempts && new Date().getTime() - startTime < (long) timeoutInSeconds * 1000) {
            // Scroll a little
            js.executeScript("window.scrollBy(0, 100);");

            // Wait for a short time before next scroll attempt
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scrollAttempts++;
        }
    }
}
