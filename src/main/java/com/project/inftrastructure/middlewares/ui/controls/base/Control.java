package com.project.inftrastructure.middlewares.ui.controls.base;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    ControlBase moveToElement(WebDriver driver);
    ControlBase highlightElement(WebDriver driver);
    ControlBase focusJs(WebDriver driver);
    WebElement expandRootElement(WebElement element, WebDriver driver);
}
