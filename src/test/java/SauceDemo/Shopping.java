package SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.List;

public class Shopping extends Page{
    @FindBys({
            @FindBy(className = "inventory_item")
    })
    List<WebElement> inventoryItemsList;

    @FindBy(css = "[data-test=\"shopping-cart-link\"]")
    WebElement shoppingCartLink;

    @FindBy(className = "footer_copy")
    WebElement footer;

    public Shopping(WebDriver driver) {
        this(driver, 5);
    }

    public Shopping(WebDriver driver, int timeoutSeconds) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeoutSeconds), this);
    }

    public void putItemsToCart (String[] items) {
        for (String item : items) {
            for (WebElement inventoryItem : this.inventoryItemsList) {
                WebElement name = inventoryItem.findElement(By.className("inventory_item_name"));
                if (name.getText().equals(item)) {
                    WebElement addToCardButton = inventoryItem.findElement(By.tagName("button"));
                    addToCardButton.click();
                }
            }
        }
    }

    public ShoppingCart navigateToShoppingCart() {
        this.shoppingCartLink.click();
        return new ShoppingCart(driver);
    }
    public void assertBadgeNumberIsCorrect(int badgeNumber) {
        WebElement shoppingCartBadge = this.shoppingCartLink.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(Integer.parseInt(shoppingCartBadge.getText()), badgeNumber);
    }

    public void assertFooterContains(String[] textList) {
        for (String text : textList) {
            Assert.assertTrue(this.footer.getText().contains(text));
        }
    }
}
