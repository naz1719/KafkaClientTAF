package com.project.inftrastructure.middlewares.ui.utils;

import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

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

    private final static Logger LOG = Logger.getLogger("Expected Condition");

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
}
