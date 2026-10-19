package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.stream.Stream;

public class SeleniumTutorial extends Page {

    public SeleniumTutorial(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void handlePrivacyNotice() {
        By privacyIframeLocator = By.xpath("//*[@title=\"SP Consent Message\"]");
        if (!this.isElementVisibleWithin(privacyIframeLocator, 5)) {
            return;
        }
        WebElement privacyIframe = driver.findElement(privacyIframeLocator);
        Stream<Runnable> privacyFunctions = Stream.of(
                () -> {
                    driver.findElement(By.xpath("//*[@aria-label=\"Accept\"]")).click();
                }
        );

       Utilities.executeFunctionsInFrame(driver, privacyIframe, privacyFunctions);
    }

    public void assertSubmitButtonIsDisplayedAfterFreeCourseText() {
        By submitButtonLocator = By.xpath("//*[normalize-space()=\"Join our FREE Course\"]/following::button[@type=\"submit\"][1]");
        Assert.assertTrue(this.isElementVisibleWithin(submitButtonLocator, 10), "Submit button should be displayed");

        WebElement submitButton = driver.findElement(submitButtonLocator);
        Assert.assertEquals(submitButton.getText(), "Submit");
    }
}
