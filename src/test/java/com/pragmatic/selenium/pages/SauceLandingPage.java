package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SauceLandingPage {
    WebDriver webDriver;

    @FindBy(css = "span.title")
    WebElement eleTitle;

    @FindBy(css = "[data-test='shopping-cart-link']")
    WebElement shoppingCart;

    By BypButton = By.xpath("//button[contains(@data-test, 'add-to-cart')]");
    By bycItem = By.cssSelector(".shopping_cart_badge");
    By removeButton = By.xpath("//button[contains(@data-test, 'remove')]");
    By byProductName = By.className("inventory_item_name");
    By byproductPrice = By.className("inventory_item_price");


    public SauceLandingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public String getTitle() {
        return eleTitle.getText();
    }


    // Method to get the product name from a specific product element
    public String getProductName(WebElement product) {
        return product.findElement(byProductName).getText();
    }


    public String getPriceName(WebElement product) {
        return product.findElement(byproductPrice).getText();
    }

    public String getNoOfItems() {
        return webDriver.findElement(bycItem).getText();

    }


    public void clickAddToCartButton() {

        List<WebElement> ProductItems = webDriver.findElements(BypButton);
        for (int i = 0; i < ProductItems.size(); i++) {
            WebElement item = ProductItems.get(i);
            item.click();
        }

    }


    public String getRemoveButtonText() {
        return webDriver.findElement(removeButton).getText();
    }

    public void clickCart() {
        shoppingCart.click();

    }
}
