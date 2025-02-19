package com.pragmatic.selenium.pages;

import com.pragmatic.selenium.test.ProductTest;
import io.cucumber.messages.types.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    By byheading = By.cssSelector("[data-test='title']");
    By byShoppingCart = By.cssSelector("[data-test='shopping-cart-badge']");
    By bycartBtn = By.cssSelector("[data-test^='add-to-cart-sauce-labs']");
    By removeBtnName = By.cssSelector("[data-test^='remove-sauce-labs']");
    By byProductName = By.cssSelector(".inventory_item_name");
    By byProductPrice = By.cssSelector(".inventory_item_price");
    By byProductImg = By.className("inventory_item_img");


    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public String headingCheck() {
        return webDriver.findElement(byheading).getText();
    }

    public void itemClick() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        List<WebElement> productElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bycartBtn));

        for (WebElement product : productElements) {
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(product));
            addToCartButton.click();
        }
    }

    public String getNoOfItems() {
        itemClick();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(byShoppingCart));
        return cartIcon.getText();

    }


    public String btnNameChange() {
        itemClick();
        return webDriver.findElement(removeBtnName).getText();
    }

    public List<ProductDetails> productDetails() {
        List<ProductDetails> productlist = new ArrayList<>();
        List<WebElement> productElements = webDriver.findElements(By.cssSelector("[data-test= inventory-item]"));

        for (WebElement product : productElements) {
            String actualProductName = product.findElement(byProductName).getText();
            String actualProductPrice = product.findElement(byProductPrice).getText();
            ProductDetails productDetails = new ProductDetails(actualProductName,actualProductPrice);
            productlist.add(productDetails);
        }
        return productlist;
    }


}



