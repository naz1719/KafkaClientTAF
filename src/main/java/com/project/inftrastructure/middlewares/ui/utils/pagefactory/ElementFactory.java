package com.project.inftrastructure.middlewares.ui.utils.pagefactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Provide functionality for initialization pages and custom element (as native Selenium Page Factory)
 */
public class ElementFactory {

    private ElementFactory(){}

    public static void initElements(WebDriver driver, final Object page) {
        initElements(new CustomElementLocatorFactory(driver), page);
    }

    public static void initElements(CustomElementLocatorFactory factory, Object page) {
        final CustomElementLocatorFactory factoryRef = factory;
        PageFactory.initElements(new CustomFieldDecorator(factoryRef), page);
    }

    public static void initElements(FieldDecorator decorator, Object page) {
        PageFactory.initElements(decorator, page);
    }

}
