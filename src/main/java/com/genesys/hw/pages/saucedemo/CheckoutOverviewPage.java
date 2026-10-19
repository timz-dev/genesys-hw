package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver, 5);
    }

    public CheckoutCompletePage navigateToCheckoutComplete() {
        this.finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
