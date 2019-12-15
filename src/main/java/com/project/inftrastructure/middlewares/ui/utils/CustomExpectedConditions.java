package com.project.inftrastructure.middlewares.ui.utils;

import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.elements.Table;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Class that provides expected conditions for driver fluent waits
 */
public class CustomExpectedConditions {

    private CustomExpectedConditions(){
        throw new IllegalStateException("CustomExpectedConditions class is not expected to be instantiated");
    }

    private static Logger logger = LoggerFactory.getLogger("Expected Condition");

    /**
     * Wait until list of elements becomes visible
     * @param controls list to wait for
     * @return expected condition that wait for list to visible
     */
    public static ExpectedCondition<List<? extends Control>> visibilityOfAllElements(final List<? extends Control> controls) {
        return new ExpectedCondition<List<? extends Control>>() {
            public List<? extends Control> apply(WebDriver driver) {
                Iterator var2 = controls.iterator();
                Control control;
                do {
                    if (!var2.hasNext()) {
                        return !controls.isEmpty() ? controls : Collections.emptyList();
                    }
                    control = (Control) var2.next();
                } while (control.isDisplayed());
                return Collections.emptyList();
            }
            public String toString() {
                return "visibility of all " + controls;
            }
        };
    }

    /**
     *
     * @param element webElement that represent column to sort by
     * @param condition condition of sorting ASD, DESC
     * @param table table to sort
     * @param columnName column to sort by
     * @return expected condition to click on column until it will be sorted
     */
    public static ExpectedCondition<Boolean> conditionIsSelected(
            final WebElement element, final String condition, Table table, String columnName, WebElement el) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    table.clickOnColumnToSort(columnName, driver, el);
                    String elementCondition = element.getAttribute("aria-sort");
                    return elementCondition.equals(condition);
                } catch (StaleElementReferenceException e) {
                    logger.info("Can't interact with element");
                    return false;
                }
            }
            public String toString() {
                return String.format("Condition ('%s') to be set in element %s",
                        condition, element);
            }
        };
    }
}
