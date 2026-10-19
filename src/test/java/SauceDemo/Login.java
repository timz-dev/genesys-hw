package SauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login extends Page {
    @FindBy(id = "user-name")
    WebElement usernameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(className = "error-message-container")
    WebElement errorMessageContainer;
    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillUsername (String username) {
        this.usernameInput.sendKeys(username);
    }
    public void fillPassword (String password) {
        this.passwordInput.sendKeys(password);
    }
    public void clickLogin () {
        this.loginButton.click();
    }

    public void fillLoginDetails(Credential credential) {
        this.fillUsername(credential.getUsername());
        this.fillPassword(credential.getPassword());
    }
    public Shopping navigateToShopping () {
        this.clickLogin();
        return new Shopping(driver);
    }
    public Shopping navigateToShopping (int timeoutSeconds) {
        this.clickLogin();
        return new Shopping(driver, timeoutSeconds);
    }

    public void assertErrorMessageIsCorrect(String messageText) {
        Assert.assertEquals(this.errorMessageContainer.getText(), messageText);
    }


}
