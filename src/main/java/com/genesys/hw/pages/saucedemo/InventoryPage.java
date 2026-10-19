package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.Timeouts;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {
    // region Locators
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By ITEM_BUTTON = By.tagName("button");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItemsList;

    @FindBy(css = "[data-test=\"shopping-cart-link\"]")
    WebElement shoppingCartLink;

    @FindBy(className = "footer_copy")
    WebElement footer;
    // endregion

    public InventoryPage(WebDriver driver, Timeouts timeout) {
        super(driver, timeout);
    }

    public void addItemsToCart(String... items) {
        for (String item : items) {
            for (WebElement inventoryItem : this.inventoryItemsList) {
                WebElement name = inventoryItem.findElement(ITEM_NAME);
                if (name.getText().equals(item)) {
                    inventoryItem.findElement(ITEM_BUTTON).click();
                }
            }
        }
    }

    public int getCartBadgeCount() {
        WebElement shoppingCartBadge = this.shoppingCartLink.findElement(CART_BADGE);
        return Integer.parseInt(shoppingCartBadge.getText());
    }

    public String getFooterText() {
        return this.footer.getText();
    }

    public CartPage navigateToCart() {
        this.shoppingCartLink.click();
        return new CartPage(driver);
    }
}
