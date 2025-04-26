package com.pragmatic.selenium.test;

import com.pragmatic.selenium.TestData.TestData;
import com.pragmatic.selenium.base.BaseTest;
import com.pragmatic.selenium.pages.SauceLandingPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SauceLoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SauceLoginTest.class);

    @Test
    public void testLoginWithValidCredentials() {
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();
        //get the access to product page first
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        String LandingPageHeading = landingPage.getTitle();
        assertEquals(LandingPageHeading, "Products", "Title doesn't match");
        logger.info("Login page testing");
        logger.debug("Landing page Title is:{}", LandingPageHeading);
    }


    @Test(dataProvider = "user-credentials", dataProviderClass = TestData.class)
    public void testLoginWithBlankUsernameAndBlankPassword(String username, String password, String expectedError) {
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        loginPage.typeUsername(username).typePassword(password).clickLogin();
        // loginPage.clickLogin();
        String acErrorMessage = loginPage.getError();
        assertEquals(acErrorMessage, expectedError, "Error message doesn't match");
        logger.debug("Expected Error Message:: {} --  Actual Error Message:: {}", expectedError, acErrorMessage);

    }


}
