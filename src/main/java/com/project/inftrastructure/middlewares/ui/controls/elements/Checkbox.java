package com.project.inftrastructure.middlewares.ui.controls.elements;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.base.WebControl;
import com.project.inftrastructure.middlewares.ui.controls.elements.impl.CheckboxControl;
import org.openqa.selenium.WebDriver;

@ImplementedBy(CheckboxControl.class)
public interface Checkbox extends Control {
    WebControl changeCheckboxState(boolean state, WebDriver driver);
}
