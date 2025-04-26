package com.pragmatic.selenium.pages;

import com.pragmatic.selenium.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductComparePage extends BaseTest {
    WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(ProductComparePage.class);

    public ProductComparePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


//get the product name from landing page
    public List<String> landingPageProductName() {
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        //Calling to clickAddToCartButton
        landingPage.clickAddToCartButton();

        List<WebElement> inventoryList1 = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));
        logger.debug("Number of inventory items found: {}", inventoryList1.size());

        return inventoryList1.stream()
                //.filter(webElement -> !webElement.findElements(By.xpath("//button[contains(@data-test, 'remove')]")).isEmpty())
                //.filter(webElement -> webElement.getText().equals("Remove"))
                .map(webElement -> webElement.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText())
                .toList();
    }


// get the product prices from landing page
    public List<String> landingPageProductPrice() {
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        //Calling to clickAddToCartButton
        landingPage.clickAddToCartButton();

        List<WebElement> inventoryList = webDriver.findElements(By.cssSelector("[data-test='inventory-item']"));

        return
                inventoryList.stream()
                        .map(webElement -> {
                            return webElement.findElement(By.cssSelector("[data-test='inventory-item-price']"))
                                    .getText()
                                    .trim().replaceAll("[^\\d.]", "");
                        })
                        .toList();

    }


}





