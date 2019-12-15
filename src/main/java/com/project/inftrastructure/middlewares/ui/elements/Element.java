package com.project.inftrastructure.middlewares.ui.elements;

import com.sample.core.testUtils.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;

import java.util.List;

public class Element implements IElement {
    private WebElement webElement;
    private TestLogger LOG = TestLogger.getLogger();

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getInfo() {
        return this.webElement.toString();
    }

    @Override
    public void clear() {
        LOG.debug("Clearing element with selector" + getSelector());
        getWebElement().clear();
    }

    @Override
    public void click() {
        getWebElement().click();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        getWebElement().sendKeys(charSequences);
    }

    @Override
    public void sendKeys(String text) {
        LOG.debug("Entering text: " + text + " to element with selector" + getSelector());
        getWebElement().sendKeys(text);
    }

    @Override
    public String getText() {
        return getWebElement().getText();
    }

    @Override
    public WebElement getWebElement() {
        return webElement;
    }

    @Override
    public String getTagName() {
        return getWebElement().getTagName();
    }

    @Override
    public String getAttribute(String attribute) {
        return getWebElement().getAttribute(attribute);
    }

    @Override
    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getWebElement().isEnabled();
    }

    @Override
    public void submit() {
        getWebElement().submit();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWebElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getWebElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getWebElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getWebElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWebElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getWebElement().getRect();
    }

    @Override
    public String getCssValue(String cssValue) {
        return getWebElement().getCssValue(cssValue);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return getWrappedElement().getScreenshotAs(outputType);
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) getWebElement()).getCoordinates();
    }

    @Override
    public WebElement getWrappedElement() {
        return getWebElement();
    }

    public String getSelector() {
        return this.webElement.toString().split("]")[1];
    }

}