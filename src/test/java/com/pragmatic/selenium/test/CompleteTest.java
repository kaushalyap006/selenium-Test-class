package com.pragmatic.selenium.test;

import com.pragmatic.selenium.base.BaseTest;
import com.pragmatic.selenium.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompleteTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CompleteTest.class);


    // check page heading
    @Test
    public void VerifyPageTitle() {

        toCompleteFlow();

        completePage finalPage = new completePage(webDriver);
        String PageHeading = finalPage.getCompletePageHeading();
        logger.debug("Complete page heading is: {}", PageHeading);
        Assert.assertEquals(PageHeading, "Checkout: Complete!", "page heading is not correct ");

    }


    //Verify thank you message
    @Test
    public void VerifyMessage() {
        toCompleteFlow();
        completePage finalPage = new completePage(webDriver);
        String message = finalPage.getThankYouMessage();
        logger.debug("message is: {}", message);
        Assert.assertEquals(message, "Thank you for your order!", "Incorrect message ");
    }


    // Verify complete Text
    @Test
    public void VerifyCompleteText() {
        toCompleteFlow();
        completePage finalPage = new completePage(webDriver);
        String completeText = finalPage.getCompleteText();
        logger.debug("Complete text is: {}", completeText);
        Assert.assertEquals(completeText, "Your order has been dispatched, and will arrive just as fast as the pony can get there!", "incorrect Text ");
    }


    // Verify BackHome Button
    @Test
    public void checkBackHomeButton() {
        toCompleteFlow();
        completePage finalPage = new completePage(webDriver);
        String HomeButtonText = finalPage.getHomeButtonText();
        logger.debug("Home Button Text is: {}", HomeButtonText);
        Assert.assertEquals(HomeButtonText, "Back Home", "Incorrect Button Text");
        Assert.assertTrue(finalPage.checkHomeButtonIsEnable(), "Home button is not enable");
    }


    //Verify that home button is redirect to productPage
    @Test
    public void checkHomeButtonRedirection() {
        toCompleteFlow();
        completePage finalPage = new completePage(webDriver);
        finalPage.clickHomeButton();
        //Verify that home page redirect to product Page
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        Assert.assertEquals(landingPage.getTitle(), "Products", "Home page redirection is not correct");
        logger.debug("BackHome Button redirects to :{}", landingPage.getTitle());
    }


}