package com.pragmatic.selenium.pages;

public class ProductDetails {
    String productName;
    String productPrice;
    //String ProductImage;

    public ProductDetails(String productName, String productPrice){
        this.productName=productName;
        this.productPrice=productPrice;
        //this.productImage=productImage;
    }

    public String getProductName(){
        return productName;
    }

    public String getProductPrice(){
        return productPrice;
    }

//    public String getProductImage(){
//        return ProductImage
//    }

}
