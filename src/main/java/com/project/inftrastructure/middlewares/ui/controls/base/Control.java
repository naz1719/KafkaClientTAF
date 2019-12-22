package com.project.inftrastructure.middlewares.ui.controls.base;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

@ImplementedBy(WebControl.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    WebControl moveToElement(WebDriver driver);
    WebControl highlightElement(WebDriver driver);
    WebControl focusJs(WebDriver driver);
    WebElement expandRootElement(WebElement element, WebDriver driver);
}
