package DemoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class DemoSite extends Page {
    @FindBy(xpath = "//*[text()=\"Email Submission\"]/preceding-sibling::iframe")
    WebElement iframe;

    public DemoSite(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void handleCookies() {
        By cookiesIframeLocator = By.className("faktor-iframe-wrapper");
        WebElement cookiesIframe = driver.findElement(cookiesIframeLocator);
        this.waitForElementVisible(cookiesIframeLocator, 10);

        WebElement acceptCookiesButton = driver.switchTo().frame(cookiesIframe).findElement(By.id("save"));

        acceptCookiesButton.click();
        driver.switchTo().defaultContent();
    }
    public void clickOnImageInIframe() {
        driver.switchTo().frame(this.iframe).findElement(By.tagName("img")).click();
        driver.switchTo().defaultContent();
    }

    public SeleniumTutorial navigateToSeleniumTutorial() {
        WebElement testingMenuItem = driver.findElement(By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]/*[@class=\"menu-block\"]/ul/li/a[contains(text(), \"Testing\")]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(testingMenuItem).build().perform();
        WebElement subMenu = driver.findElement(By.xpath("//*[@id=\"rt-header\"]//*[contains(@class, \"rt-omega\")]/*[@class=\"menu-block\"]/ul/li//*[contains(text(), \"Selenium\")]"));
        actions.moveToElement(subMenu).build().perform();
        actions.click().build().perform();

        By googleAdsLocator = By.xpath("//iframe[contains(@id, \"google_ads_iframe\")]");
        this.waitForElementVisible(googleAdsLocator, 10);

        WebElement googleAdsIframe = driver.findElement(googleAdsLocator);
        WebElement advertisementIframe = driver.switchTo().frame(googleAdsIframe).findElement(By.id("ad_iframe"));

        WebElement closeAdButton = driver.switchTo().frame(advertisementIframe).findElement(By.id("dismiss-button"));
        closeAdButton.click();

        driver.switchTo().defaultContent();

        return new SeleniumTutorial(driver);
    }

    public void assertNewTabIsOpenedWithTitle(String title) {
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        Assert.assertEquals(driver.getTitle(), title);

        driver.close();
        driver.switchTo().window(browserTabs.get(0));
    }

}
