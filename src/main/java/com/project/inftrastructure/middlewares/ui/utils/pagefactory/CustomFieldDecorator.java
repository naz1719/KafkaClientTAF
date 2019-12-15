package com.project.inftrastructure.middlewares.ui.utils.pagefactory;

import com.project.inftrastructure.middlewares.ui.annotations.Description;
import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import com.project.inftrastructure.middlewares.ui.annotations.Page;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.base.WebControl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Allow WebDriver to work with custom WebElement types like Checkbox or InputControl.
 */
public class CustomFieldDecorator implements FieldDecorator {

    private CustomElementLocatorFactory factory;

    public CustomFieldDecorator(CustomElementLocatorFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!(WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))) {
            return null;
        }
        if (field.getDeclaringClass() == WebControl.class) {
            return null;
        }
        ElementLocator locator;

            locator = factory.createLocator(field);

        if (locator == null) {
            return null;
        }
        Class<?> fieldType = field.getType();
        if (WebElement.class.equals(fieldType)) {
            fieldType = Control.class;
        }
        if (WebElement.class.isAssignableFrom(fieldType)) {
            return proxyForLocator(loader, fieldType, locator, getName(field), getPage(field));
        } else if (List.class.isAssignableFrom(fieldType)) {
            Class<?> erasureClass = getErasureClass(field);
            return proxyForListLocator(loader, erasureClass, locator, getName(field), getPage(field));
        } else {
            return null;
        }
    }

    private Class<?> getErasureClass(Field field) {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        return (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }

    private boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }
        Class<?> erasureClass = getErasureClass(field);
        if (erasureClass == null) {
            return false;
        }
        if (!WebElement.class.isAssignableFrom(erasureClass)) {
            return false;
        }
        if(field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null &&
                field.getAnnotation(FindAll.class) == null) {
            return false;
        }

        return true;
    }


    protected <T> T proxyForLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator, String name, String page) {
        InvocationHandler handler = new ElementHandler(interfaceType, locator, name, page);

        T proxy;
        proxy = interfaceType.cast(Proxy.newProxyInstance(
                loader, new Class[]{interfaceType, WebElement.class, WrapsElement.class, Locatable.class}, handler));
        return proxy;
    }


    @SuppressWarnings("unchecked")
    protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator, String name, String page) {
        InvocationHandler handler;
        if (interfaceType.getAnnotation(ImplementedBy.class) != null) {
            handler = new ElementListHandler(interfaceType, locator, name, page);
        } else {
            handler = new LocatingElementListHandler(locator);
        }
        List<T> proxy;
        proxy = (List<T>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }

    private String getName(Field field) {
        return field.isAnnotationPresent(Description.class) ? field
                .getAnnotation(Description.class).name() : field.getName();
    }

    private String getPage(Field field) {
        return field.getDeclaringClass().isAnnotationPresent(Page.class) ? field
                .getDeclaringClass().getAnnotation(Page.class).title()
                : "PAGE NOT DEFINED!!!";
    }

}
