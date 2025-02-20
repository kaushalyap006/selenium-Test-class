package com.pragmatic.selenium.TestData;

import org.testng.annotations.DataProvider;

public class TestData {



    @DataProvider(name = "user-credentials")
    public Object[][] userCredentials() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"abcd", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "asas", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "", "Epic sadface: Password is required"},

        };
    }

    @DataProvider(name = "product_data")
    public Object[][] productData() {
        return new Object[][]{
                {"Sauce Labs Backpack", "$29.99"},
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
                {"Sauce Labs Fleece Jacket", "$49.99"},
                {"Sauce Labs Onesie", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"},
        };
    }

//    @DataProvider(name = "product_data")
//    public Object[][] testProductData() {
//        return new Object[][]{
//                {"Sauce Labs Backpack", "$29.99", "inventory-item-sauce-labs-backpack-img"},
//            {"Sauce Labs Bike Light", "$9.99", "inventory-item-sauce-labs-bike-light-img"},
//            {"Sauce Labs Bolt T-Shirt", "$15.99", "inventory-item-sauce-labs-bolt-t-shirt-img"},
//            {"Sauce Labs Fleece Jacket", "$49.99", "inventory-item-sauce-labs-fleece-jacket-img"},
//            {"Sauce Labs Onesie", "$7.99", "inventory-item-sauce-labs-onesie-img"},
//            {"Test.allTheThings() T-Shirt (Red)", "$15.99", "inventory-item-test.allthethings()-t-shirt-(red)-img"},
//
//        } ;

    }

