package com.pragmatic.selenium;

import org.testng.annotations.DataProvider;

public class DataProviderSauceLabs {
    @DataProvider(name="login-credentials")
    public Object[][] userCredentials(){
        return new Object[][]{
                {"standard_user","secret_sauce"},

        };
}
}
