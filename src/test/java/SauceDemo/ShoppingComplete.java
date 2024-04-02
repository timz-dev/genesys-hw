package SauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class ShoppingComplete extends Page {
    @FindBy(className = "complete-header")
    WebElement completeHeader;

    public ShoppingComplete(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public void assertShoppingCompleteHeaderIsCorrect (String headerText) {
        Assert.assertTrue(completeHeader.getText().contains(headerText));
    }
}
