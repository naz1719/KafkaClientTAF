package com.project.inftrastructure.middlewares.ui.controls.base;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    Control moveToElement(WebDriver driver);
    Control highlightElement(WebDriver driver);
    Control focusJs(WebDriver driver);
    WebElement expandRootElement(WebElement element, WebDriver driver);

    Control isElementIsClickable(int sec);
    Control isElementIsVisible(int sec);
    Control waitForStaleness(int sec);
    Control clickJS();

}
