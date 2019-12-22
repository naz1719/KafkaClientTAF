package com.project.inftrastructure.middlewares.ui.utils.pagefactory;

import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.utils.ImplementedByProcessor;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * Represent proxy for List of custom element
 */
public class ElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<?> wrappingType;
    private final Logger LOG = Logger.getLogger(ElementListHandler.class);
    private String name;
    private String page;

    <T> ElementListHandler(Class<T> interfaceType, ElementLocator locator, String name, String page) {
        this.locator = locator;
        this.name = name;
        this.page = page;
        if(!Control.class.isAssignableFrom(interfaceType)) {
            LOG.error("Interface not assignable to Control");
            throw new RuntimeException("interface not assignable to Control");
        }
        this.wrappingType = ImplementedByProcessor.getWrapperClass(interfaceType);

    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        List<Object> wrappedList = new ArrayList<>();
        Constructor<?> cons = wrappingType.getConstructor(WebElement.class, String.class, String.class);
        for (WebElement element : locator.findElements()) {
            Object thing = cons.newInstance(element, name, page);
            wrappedList.add(wrappingType.cast(thing));
        }
        try {
            return method.invoke(wrappedList, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }


}
