package DemoGuru;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class Utilities {
    public static void executeFunctionsInFrame(WebDriver driver, WebElement iframeElement, Stream<Runnable> innerFunctions) {
        driver.switchTo().frame(iframeElement);
        innerFunctions.forEach(Runnable::run);
        driver.switchTo().defaultContent();
    }

    public static void removeIframe(WebDriver driver, WebElement iframeElement) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].parentNode.removeChild(arguments[0])", iframeElement);
    }
}
