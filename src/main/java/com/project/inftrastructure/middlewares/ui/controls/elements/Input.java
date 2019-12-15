package com.project.inftrastructure.middlewares.ui.controls.elements;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.elements.impl.InputControl;

@ImplementedBy(InputControl.class)
public interface Input extends Control {
    void sendText(String text);
}
