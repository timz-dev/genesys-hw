import SauceDemo.Credential;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

public class Utilities {
    public static Credential parseCredentials() {
        try (InputStream json = Utilities.class.getResourceAsStream("/credential.json")) {
            if (json == null) {
                throw new IllegalStateException("credential.json not found on classpath");
            }
            return new ObjectMapper().readValue(json, Credential.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void executeFunctionsInFrame(WebDriver driver, WebElement iframeElement, Stream<Runnable> innerFunctions) {
        driver.switchTo().frame(iframeElement);
        innerFunctions.forEach(Runnable::run);
        driver.switchTo().defaultContent();
    }
}
