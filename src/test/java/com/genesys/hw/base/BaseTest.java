package com.genesys.hw.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Starts every test method in a brand-new Chrome with its own throwaway profile directory,
 * so no cookies, local/session storage, cache or history leak between tests.
 */
public abstract class BaseTest {
    protected final Logger logger = LogManager.getLogger(getClass());
    protected WebDriver driver;
    private Path userDataDir;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        userDataDir = Files.createTempDirectory("chrome-profile-");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + userDataDir);
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false));
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        logger.info("WebDriver initialized with fresh profile {}", userDataDir);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver closed.");
        }
        if (userDataDir != null) {
            try (Stream<Path> paths = Files.walk(userDataDir)) {
                paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
            userDataDir = null;
        }
    }
}
