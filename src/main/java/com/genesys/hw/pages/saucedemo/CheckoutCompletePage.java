package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.Timeouts;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {
    // region Locators
    @FindBy(className = "complete-header")
    WebElement completeHeader;
    // endregion

    public CheckoutCompletePage(WebDriver driver) {
        super(driver, Timeouts.SHORT);
    }

    public String getHeaderText() {
        return this.completeHeader.getText();
    }
}
