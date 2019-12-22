package com.project.inftrastructure.middlewares.ui.controls.base;

import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.execution.logger.templates.MessageTemplatesUI;
import com.project.inftrastructure.middlewares.ui.controls.elements.impl.CheckboxControl;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

/**
 * Wrapper around {@link WebElement} with needed features.
 * Every custom control, like {@link CheckboxControl} extends from this WebControl.
 */
public class ControlBase implements Control {
    protected static final TestLogger LOG = TestLogger.getLogger();
    protected final WebElement webElement;
    protected final String name;
    protected final String page;
    private String message;

    public ControlBase(final WebElement webElement, final String name, final String page) {
        this.webElement = webElement;
        this.name = name;
        this.page = page;

    }

    /**
     * This method does void click
     * @deprecated As of it void it doesn't allow use chain approach, use {@link #chainClick()} instead.
     */
    @Override
    public void click() {
        message = String.format(MessageTemplatesUI.CLICK_ON, name, page);
        LOG.info(message);
        webElement.click();
    }

    public ControlBase chainClick(){
        click();
        return this;
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }


    @Override
    public void submit() {
        webElement.submit();
    }


    @Override
    public String getAttribute(String attributeName) {
        message = String.format(MessageTemplatesUI.GET_ATTRIBUTE, attributeName, name, page);
        LOG.info(message);
        return webElement.getAttribute(attributeName);
    }


    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }


    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public String getText() {
        message = String.format(MessageTemplatesUI.GET_TEXT, name, page);
        LOG.info(message);
        return webElement.getText();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public boolean isSelected() {
        message = String.format(MessageTemplatesUI.IS_SELECTED, name, page);
        LOG.info(message);
        return webElement.isSelected();
    }

    @Override
    public WebElement findElement(By by) {
        message = String.format(MessageTemplatesUI.FIND_ELEMENT, name, page);
        LOG.info(message);
        return webElement.findElement(by);
    }

    @Override
    public boolean isEnabled() {
        message = String.format(MessageTemplatesUI.IS_ENABLED, name, page);
        LOG.info(message);
        return webElement.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return getWrappedElement().isDisplayed();
    }

    @Override
    public void clear() {
        message = String.format(MessageTemplatesUI.CLEAR_TEXT, name, page);
        LOG.info(message);
        webElement.clear();
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) webElement).getCoordinates();
    }

    @Override
    public Rectangle getRect(){
        return this.webElement.getRect();}

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) {
        message = String.format(MessageTemplatesUI.GET_SCREENSHOT, name, page);
        LOG.info(message);
        return this.webElement.getScreenshotAs(outputType);
    }


    public ControlBase moveToElement(WebDriver driver){
        message = String.format(MessageTemplatesUI.MOVE_TO, name, page);
        LOG.info(message);
        new Actions(driver).moveToElement(webElement).perform();
        return this;
    }

    public ControlBase focusJs(WebDriver driver) {
        message = String.format(MessageTemplatesUI.FOCUS_ON, name, page);
        LOG.info(message);
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", webElement);
        return this;
    }

    public ControlBase highlightElement(WebDriver driver) {
        String bg = webElement.getCssValue("backgroundColor");
        for (int i = 0; i < 3; i++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='red'", webElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='" + bg + "'", webElement);
        }
        return this;
    }

    /**
     *
     * @param element that belongs to first shadow root
     * @return converted shadow element that can be used entry point for inner shadow elements
     */
    public WebElement expandRootElement(WebElement element, WebDriver driver){
        return (WebElement)((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot",element);
    }
}
