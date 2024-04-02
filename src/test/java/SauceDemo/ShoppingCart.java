package SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class ShoppingCart extends Page {
    @FindBys({
        @FindBy(className="cart_item")
    })
    List<WebElement> cartItemList;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public ShoppingCart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public void assertCorrectItemsInCart (String[] items) {
        for (WebElement inventoryItem : this.cartItemList) {
            WebElement name = inventoryItem.findElement(By.className("inventory_item_name"));

            Assert.assertTrue(Arrays.stream(items).anyMatch(name.getText()::equals));
        }
    }

    public ShoppingInformation navigateToShoppingInformation () {
        this.checkoutButton.click();
        return new ShoppingInformation(driver);
    }

}
