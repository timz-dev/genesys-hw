package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.Timeouts;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    // region Locators
    @FindBy(id = "finish")
    WebElement finishButton;
    // endregion

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver, Timeouts.SHORT);
    }

    public CheckoutCompletePage navigateToCheckoutComplete() {
        this.finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
