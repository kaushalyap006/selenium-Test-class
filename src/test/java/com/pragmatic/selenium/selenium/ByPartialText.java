package com.pragmatic.selenium.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByPartialText extends By {
    private final String partialText;

    public ByPartialText(String partialText){
        this.partialText = partialText;
       // this.exactText=exactText;
    }

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        // Correct XPath to search for elements containing the partial text
        String xpath = String.format("//*[contains(text(), '%s')]", partialText);
        System.out.println("xpath = " + xpath);  // For debugging purposes
        return searchContext.findElements(By.xpath(xpath));  // Use the constructed xpath
    }
}
