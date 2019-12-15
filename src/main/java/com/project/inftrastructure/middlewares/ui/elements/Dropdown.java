package com.project.inftrastructure.middlewares.ui.elements;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Dropdown extends Element {

    public Dropdown(WebElement webElement) {
        super(webElement);
    }

    public List<WebElement> getOptions() {
        return new Select(getWebElement()).getOptions();
    }

    public void selectByVisibleText(String text) {
      getOptions().stream().filter(i -> i.getText().contains(text))
                .findFirst().orElseThrow(() ->
                        new NoSuchElementException("There is no node with configuration name - " + text)).click();
    }

    public void deselectByVisibleText(String index) {
        new Select(getWebElement()).deselectByValue(index);
    }

    public void selectByValue(String value) {
        new Select(getWebElement()).selectByValue(value);
    }

    public void deselectByValue(String value) {
        new Select(getWebElement()).deselectByValue(value);
    }

    public void selectByIndex(int index) {
        new Select(getWebElement()).selectByIndex(index);
    }

    public void deselectByIndex(String index) {
        new Select(getWebElement()).deselectByValue(index);
    }

    public boolean isMultiple() {
        return new Select(getWebElement()).isMultiple();
    }

    public void deselectAll() {
        new Select(getWebElement()).deselectAll();
    }

    public WebElement getOptionByText(String configurationName) {
        return getOptions().stream().filter(i -> i.getText().contains(configurationName))
                .findFirst().orElseThrow(() ->
                        new NoSuchElementException("There is no node with configuration name - " + configurationName));
    }

    public WebElement getOptionByIndex(int index) {
        return new Select(getWebElement()).getOptions().get(index);
    }

    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    public WebElement getOptionByValue(String optionsValue) {
        return getOptions().stream().filter(i -> i.getCssValue("value").contains(optionsValue))
                .findFirst().orElseThrow(() ->
                        new NoSuchElementException("There is no node with value - " + optionsValue));
    }

    public void selectByText(String textToSelect) {
        getOptions().stream().filter(i -> i.getText().contains(textToSelect))
                .findFirst().orElseThrow(() ->
                new NoSuchElementException("There is no node with name - " + textToSelect)).click();
    }
}