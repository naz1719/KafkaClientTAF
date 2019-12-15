package com.project.inftrastructure.middlewares.ui.elements;

import org.openqa.selenium.WebElement;

public class CheckBox extends Element {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public void toggle() {
        getWrappedElement().click();
    }

    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }
}