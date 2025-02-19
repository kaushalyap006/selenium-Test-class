package com.pragmatic.selenium.selenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public class Check implements ICheck, WrapsElement {
    private final WebElement checkBox;

    public Check(WebElement checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public void click() {
        checkBox.click();
    }


    @Override
    public boolean isSelected() {
        return checkBox.isSelected();
    }


    public boolean isVisible()
    {
        return checkBox.isDisplayed();
    }

    public String getLabel()
    {
        return checkBox.getText();
    }

    @Override
    public void check() {
        if (!isSelected()){
            click();
        }
    }

    @Override
    public void uncheck() {
        if (isSelected()){
            click();
        }
    }



    @Override
    public WebElement getWrappedElement() {
        return checkBox;


    }
}

