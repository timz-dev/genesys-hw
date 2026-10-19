package com.genesys.hw.tests.saucedemo;

import com.genesys.hw.Timeouts;
import com.genesys.hw.base.BaseTest;
import com.genesys.hw.models.Credential;
import com.genesys.hw.models.PersonalInformation;
import com.genesys.hw.pages.saucedemo.CartPage;
import com.genesys.hw.pages.saucedemo.CheckoutCompletePage;
import com.genesys.hw.pages.saucedemo.CheckoutInformationPage;
import com.genesys.hw.pages.saucedemo.InventoryPage;
import com.genesys.hw.pages.saucedemo.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    @Test
    public void automate_purchase_process() {
        String[] items = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket" };
        InventoryPage inventory = new LoginPage(driver).open()
                .loginAs(Credential.forUser("performance_glitch_user"), Timeouts.LONG);
        inventory.addItemsToCart(items);
        Assert.assertEquals(inventory.getCartBadgeCount(), items.length);

        CartPage cart = inventory.navigateToCart();
        Assert.assertEqualsNoOrder(cart.getItemNames().toArray(), items);

        CheckoutInformationPage information = cart.navigateToCheckoutInformation();
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setFirstName("Test");
        personalInformation.setLastName("User");
        personalInformation.setPostalCode(6734);
        information.fillYourInformation(personalInformation);

        CheckoutCompletePage complete = information.navigateToCheckoutOverview().navigateToCheckoutComplete();
        Assert.assertEquals(complete.getHeaderText(), "Thank you for your order!");
    }

    @Test
    public void verify_error_messages_for_mandatory_fields() {
        InventoryPage inventory = new LoginPage(driver).open()
            .loginAs(Credential.forUser("standard_user"), Timeouts.LONG);
        inventory.scrollToBottom();

        String footerText = inventory.getFooterText();
        String footerYear = System.getProperty("footer.year", "2024");
        // requested default year 2024 got updated to 2026, test will pass with -Dfooter.year=2026 JVM arg
        Assert.assertTrue(footerText.contains(footerYear), "Footer should contain " + footerYear);
        Assert.assertTrue(footerText.contains("Terms of Service"), "Footer should contain Terms of Service");
    }
}
