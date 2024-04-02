package SauceDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ShoppingInformation extends Page {
    @FindBy(id = "first-name")
    WebElement firstNameInput;
    @FindBy(id = "last-name")
    WebElement lastNameInput;
    @FindBy(id = "postal-code")
    WebElement postalCodeInput;
    @FindBy(id = "continue")
    WebElement continueButton;
    public ShoppingInformation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
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

    public ShoppingOverview navigateToShoppingOverview(PersonalInformation personalInformation) {
        this.continueButton.click();
        return new ShoppingOverview(driver);
    }
}
