package com.project.inftrastructure.middlewares.ui.utils.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Factory for locator creation based on annotations {@link org.openqa.selenium.support.FindBy}
 */
public class CustomElementLocatorFactory implements ElementLocatorFactory {
    private final WebDriver driver;

    public CustomElementLocatorFactory(final WebDriver driver)
    {
        this.driver = driver;
    }
    @Override
    public ElementLocator createLocator(final Field field) {
            return new DefaultElementLocator(driver, field);
    }
}
