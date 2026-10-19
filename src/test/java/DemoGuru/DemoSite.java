package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.stream.Stream;

public class DemoSite extends Page {
    @FindBy(xpath = "//*[text()=\"Email Submission\"]/preceding-sibling::iframe")
    WebElement iframe;

    public DemoSite(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void handleCookies() {
        By cookiesIframeLocator = By.className("faktor-iframe-wrapper");
        if (!this.isElementVisibleWithin(cookiesIframeLocator, 5)) {
            return;
        }
        WebElement cookiesIframe = driver.findElement(cookiesIframeLocator);

        Stream<Runnable> cookiesFunctions = Stream.of(
                () -> {
                    driver.findElement(By.id("save")).click();
                }
        );
        Utilities.executeFunctionsInFrame(driver, cookiesIframe, cookiesFunctions);
    }
    public void clickOnImageInIframe() {
        Stream<Runnable> functions = Stream.of(
                () -> {
                    driver.findElement(By.tagName("img")).click();
                }
        );
        Utilities.executeFunctionsInFrame(driver, this.iframe, functions);
    }

    public SeleniumTutorial navigateToSeleniumTutorial() {
        WebElement testingMenuItem = driver.findElement(
                By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]" +
                        "/*[@class=\"menu-block\"]/ul/li/a[contains(text(), \"Testing\")]"));

        Actions actions = new Actions(driver);
        actions
                .moveToElement(testingMenuItem);
        WebElement subMenu = driver.findElement(
                By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]" +
                        "/*[@class=\"menu-block\"]/ul/li//*[contains(text(), \"Selenium\")]"));
        actions
                .moveToElement(subMenu);
        actions
                .click()
                .build()
                .perform();

        try {
            By googleAdsLocator = By.xpath("//iframe[contains(@id, \"google_ads_iframe\")]");
            this.waitForElementVisible(googleAdsLocator, 10);

            WebElement googleAdsIframe = driver.findElement(googleAdsLocator);
            Stream<Runnable> googleAdsFunctions = Stream.of(
                    () -> {
                        WebElement advertisementIframe = driver.findElement(By.id("ad_iframe"));

                        Stream<Runnable> advertisementFunctions = Stream.of(
                                () -> {
                                    driver.findElement(By.id("dismiss-button")).click();
                                }
                        );
                        Utilities.executeFunctionsInFrame(driver, advertisementIframe, advertisementFunctions);
                    }
            );
            Utilities.executeFunctionsInFrame(driver, googleAdsIframe, googleAdsFunctions);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return new SeleniumTutorial(driver);
    }

    public void assertNewTabIsOpenedWithTitle(String title) {
        String originalHandle = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String newHandle = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(originalHandle))
                .findFirst()
                .orElseThrow();

        driver.switchTo().window(newHandle);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
            Assert.assertEquals(driver.getTitle(), title);
        } finally {
            driver.close();
            driver.switchTo().window(originalHandle);
        }
    }

}
