package com.pragmatic.selenium.test;


import com.pragmatic.selenium.pages.CartPage;
import com.pragmatic.selenium.pages.SauceLandingPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {
    WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(CartTest.class);

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        // SauceLoginPage=new SauceLoginPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);

        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();
        landingPage.clickAddToCartButton();
        landingPage.clickCart();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

// Verify page title
    @Test
    public void getPageTitle() {

        CartPage cPage = new CartPage(webDriver);
        String cartPageHeading = cPage.getPageHeading();
        Assert.assertEquals(cartPageHeading, "Your Cart", "title doesn't match");

        logger.info("cart page Testing");
        logger.debug("Actual Page title is:{}", cartPageHeading);
    }

    //Verify remove button Text

    // Verify that item can be removed from the list when click on remove button

    //Verify that remove button is enable before click it
    // Verify that cart icon count get change when remove the item

    // Verify to get the text of 'continue shopping' button
    // Verify that 'continue  shopping button is enable' before click
    // Verify that user is able to go to product page when click on the 'continue shopping button'

    //Verify that checkout button is enable before click on it
    //Verify that user is able to navigate checkout page by clicking on the checkout button

   // Verify added product are visible in checkout page

}
