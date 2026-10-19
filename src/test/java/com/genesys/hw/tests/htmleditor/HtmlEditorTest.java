package com.genesys.hw.tests.htmleditor;

import com.genesys.hw.base.BaseTest;
import com.genesys.hw.pages.htmleditor.HtmlEditorPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HtmlEditorTest extends BaseTest {

    @Test
    public void rich_text_editor() {
        String testInput = "Automation Test Example";
        String[] inputSplit = testInput.split(" ");

        HtmlEditorPage htmlEditor = new HtmlEditorPage(driver).open();
        htmlEditor.switchBoldTextOn(true);
        htmlEditor.fillEditorArea(inputSplit[0]);
        htmlEditor.switchBoldTextOn(false);
        htmlEditor.fillEditorArea(Keys.SPACE);
        htmlEditor.switchUnderlineTextOn(true);
        htmlEditor.fillEditorArea(inputSplit[1]);
        htmlEditor.switchUnderlineTextOn(false);
        htmlEditor.fillEditorArea(Keys.SPACE);
        htmlEditor.fillEditorArea(inputSplit[2]);

        String editorText = htmlEditor.getEditorText();
        for (String textPart : inputSplit) {
            Assert.assertTrue(editorText.contains(textPart), "Editor text should contain: " + textPart);
        }
        Assert.assertEquals(htmlEditor.getTextsWrappedBy("strong, b"), List.of(inputSplit[0]), "Bold texts");
        Assert.assertEquals(htmlEditor.getTextsWrappedBy("u"), List.of(inputSplit[1]), "Underlined texts");
    }
}
