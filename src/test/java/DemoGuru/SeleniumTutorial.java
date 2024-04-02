package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class SeleniumTutorial extends Page {

    public SeleniumTutorial(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void handlePrivacyNotice() {
        By privacyIframeLocator = By.xpath("//*[@title=\"SP Consent Message\"]");
        this.waitForElementVisible(privacyIframeLocator, 10);

        WebElement privacyIframe = driver.findElement(privacyIframeLocator);
        WebElement privacyAcceptButton = driver.switchTo().frame(privacyIframe).findElement( By.xpath("//*[@aria-label=\"Accept\"]"));
        privacyAcceptButton.click();
        driver.switchTo().defaultContent();
    }
}
