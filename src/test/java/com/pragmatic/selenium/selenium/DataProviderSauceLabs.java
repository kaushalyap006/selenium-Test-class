package com.pragmatic.selenium.selenium;

import org.testng.annotations.DataProvider;

public class DataProviderSauceLabs {


    @DataProvider(name = "Invalid_Credentials")
    public Object[][] Invalid_Credentials() {
        return new Object[][]{
                //Test Case 1.2: Verify login with an invalid username.
                {"abcd", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},

                //Test Case 1.3: Verify login with an invalid password.
                {"standard_user", "asas", "Epic sadface: Username and password do not match any user in this service"},

                //Test Case 1.4: Verify login with empty username and password fields.
                {"", "", "Epic sadface: Username is required"},

                //Test Case 1.5: Verify login with locked-out user credentials (e.g., `locked_out_user`).
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
//
                //Test Case 1.7: Verify login with blank password
                {"standard_user", "", "Epic sadface: Password is required"},

        };
    }

    @DataProvider(name = "valid_Credentials")
    public Object[][] validCredentials() {
        return new Object[][]{
                //Test Case 1.1: Verify login with valid username and password.
                {"standard_user", "secret_sauce"},
                //Test Case 1.6: Verify login with performance glitch user credentials.
                //     {"performance_glitch_user", "secret_sauce"},
        };

    }


}



