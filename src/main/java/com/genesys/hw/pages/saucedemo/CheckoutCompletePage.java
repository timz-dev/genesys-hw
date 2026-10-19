package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {
    @FindBy(className = "complete-header")
    WebElement completeHeader;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver, 5);
    }

    public String getHeaderText() {
        return this.completeHeader.getText();
    }
}
