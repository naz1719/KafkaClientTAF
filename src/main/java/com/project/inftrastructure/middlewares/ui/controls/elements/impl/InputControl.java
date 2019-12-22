package com.project.inftrastructure.middlewares.ui.controls.elements.impl;

import com.project.inftrastructure.execution.logger.templates.MessageTemplatesUI;
import com.project.inftrastructure.middlewares.ui.controls.base.WebControl;
import com.project.inftrastructure.middlewares.ui.controls.elements.Input;
import org.openqa.selenium.WebElement;

public class InputControl extends WebControl implements Input {
    public InputControl(WebElement webElement, String name, String page) {
        super(webElement, name, page);
    }

    public WebControl clearAndType(String text) {
        getWrappedElement().clear();
        getWrappedElement().sendKeys(text);
        String message = String.format(MessageTemplatesUI.TYPE_TEXT, text, name, page);
        LOG.info(message);
        return this;
    }
}
