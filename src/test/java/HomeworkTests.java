import DemoGuru.DemoSite;
import DemoGuru.SeleniumTutorial;
import HtmlEditor.HtmlEditor;
import SauceDemo.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class HomeworkTests {
    private static final Logger logger = LogManager.getLogger(HomeworkTests.class);
    private static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/inventory.html";
    private static final String HTML_EDITOR_URL = "https://onlinehtmleditor.dev";
    private static final String DEMO_GURU99_URL = "http://demo.guru99.com/test/guru99home";
    private static final String REST_API_TESTING_URL = "https://jsonplaceholder.typicode.com";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false));
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        logger.info("WebDriver initialized.");
    }

    @Test
    public void automate_purchase_process() {
        driver.get(SAUCE_DEMO_URL);

        Login login = new Login(driver);

        Credential loginCredential = Utilities.parseCredentials("performance_glitch_user");
        login.fillLoginDetails(loginCredential);
        Shopping shopping = login.navigateToShopping(15);
        String[] items = new String[]{"Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};
        shopping.putItemsToCart(items);
        shopping.assertBadgeNumberIsCorrect(items.length);

        ShoppingCart shoppingCart = shopping.navigateToShoppingCart();
        shoppingCart.assertCorrectItemsInCart(items);

        ShoppingInformation shoppingInformation = shoppingCart.navigateToShoppingInformation();

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setFirstName("Test");
        personalInformation.setLastName("User");
        personalInformation.setPostalCode(6734);

        shoppingInformation.fillYourInformation(personalInformation);

        ShoppingOverview shoppingOverview = shoppingInformation.navigateToShoppingOverview(personalInformation);

        ShoppingComplete shoppingComplete = shoppingOverview.navigateToShoppingComplete();
        shoppingComplete.assertShoppingCompleteHeaderIsCorrect("Thank you for your order!");
    }

    @Test
    public void verify_error_messages_for_mandatory_fields() {
        driver.get(SAUCE_DEMO_URL);
        Login login = new Login(driver);

        login.clickLogin();
        login.assertErrorMessageIsCorrect("Epic sadface: Username is required");

        Credential loginCredential = Utilities.parseCredentials("standard_user");
        login.fillLoginDetails(loginCredential);
        Shopping shopping = login.navigateToShopping();
        shopping.scrollToBottom();

        String footerYear = System.getProperty("footer.year", "2024");
        String[] footerItems = new String[]{footerYear, "Terms of Service"};
        shopping.assertFooterContains(footerItems);
    }

    @Test
    public void rich_text_editor () throws InterruptedException {
        driver.get(HTML_EDITOR_URL);

        String testInput = "Automation Test Example";
        String[] inputSplit = testInput.split(" ");

        HtmlEditor htmlEditor = new HtmlEditor(driver);
        htmlEditor.switchBoldTextOn(true);
        htmlEditor.fillEditorArea(inputSplit[0]);
        htmlEditor.switchBoldTextOn(false);
        htmlEditor.fillEditorArea(Keys.SPACE);
        htmlEditor.switchUnderlineTextOn(true);
        htmlEditor.fillEditorArea(inputSplit[1]);
        htmlEditor.switchUnderlineTextOn(false);
        htmlEditor.fillEditorArea(Keys.SPACE);
        htmlEditor.fillEditorArea(inputSplit[2]);
        htmlEditor.assertEditorFilledWithText(testInput);
        htmlEditor.assertFormattedTexts("strong, b", inputSplit[0]);
        htmlEditor.assertFormattedTexts("u", inputSplit[1]);
    }

    @Test
    public void iframe_and_tab_handling () {
        driver.get(DEMO_GURU99_URL);
        DemoSite demoSite = new DemoSite(driver);
        demoSite.handleCookies();
        demoSite.clickOnImageInIframe();

        // requested title "Selenium Live Project: FREE Real Time Project for Practice" changed on the live site
        String newTabTitle = "Selenium Live Project for Practice";
        demoSite.assertNewTabIsOpenedWithTitle(newTabTitle);

        SeleniumTutorial seleniumTutorial = demoSite.navigateToSeleniumTutorial();
        seleniumTutorial.handlePrivacyNotice();
        // requested red "Join Now" button does not exist anymore, changed to the red "Submit" button after "Join our FREE Course"
        seleniumTutorial.assertSubmitButtonIsDisplayedAfterFreeCourseText();
    }

    @Test
    public void rest_api_testing() {
        RestAssured.baseURI = REST_API_TESTING_URL;

        Response response = RestAssured.get("/users");
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        JsonPath jsonPath = response.jsonPath();
        logger.info(jsonPath.getList("$"));
        List<Object> users = jsonPath.getList("$");

        int index = 0;
        for (Object user : users) {
            List<String> names = jsonPath.getList("name");
            List<String> emails = jsonPath.getList("email");
            System.out.println(names.get(index) + " | " + emails.get(index));
            index++;
        }

        String firstEmail = jsonPath.getString("[0].email");
        Assert.assertTrue(firstEmail.contains("@"), "First email address doesn't contain @");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed.");
        }
    }
}
