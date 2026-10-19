package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.Timeouts;
import com.genesys.hw.models.Credential;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/";

    // region Locators
    @FindBy(id = "user-name")
    WebElement usernameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(className = "error-message-container")
    WebElement errorMessageContainer;
    // endregion

    public LoginPage(WebDriver driver) {
        super(driver, Timeouts.SHORT);
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
        return this.loginAs(credential, Timeouts.SHORT);
    }

    public InventoryPage loginAs(Credential credential, Timeouts timeout) {
        this.fillLoginDetails(credential);
        this.clickLogin();
        return new InventoryPage(driver, timeout);
    }

    public String getErrorText() {
        return this.errorMessageContainer.getText();
    }
}
