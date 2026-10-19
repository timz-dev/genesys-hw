package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(className = "cart_item")
    List<WebElement> cartItemList;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver, 5);
    }

    public List<String> getItemNames() {
        return this.cartItemList.stream()
                .map(item -> item.findElement(By.className("inventory_item_name")).getText())
                .toList();
    }

    public CheckoutInformationPage navigateToCheckoutInformation() {
        this.checkoutButton.click();
        return new CheckoutInformationPage(driver);
    }
}
