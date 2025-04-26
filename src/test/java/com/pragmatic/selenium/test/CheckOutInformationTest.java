package com.pragmatic.selenium.test;

import com.pragmatic.selenium.TestData.TestData;
import com.pragmatic.selenium.base.BaseTest;
import com.pragmatic.selenium.pages.CartPage;
import com.pragmatic.selenium.pages.CheckOutInformationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutInformationTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(CheckOutInformationTest.class);


    //Verify page heading
    @Test
    public void getPageTitle() {
        toCheckOutInfoFlow();


        CheckOutInformationPage checkOutInfo = new CheckOutInformationPage(webDriver);
        String PgeHeading = checkOutInfo.getPageTitle();
        Assert.assertEquals(PgeHeading, "Checkout: Your Information", "title doesn't match");
        logger.debug("Page heading is:{}", PgeHeading);
    }


    //Verify cancel button
    @Test
    public void verifyCancelButton() {
        toCheckOutInfoFlow();



        CheckOutInformationPage checkOutInfo = new CheckOutInformationPage(webDriver);
        Assert.assertEquals(checkOutInfo.getCancelButtonText(), "Cancel", "Button name is not correct");
        Assert.assertTrue(checkOutInfo.isCancelButtonEnabled(), "cancel button is not enabled");
        checkOutInfo.clickCancelButton();
        //verify back to cart page
        CartPage cPage = new CartPage(webDriver);
        String cartPageTitle = cPage.getPageHeading();
        Assert.assertEquals(cartPageTitle, "Your Cart", "Incorrect Page redirection");
        logger.debug("Cart page title after click on cancel button :" + cartPageTitle);

    }

    //verify continue button
    @Test(dataProvider = "test_error_messages", dataProviderClass = TestData.class)
    public void verifyValidationMessages(String firstName, String lastName, String postalCode, String expectedError) {
        toCheckOutInfoFlow();

        CheckOutInformationPage checkOutInfo = new CheckOutInformationPage(webDriver);
        checkOutInfo.typeFirstName(firstName).typeLastName(lastName).typePostalCode(postalCode).clickContinueButton();

        String actualError = checkOutInfo.actualMessage();
        Assert.assertEquals(actualError, expectedError, "Error message is not correct");
        logger.debug("Expected Error Message:: {} --  Actual Error Message:: {}", expectedError, actualError);
    }

}
