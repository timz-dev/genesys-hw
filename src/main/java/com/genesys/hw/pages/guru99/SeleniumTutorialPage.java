package com.genesys.hw.pages.guru99;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumTutorialPage extends BasePage {
    private static final By FREE_COURSE_SUBMIT_BUTTON =
            By.xpath("//*[normalize-space()=\"Join our FREE Course\"]/following::button[@type=\"submit\"][1]");

    public SeleniumTutorialPage(WebDriver driver) {
        super(driver);
    }

    public void handlePrivacyNotice() {
        By privacyIframeLocator = By.xpath("//*[@title=\"SP Consent Message\"]");
        if (!this.isElementVisibleWithin(privacyIframeLocator, 5)) {
            return;
        }
        inFrame(driver.findElement(privacyIframeLocator),
                () -> driver.findElement(By.xpath("//*[@aria-label=\"Accept\"]")).click());
    }

    public boolean isFreeCourseSubmitButtonDisplayed() {
        return this.isElementVisibleWithin(FREE_COURSE_SUBMIT_BUTTON, 10);
    }

    public String getFreeCourseSubmitButtonText() {
        return driver.findElement(FREE_COURSE_SUBMIT_BUTTON).getText();
    }
}
