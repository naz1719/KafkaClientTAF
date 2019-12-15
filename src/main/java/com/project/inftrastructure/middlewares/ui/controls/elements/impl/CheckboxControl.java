package com.project.inftrastructure.middlewares.ui.controls.elements.impl;

import com.project.inftrastructure.middlewares.ui.controls.base.WebControl;
import com.project.inftrastructure.middlewares.ui.controls.elements.Checkbox;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxControl extends WebControl implements Checkbox {
    public CheckboxControl(WebElement webElement, String name, String page) {
        super(webElement , name , page);
    }

    public void changeCheckboxState(boolean requiredCheckboxState, WebDriver driver) {
        if(getWrappedElement().getAttribute("aria-checked") == null){
            throw new InvalidElementStateException("Element is not checkbox");
        }
        else if (getWrappedElement().isSelected() != requiredCheckboxState) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWrappedElement());
        }
    }
}
