package com.project.inftrastructure.middlewares.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

public interface IElement extends WebElement, WrapsElement, Locatable {
    String getInfo();

    void clear();

    void click();

    void sendKeys(String text);

    String getText();

    WebElement getWebElement();

    String getTagName();

    String getAttribute(String attribute);

    boolean isSelected();

    boolean isEnabled();

    void submit();

    void sendKeys(CharSequence... charSequences);

    List<WebElement> findElements(By by);

    WebElement findElement(By by);

    boolean isDisplayed();

    Point getLocation();

    Dimension getSize();

    Rectangle getRect();

    String getCssValue(String cssValue);

    <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException;

    Coordinates getCoordinates();

    WebElement getWrappedElement();
}
