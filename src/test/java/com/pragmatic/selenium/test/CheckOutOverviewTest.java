package com.pragmatic.selenium.test;

import com.pragmatic.selenium.base.BaseTest;
import com.pragmatic.selenium.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckOutOverviewTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(CheckOutOverviewTest.class);



    // Verify Page heading
    @Test
    public void getPageHeading() {
        toCheckoutOverviewFlow();

        CheckoutOverviewPage overview = new CheckoutOverviewPage(webDriver);
        String CheckoutOverviewPageTitle = overview.getPageHeading();
        Assert.assertEquals(CheckoutOverviewPageTitle, "Checkout: Overview", "Incorrect page heading");
        logger.debug(" CheckOut Page Title is: {}", CheckoutOverviewPageTitle);
    }


    //Verify cancel button
    @Test
    public void checkCancelButton() {
        toCheckoutOverviewFlow();

        CheckoutOverviewPage overview = new CheckoutOverviewPage(webDriver);

        Assert.assertTrue(overview.checkCancelButtonIsEnabled(), "button is not enabled ");
        Assert.assertEquals(overview.getCancelButtonText(), "Cancel", "Incorrect Text");
        overview.clickCancelButton();
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        Assert.assertEquals(landingPage.getTitle(), "Products", "Incorrect page redirection");
    }


    //Verify Finish Button
    @Test
    public void checkFinishButton() {
        toCheckoutOverviewFlow();

        CheckoutOverviewPage overview = new CheckoutOverviewPage(webDriver);

        Assert.assertTrue(overview.checkFinishButtonIsEnabled(), "button is not enabled ");
        Assert.assertEquals(overview.getFinishButtonText(), "Finish", "Incorrect Text");
    }


    // Verify product items and checkout items are match
    @Test
    public void checkVisibleProductDetails() {
        toCheckoutOverviewFlow();

        ProductComparePage comparePage = new ProductComparePage(webDriver);
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);

        // Get product names and prices from the landing page
        List<String> itemNameFromLanding = comparePage.landingPageProductName();
        List<String> itemPriceFromLanding = comparePage.landingPageProductPrice();

        // Get inventory list and extract product names and prices
        List<WebElement> inventoryList = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));
        List<String> itemNames = inventoryList.stream()
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                .toList();


        List<String> itemPrice = inventoryList.stream()
                .map(webElement -> {
                    return webElement.findElement(By.cssSelector("[data-test='inventory-item-price']"))
                            .getText()
                            .trim().replaceAll("[^\\d.]", "");
                })
                .toList();

        // Debug logs

        logger.debug("Inventory items: {}", itemPrice);
        logger.debug("Landing page product names: {}", itemPriceFromLanding);

        logger.debug("Inventory items: {}", itemNames);
        logger.debug("Landing page product names: {}", itemNameFromLanding);

        boolean namesMatch = itemNames.containsAll(itemNameFromLanding) && itemNameFromLanding.containsAll(itemNames);
        boolean pricesMatch = itemPrice.containsAll(itemPriceFromLanding) && itemPriceFromLanding.containsAll(itemPrice);

        //Compare landing page names/prices with checkout page names & prices
        // boolean match = itemNames.equals(itemNameFromLanding) && itemPrice.equals(itemPriceFromLanding);
        if (namesMatch && pricesMatch) {
            logger.debug("Both product lists match.");
        } else {
            logger.debug("Product lists do not match.");
        }
        Assert.assertTrue(namesMatch && pricesMatch, "Product names or prices do not match.");

    }


    // Verify that Item total is correct.
    @Test
    public void getPriceDetails() {
        toCheckoutOverviewFlow();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);
        List<WebElement> inventoryList = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));

        double expectedItemTotal = inventoryList.stream()
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-price']"))
                        .getText().trim().replaceAll("[^\\d.]", ""))
                .mapToDouble(Double::parseDouble)
                .sum();

        logger.debug("Total Price from added items{} ", expectedItemTotal);

        //Verify that total price is match with
        String ItemTotalText = overviewPage.getItemTotalText();

        double actualItemTotal = Double.parseDouble(ItemTotalText);
        logger.debug("Total Price from checkout page{} ", actualItemTotal);

        Assert.assertEquals(actualItemTotal, expectedItemTotal, "Item total doesn't match");
        Assert.assertTrue(Double.compare(actualItemTotal, expectedItemTotal) == 0, "Item total doesn't match.");

        //Verify that total price is correct

        String actualTotalValueText = overviewPage.getActualTotalValueText();
        double actualTotalPrice = Double.parseDouble(actualTotalValueText);
        logger.debug("Actual Total price is:{}", actualTotalPrice);

        String taxLabel = overviewPage.getTaxText();
        double tax = Double.parseDouble(taxLabel);
        logger.debug("Tax value{}", tax);
        double expectedTotalPrice = expectedItemTotal + tax;
        logger.debug("expected Total price is(Total Price from added items +Tax value) :{}", expectedTotalPrice);
        Assert.assertEquals(actualTotalPrice, expectedTotalPrice, " Total price doesn't match");
        //Assert.assertTrue(Double.compare(actualTotalPrice, expectedTotalPrice) == 0, "Item total doesn't match.");

    }

}




