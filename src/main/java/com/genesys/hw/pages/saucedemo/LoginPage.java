package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.models.Credential;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/";

    @FindBy(id = "user-name")
    WebElement usernameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(className = "error-message-container")
    WebElement errorMessageContainer;

    public LoginPage(WebDriver driver) {
        super(driver, 5);
    }

    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    public void fillUsername(String username) {
        this.usernameInput.sendKeys(username);
    }

    public void fillPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        this.loginButton.click();
    }

    public void fillLoginDetails(Credential credential) {
        this.fillUsername(credential.getUsername());
        this.fillPassword(credential.getPassword());
    }

    public InventoryPage loginAs(Credential credential) {
        return this.loginAs(credential, 5);
    }

    public InventoryPage loginAs(Credential credential, int timeoutSeconds) {
        this.fillLoginDetails(credential);
        this.clickLogin();
        return new InventoryPage(driver, timeoutSeconds);
    }

    public String getErrorText() {
        return this.errorMessageContainer.getText();
    }
}
