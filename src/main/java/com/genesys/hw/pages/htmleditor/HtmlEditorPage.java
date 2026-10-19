package com.genesys.hw.pages.htmleditor;

import com.genesys.hw.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HtmlEditorPage extends BasePage {
    public static final String URL = "https://onlinehtmleditor.dev";

    // region Locators
    @FindBy(xpath = "//*[contains(@data-cke-tooltip-text, \"Bold\")]")
    WebElement boldTextButton;

    @FindBy(xpath = "//*[contains(@data-cke-tooltip-text, \"Underline\")]")
    WebElement underlineTextButton;

    @FindBy(css = ".ck-editor__main > [role='textbox']")
    WebElement editorArea;
    // endregion

    public HtmlEditorPage(WebDriver driver) {
        super(driver);
    }

    public HtmlEditorPage open() {
        driver.get(URL);
        return this;
    }

    public void switchBoldTextOn(boolean desiredState) {
        toggle(this.boldTextButton, desiredState);
    }

    public void switchUnderlineTextOn(boolean desiredState) {
        toggle(this.underlineTextButton, desiredState);
    }

    private static void toggle(WebElement button, boolean desiredState) {
        if (desiredState != Boolean.parseBoolean(button.getAttribute("aria-pressed"))) {
            button.click();
        }
    }

    public void fillEditorArea(CharSequence... input) {
        this.editorArea.sendKeys(input);
    }

    public String getEditorText() {
        return this.editorArea.getText();
    }

    public List<String> getTextsWrappedBy(String cssSelector) {
        return this.editorArea.findElements(By.cssSelector(cssSelector)).stream()
                .map(WebElement::getText)
                .toList();
    }
}
