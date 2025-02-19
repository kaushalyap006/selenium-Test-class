package com.pragmatic.selenium.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



    @CucumberOptions(
            features = {"src/test/resources/features/sauce-login.feature"},
            glue={"com.pragmatic.selenium.steps"},
            monochrome = true,
            plugin = {
                    "pretty",
                    "html:target/cucumber-reports.html",
                    "json:target/cucumber.json"
            }

    )
    public class TestRunner extends AbstractTestNGCucumberTests {

}
