package com.genesys.hw.tests.guru99;

import com.genesys.hw.base.BaseTest;
import com.genesys.hw.pages.guru99.Guru99HomePage;
import com.genesys.hw.pages.guru99.SeleniumTutorialPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Guru99Test extends BaseTest {

    @Test
    public void iframe_and_tab_handling() {
        Guru99HomePage home = new Guru99HomePage(driver).open();
        home.handleCookies();
        home.clickOnImageInIframe();

        // requested title "Selenium Live Project: FREE Real Time Project for Practice" changed on the live site
        Assert.assertEquals(home.getNewTabTitle(), "Selenium Live Project for Practice");

        SeleniumTutorialPage tutorial = home.navigateToSeleniumTutorial();
        tutorial.handlePrivacyNotice();
        // requested red "Join Now" button does not exist anymore, changed to the red "Submit" button after "Join our FREE Course"
        Assert.assertTrue(tutorial.isFreeCourseSubmitButtonDisplayed(), "Submit button should be displayed");
        Assert.assertEquals(tutorial.getFreeCourseSubmitButtonText(), "Submit");
    }
}
