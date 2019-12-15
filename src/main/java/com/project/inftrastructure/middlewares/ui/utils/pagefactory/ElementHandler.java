package com.project.inftrastructure.middlewares.ui.utils.pagefactory;

import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.utils.ImplementedByProcessor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represent proxy between custom element and {@link WebElement}
 */
public class ElementHandler implements InvocationHandler {

    private final ElementLocator locator;
    private final Class<?> wrappingType;
    String name;
    String page;

    public <T> ElementHandler(Class<T> interfaceType, ElementLocator locator, String name, String page) {
        this.locator = locator;
        this.name = name;
        this.page = page;
        if (!Control.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("Interface not assignable to Control.");
        }
        this.wrappingType = ImplementedByProcessor.getWrapperClass(interfaceType);
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        final WebElement element;
        try {
            element = locator.findElement();
        } catch (NoSuchElementException e) {
            if ("toString".equals(method.getName())) {
                return "Proxy element for: " + locator.toString();
            }
            throw e;
        }
        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }
        Constructor<?> cons = wrappingType.getConstructor(WebElement.class, String.class, String.class);
        Object thing = cons.newInstance(element, name, page);
        try {
            return method.invoke(wrappingType.cast(thing), objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
