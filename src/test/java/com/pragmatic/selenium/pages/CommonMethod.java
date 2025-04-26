package com.pragmatic.selenium.pages;

import org.openqa.selenium.WebDriver;

public class CommonMethod {
    WebDriver webDriver;

    public CommonMethod(WebDriver webDriver) {
        this.webDriver=webDriver;
    }



    public void setCart(){
        SauceLandingPage landingPage = new SauceLandingPage(webDriver);
        landingPage.clickAddToCartButton();
        landingPage.clickCart();
    }

    public void setNavigationToCheckOut(){
        CartPage cPage = new CartPage(webDriver);
        cPage.clickCheckOutButton();
    }

    public void setCheckOutPage(){
        CheckOutInformationPage checkOutInfo = new CheckOutInformationPage(webDriver);
        // click on checkout button
        checkOutInfo.typeFirstName("TestFirstName").typeLastName("TestLastName").typePostalCode("111111").clickContinueButton();
    }

    public void setOrderCompletion(){
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(webDriver);

        overviewPage.clickFinishButton();

    }
}
