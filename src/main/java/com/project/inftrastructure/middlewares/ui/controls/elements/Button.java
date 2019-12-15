package com.project.inftrastructure.middlewares.ui.controls.elements;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.elements.impl.ButtonControl;
import org.openqa.selenium.WebDriver;

@ImplementedBy(ButtonControl.class)
public interface Button extends Control {
    void clickJS(WebDriver driver);
}
