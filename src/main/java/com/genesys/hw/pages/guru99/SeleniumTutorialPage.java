package com.genesys.hw.pages.guru99;

import com.genesys.hw.Timeouts;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumTutorialPage extends BasePage {
    // region Locators
    private static final By PRIVACY_IFRAME = By.xpath("//*[@title=\"SP Consent Message\"]");
    private static final By PRIVACY_ACCEPT_BUTTON = By.xpath("//*[@aria-label=\"Accept\"]");
    private static final By FREE_COURSE_SUBMIT_BUTTON =
            By.xpath("//*[normalize-space()=\"Join our FREE Course\"]/following::button[@type=\"submit\"][1]");
    // endregion

    public SeleniumTutorialPage(WebDriver driver) {
        super(driver);
    }

    public void handlePrivacyNotice() {
        if (!this.isElementVisibleWithin(PRIVACY_IFRAME, Timeouts.SHORT)) {
            return;
        }
        inFrame(driver.findElement(PRIVACY_IFRAME), () -> driver.findElement(PRIVACY_ACCEPT_BUTTON).click());
    }

    public boolean isFreeCourseSubmitButtonDisplayed() {
        return this.isElementVisibleWithin(FREE_COURSE_SUBMIT_BUTTON, Timeouts.DEFAULT);
    }

    public String getFreeCourseSubmitButtonText() {
        return driver.findElement(FREE_COURSE_SUBMIT_BUTTON).getText();
    }
}
