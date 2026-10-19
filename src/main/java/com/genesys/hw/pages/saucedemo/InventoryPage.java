package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {
    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItemsList;

    @FindBy(css = "[data-test=\"shopping-cart-link\"]")
    WebElement shoppingCartLink;

    @FindBy(className = "footer_copy")
    WebElement footer;

    public InventoryPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public void addItemsToCart(String... items) {
        for (String item : items) {
            for (WebElement inventoryItem : this.inventoryItemsList) {
                WebElement name = inventoryItem.findElement(By.className("inventory_item_name"));
                if (name.getText().equals(item)) {
                    inventoryItem.findElement(By.tagName("button")).click();
                }
            }
        }
    }

    public int getCartBadgeCount() {
        WebElement shoppingCartBadge = this.shoppingCartLink.findElement(By.className("shopping_cart_badge"));
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
