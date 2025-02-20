package com.pragmatic.selenium.test;

import com.pragmatic.selenium.TestData.TestData;
import com.pragmatic.selenium.pages.ProductDetails;
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

import java.util.ArrayList;
import java.util.List;

public class SauceLandingTest {
    WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(SauceLandingTest.class);

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    //Get all the product name/price/image


    // Get all the product name/price/image
    @Test(dataProvider = "product_data", dataProviderClass = TestData.class)
    public void getAllProductDetails(String exName, String exPrice) {
        // Initialize SauceLandingPage object
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);

        // Find all product elements using the correct locator
        List<WebElement> productElements = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));

        // Create a list to hold expected products
        List<ProductDetails> expectedProductList = new ArrayList<>();

        // Add expected products from the data provider to the list
       // expectedProductList.add(new ProductDetails(exName, exPrice));


        //loop through each element
        for (int i = 0; i < productElements.size(); i++) {
            WebElement product = productElements.get(i);
            //Get the actual product name & price
            String actualProductName = landingPage.getProductName(product);
            String actualPriceName = landingPage.getPriceName(product);

            //Log actual Name and price
            logger.debug("Actual-Name: {}, Price: {}", actualProductName, actualPriceName);

            ProductDetails expectedProduct = expectedProductList.get(i);


            //Log expected product name and price
            logger.debug("Expected-Name: {}, Price: {}", exName, exPrice);


            //Assert that the actual product details match with expected product details
            Assert.assertEquals(actualProductName, exName,"product Name doesn't match");
            Assert.assertEquals(actualPriceName, exPrice,"Product Price doesn't match");

        }}}
}
