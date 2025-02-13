package com.pragmatic.selenium.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public class Button implements IButton, WrapsElement {
    private final WebElement btnElement;

    public Button(WebElement btnElement) {
        this.btnElement = btnElement;
    }

    public void click() {
        btnElement.click();
    }

    @Override
    public boolean isEnabled() {
        return btnElement.isEnabled();
    }


    @Override
    public boolean isVisible() {
        return btnElement.isDisplayed();
    }

    @Override
    public String getText() {
        return btnElement.getDomAttribute("value");
    }

    @Override
    public WebElement getWrappedElement() {
        return btnElement;
    }
}
