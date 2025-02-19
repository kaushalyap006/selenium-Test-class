package com.pragmatic.selenium.pages;

public class ProductDetails {
    private String name;
    private String price;

    public ProductDetails(String name, String price){
        this.name= name;
        this.price= price;
    }

    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
}

