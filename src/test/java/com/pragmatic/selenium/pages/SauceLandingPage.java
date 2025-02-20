package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLandingPage {
    WebDriver webDriver;

    @FindBy(css="span.title")
    WebElement eleTitle;

    By byProductName= By.className("inventory_item_name");
    By byproductPrice= By.className("inventory_item_price");

    public SauceLandingPage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String getTitle() {
      return   eleTitle.getText();
    }

// Method to get the product name from a specific product element
    public String getProductName(WebElement product) {
        //return webDriver.findElement(byProductName).getText();
        return product.findElement(byProductName).getText();
    }

    public String getPriceName(WebElement product) {
        //return webDriver.findElement(byproductPrice).getText();
        return product.findElement(byproductPrice).getText();
    }
}
