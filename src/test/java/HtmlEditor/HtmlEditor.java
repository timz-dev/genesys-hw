package HtmlEditor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HtmlEditor extends Page {

    @FindBy(xpath = "//*[contains(@data-cke-tooltip-text, \"Bold\")]")
    WebElement boldTextButton;

    @FindBy(xpath = "//*[contains(@data-cke-tooltip-text, \"Underline\")]")
    WebElement underlineTextButton;

    @FindBy(xpath = "//*[contains(@aria-label, \"editing area\")]")
    WebElement editorArea;

    public HtmlEditor(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void switchBoldTextOn(boolean desiredState) {
        boolean currentState = Boolean.parseBoolean(this.boldTextButton.getAttribute("aria-pressed"));
        if (desiredState != currentState) {
            this.boldTextButton.click();
        }
    }
    public void switchUnderlineTextOn(boolean desiredState) {
        boolean currentState = Boolean.parseBoolean(this.underlineTextButton.getAttribute("aria-pressed"));
        if (desiredState != currentState) {
            this.underlineTextButton.click();
        }
    }
    public void fillEditorArea(CharSequence... input) {
        this.editorArea.sendKeys(input);
    }
    public void assertEditorFilledWithText(String input) {

        String[] inputSplit = input.split(" ");
        boolean containsAllTextPart = true;

        for (String textPart : inputSplit) {
            if (!this.editorArea.getText().contains(textPart)) {
                containsAllTextPart = false;
            }
        }
        Assert.assertTrue(containsAllTextPart, "Editor Area Input is correct");
    }
}
