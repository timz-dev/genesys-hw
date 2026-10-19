package com.genesys.hw.pages.guru99;

import com.genesys.hw.Timeouts;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Guru99HomePage extends BasePage {
    public static final String URL = "http://demo.guru99.com/test/guru99home";

    // region Locators
    private static final By COOKIES_IFRAME = By.className("faktor-iframe-wrapper");
    private static final By COOKIES_SAVE_BUTTON = By.id("save");
    private static final By IFRAME_IMAGE = By.tagName("img");
    private static final By TESTING_MENU_ITEM = By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]" +
            "/*[@class=\"menu-block\"]/ul/li/a[contains(text(), \"Testing\")]");
    private static final By SELENIUM_SUB_MENU = By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]" +
            "/*[@class=\"menu-block\"]/ul/li//*[contains(text(), \"Selenium\")]");
    private static final By GOOGLE_ADS_IFRAME = By.xpath("//iframe[contains(@id, \"google_ads_iframe\")]");
    private static final By AD_IFRAME = By.id("ad_iframe");
    private static final By AD_DISMISS_BUTTON = By.id("dismiss-button");

    @FindBy(xpath = "//*[text()=\"Email Submission\"]/preceding-sibling::iframe")
    WebElement iframe;
    // endregion

    public Guru99HomePage(WebDriver driver) {
        super(driver);
    }

    public Guru99HomePage open() {
        driver.get(URL);
        return this;
    }

    public void handleCookies() {
        if (!this.isElementVisibleWithin(COOKIES_IFRAME, Timeouts.SHORT)) {
            return;
        }
        inFrame(driver.findElement(COOKIES_IFRAME), () -> driver.findElement(COOKIES_SAVE_BUTTON).click());
    }

    public void clickOnImageInIframe() {
        inFrame(this.iframe, () -> driver.findElement(IFRAME_IMAGE).click());
    }

    /** Waits for the tab opened by the iframe image, reads its title, then closes it and returns to the original tab. */
    public String getNewTabTitle() {
        String originalHandle = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Timeouts.DEFAULT.duration());
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
        new Actions(driver)
                .moveToElement(driver.findElement(TESTING_MENU_ITEM))
                .moveToElement(driver.findElement(SELENIUM_SUB_MENU))
                .click()
                .build()
                .perform();

        dismissGoogleAd();
        return new SeleniumTutorialPage(driver);
    }

    private void dismissGoogleAd() {
        if (!this.isElementVisibleWithin(GOOGLE_ADS_IFRAME, Timeouts.DEFAULT)) {
            return;
        }
        inFrame(driver.findElement(GOOGLE_ADS_IFRAME), () ->
                inFrame(driver.findElement(AD_IFRAME), () ->
                        driver.findElement(AD_DISMISS_BUTTON).click()));
    }
}
