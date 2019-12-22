package com.project.inftrastructure.middlewares.ui.controls.base;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    Control moveToElement(WebDriver driver);
    Control highlightElement(WebDriver driver);
    Control focusJs(WebDriver driver);
    WebElement expandRootElement(WebElement element, WebDriver driver);

    List<WebElement> findElementsWithTimeout(By by);
//    List<? extends Control> findElementsWithTimeout(List<? extends Control> webElementList , int sec);

    Control isElementIsClickable(int sec);
    Control isElementIsVisible(int sec);
    Control waitForStaleness(int sec);
    Control clickJS();

}
