package com.genesys.hw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this(driver, 10);
    }

    protected BasePage(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeoutSeconds), this);
    }

    protected void waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isElementVisibleWithin(By locator, int timeoutInSeconds) {
        try {
            this.waitForElementVisible(locator, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void inFrame(WebElement iframeElement, Runnable action) {
        driver.switchTo().frame(iframeElement);
        try {
            action.run();
        } finally {
            driver.switchTo().parentFrame();
        }
    }

    public void scrollToBottom() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
