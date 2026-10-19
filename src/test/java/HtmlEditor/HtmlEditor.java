package HtmlEditor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class HtmlEditor extends Page {

    @FindBy(xpath = "//*[contains(@data-cke-tooltip-text, \"Bold\")]")
    WebElement boldTextButton;

    @FindBy(xpath = "//*[contains(@data-cke-tooltip-text, \"Underline\")]")
    WebElement underlineTextButton;

    @FindBy(css = ".ck-editor__main > [role='textbox']")
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
        String editorText = this.editorArea.getText();
        for (String textPart : input.split(" ")) {
            Assert.assertTrue(editorText.contains(textPart), "Editor text should contain: " + textPart);
        }
    }

    public void assertFormattedTexts(String cssSelector, String... texts) {
        List<String> actual = this.editorArea.findElements(By.cssSelector(cssSelector)).stream()
                .map(WebElement::getText)
                .toList();
        Assert.assertEquals(actual, Arrays.asList(texts), "Texts wrapped by " + cssSelector);
    }
}
