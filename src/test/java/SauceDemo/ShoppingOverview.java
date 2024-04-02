package SauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ShoppingOverview extends Page {
    @FindBy(id = "finish")
    WebElement finishButton;

    public ShoppingOverview (WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public ShoppingComplete navigateToShoppingComplete () {
        this.finishButton.click();
        return new ShoppingComplete(driver);
    }

}
