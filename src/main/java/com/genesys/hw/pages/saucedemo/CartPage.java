package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.Timeouts;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    // region Locators
    private static final By ITEM_NAME = By.className("inventory_item_name");

    @FindBy(className = "cart_item")
    List<WebElement> cartItemList;

    @FindBy(id = "checkout")
    WebElement checkoutButton;
    // endregion

    public CartPage(WebDriver driver) {
        super(driver, Timeouts.SHORT);
    }

    public List<String> getItemNames() {
        return this.cartItemList.stream()
                .map(item -> item.findElement(ITEM_NAME).getText())
                .toList();
    }

    public CheckoutInformationPage navigateToCheckoutInformation() {
        this.checkoutButton.click();
        return new CheckoutInformationPage(driver);
    }
}
