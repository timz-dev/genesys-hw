package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.stream.Stream;

public class SeleniumTutorial extends Page {

    public SeleniumTutorial(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void handlePrivacyNotice() {
        By privacyIframeLocator = By.xpath("//*[@title=\"SP Consent Message\"]");
        this.waitForElementVisible(privacyIframeLocator, 10);
        WebElement privacyIframe = driver.findElement(privacyIframeLocator);
        Stream<Runnable> privacyFunctions = Stream.of(
                () -> {
                    driver.findElement(By.xpath("//*[@aria-label=\"Accept\"]")).click();
                }
        );

       Utilities.executeFunctionsInFrame(driver, privacyIframe, privacyFunctions);
    }
}
