package com.genesys.hw.pages.saucedemo;

import com.genesys.hw.models.PersonalInformation;
import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformationPage extends BasePage {
    @FindBy(id = "first-name")
    WebElement firstNameInput;
    @FindBy(id = "last-name")
    WebElement lastNameInput;
    @FindBy(id = "postal-code")
    WebElement postalCodeInput;
    @FindBy(id = "continue")
    WebElement continueButton;

    public CheckoutInformationPage(WebDriver driver) {
        super(driver, 5);
    }

    public void fillFirstName(String firstName) {
        this.firstNameInput.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        this.lastNameInput.sendKeys(lastName);
    }

    public void fillPostalCode(int postalCode) {
        this.postalCodeInput.sendKeys(Integer.toString(postalCode));
    }

    public void fillYourInformation(PersonalInformation personalInformation) {
        this.fillFirstName(personalInformation.getFirstName());
        this.fillLastName(personalInformation.getLastName());
        this.fillPostalCode(personalInformation.getPostalCode());
    }

    public CheckoutOverviewPage navigateToCheckoutOverview() {
        this.continueButton.click();
        return new CheckoutOverviewPage(driver);
    }
}
