package com.pragmatic.selenium.test;

import com.pragmatic.selenium.TestData.TestData;
import com.pragmatic.selenium.base.BaseTest;
import com.pragmatic.selenium.pages.SauceLandingPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SauceLandingTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SauceLandingTest.class);


    // Get all the product name/price/image
    @Test(dataProvider = "product_data", dataProviderClass = TestData.class)
    public void getAllProductDetails(String expectedName, String expectedPrice) {
        setLogin();

        List<WebElement> inventoryList = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));

        long productCountWithGivenPriceAndName = inventoryList.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']"))
                        .getText().equals(expectedName))
                .filter(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-price']"))
                        .getText().equals(expectedPrice)).count();
        Assert.assertEquals(productCountWithGivenPriceAndName, 1, "product name doesn't match");

        logger.debug("Expected-Name: {}, Price: {}", expectedName, expectedPrice);

    }



    //Verify no of items which is displayed in cart icon
    @Test
    public void getNumberOfProducts() {
        setLogin();

        // Initialize SauceLandingPage object
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        //Calling to clickAddToCartButton
        landingPage.clickAddToCartButton();

        Assert.assertEquals(landingPage.getRemoveButtonText(), "Remove");

        String actualItems = landingPage.getNoOfItems();
        Assert.assertEquals(actualItems, "6");
        logger.debug("Items in cart: {}", actualItems);

        List<WebElement> inventoryList = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));

        List<String> itemNames = inventoryList.stream()
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                .toList();
        logger.debug("Inventory items: {}", itemNames);


        List<String> itemPrice = inventoryList.stream()
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-price']"))
                        .getText())
                .toList();
        logger.debug("Inventory items: {}", itemPrice);

    }

}





