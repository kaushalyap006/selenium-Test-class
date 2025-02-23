package com.pragmatic.selenium.test;


import com.pragmatic.selenium.pages.CartPage;
import com.pragmatic.selenium.pages.SauceLandingPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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


    //Verify remove buttons
    @Test
    public void getRemoveButtonText() {

        // Find all buttons with the class 'btn btn_secondary btn_small cart'

        List<WebElement> buttonList = webDriver.findElements(By.xpath("//button[contains(@class,'btn btn_secondary btn_small cart')]"));


        // Count how many of buttons have the text "Remove"

        long removeButtonCount = buttonList.stream()
                .filter(webElement -> webElement.getText().equals("Remove")).count();

        System.out.println(removeButtonCount);
        Assert.assertEquals(removeButtonCount, 6, "Button name doesn't match");


        // Verify that all "Remove" buttons are enabled
        boolean areAllRemoveButtonsEnabled = buttonList.stream()
                .filter(webElement -> webElement.getText().equals("Remove")) // Filter only "Remove" buttons
                .allMatch(WebElement::isEnabled); // Check if all "Remove" buttons are enabled        Assert.assertTrue(areAllRemoveButtonsEnable, "Not all 'Remove' buttons are enabled.");

        Assert.assertTrue(areAllRemoveButtonsEnabled, "Not all 'Remove' buttons are enabled.");
        // Verify that cart icon count get change when remove the item
        buttonList.stream().filter(webElement -> webElement.getText().equals("Remove"))
                .forEach(WebElement::click);

    }



    // Verify to get the text of 'continue shopping' button

    @Test
    public void getContinueShoppingButton() {
        CartPage cPage = new CartPage(webDriver);
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        Assert.assertTrue(cPage.checkButtonIsEnabled(), "Button is not enabled");
        Assert.assertEquals(cPage.getContinueButtonText(), "Continue Shopping", "Incorrect Button Name");
        Assert.assertTrue(cPage.checkContinueButtonIsEnable(), "Continue shopping button is not enabled");
        cPage.clickContinueButton();
        Assert.assertEquals(landingPage.getTitle(), "Products", "Not landing to the correct page");
    }



    //Verify that checkout button
    @Test
    public void getCheckOutButton() {
        CartPage cPage = new CartPage(webDriver);
        Assert.assertTrue(cPage.checkContinueButtonIsEnable(), "Checkout button is not enabled");
        Assert.assertEquals(cPage.getCheckoutButtonText(), "Checkout", "Invalid button name");

    }


}
