package com.pragmatic.selenium.pages;

public class ProductDetails {
    String productName;
    String productPrice;


    public ProductDetails(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;

    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

}
