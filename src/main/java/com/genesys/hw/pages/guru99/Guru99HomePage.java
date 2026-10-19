package com.genesys.hw.pages.guru99;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Guru99HomePage extends BasePage {
    public static final String URL = "http://demo.guru99.com/test/guru99home";

    @FindBy(xpath = "//*[text()=\"Email Submission\"]/preceding-sibling::iframe")
    WebElement iframe;

    public Guru99HomePage(WebDriver driver) {
        super(driver);
    }

    public Guru99HomePage open() {
        driver.get(URL);
        return this;
    }

    public void handleCookies() {
        By cookiesIframeLocator = By.className("faktor-iframe-wrapper");
        if (!this.isElementVisibleWithin(cookiesIframeLocator, 5)) {
            return;
        }
        inFrame(driver.findElement(cookiesIframeLocator), () -> driver.findElement(By.id("save")).click());
    }

    public void clickOnImageInIframe() {
        inFrame(this.iframe, () -> driver.findElement(By.tagName("img")).click());
    }

    /** Waits for the tab opened by the iframe image, reads its title, then closes it and returns to the original tab. */
    public String getNewTabTitle() {
        String originalHandle = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String newHandle = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(originalHandle))
                .findFirst()
                .orElseThrow();

        driver.switchTo().window(newHandle);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
            return driver.getTitle();
        } finally {
            driver.close();
            driver.switchTo().window(originalHandle);
        }
    }

    public SeleniumTutorialPage navigateToSeleniumTutorial() {
        WebElement testingMenuItem = driver.findElement(
                By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]" +
                        "/*[@class=\"menu-block\"]/ul/li/a[contains(text(), \"Testing\")]"));
        WebElement subMenu = driver.findElement(
                By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]" +
                        "/*[@class=\"menu-block\"]/ul/li//*[contains(text(), \"Selenium\")]"));
        new Actions(driver)
                .moveToElement(testingMenuItem)
                .moveToElement(subMenu)
                .click()
                .build()
                .perform();

        dismissGoogleAd();
        return new SeleniumTutorialPage(driver);
    }

    private void dismissGoogleAd() {
        By googleAdsLocator = By.xpath("//iframe[contains(@id, \"google_ads_iframe\")]");
        if (!this.isElementVisibleWithin(googleAdsLocator, 10)) {
            return;
        }
        inFrame(driver.findElement(googleAdsLocator), () ->
                inFrame(driver.findElement(By.id("ad_iframe")), () ->
                        driver.findElement(By.id("dismiss-button")).click()));
    }
}
