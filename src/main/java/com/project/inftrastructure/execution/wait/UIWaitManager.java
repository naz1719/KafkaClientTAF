package com.project.inftrastructure.execution.wait;

import com.project.inftrastructure.middlewares.ui.driver.WebDriverManager;
import com.project.inftrastructure.middlewares.ui.elements.Element;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.project.inftrastructure.middlewares.ui.driver.WebDriverManager.getDriver;

public class UIWaitManager {
    public static final int DEFAULT_TIME_OUT = 60;// Seconds
    private static final UIWaitManager instance = new UIWaitManager();
    private static final long DEFAULT_POLLING = 1L;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final String ELEMENT_NOT_CLICKABLE = "Element wasn't clickable in %s sec";
    private static final String ERROR_WAITING = "Some problem occurs during waiting";
    private static final int COUNT_RETRY = 5;
    private static final int DURATION = 3;
    private static final Logger LOG = Logger.getLogger(UIWaitManager.class);
    private static final int COUNT_RETRY_INCYCLE = 50;
    private static final int RETRY_CYCLE_SLEEP = 500;
    private long timeOut;
    private TimeUnit timeUnitForTimeOut;
    private long polling;
    private TimeUnit timeUnitForPolling;

    public UIWaitManager(int timeOut, TimeUnit unit) {
        this.timeOut = timeOut;
        this.timeUnitForTimeOut = unit;
        this.polling = DEFAULT_POLLING;
        this.timeUnitForPolling = DEFAULT_TIME_UNIT;
    }

    private UIWaitManager() {
        this.timeOut = DEFAULT_TIME_OUT;
        this.timeUnitForTimeOut = DEFAULT_TIME_UNIT;
        this.polling = DEFAULT_POLLING;
        this.timeUnitForPolling = DEFAULT_TIME_UNIT;
    }

    public static UIWaitManager getInstance() {
        return instance;
    }

    //    WaitManager.waitForCondition(() -> isContainerVisible, false);
    public static void waitForCondition(Callable<Boolean> callable, boolean stopWaitIfExceptionOccurred) {
        long beginTime = System.currentTimeMillis();
        boolean continueWaiting = true;
        while (continueWaiting && ((System.currentTimeMillis() - beginTime) < DEFAULT_TIME_OUT * 1000)) {
            try {
                if (callable.call()) {
                    continueWaiting = false;
                }
            } catch (Exception e) {
                if (stopWaitIfExceptionOccurred) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver -> {
            if (driver != null) {
                return WebDriverManager.executeScript("return document.readyState").toString().equals("complete");
            }
            throw new NullPointerException("Exception occurred while 'waitForPageLoaded', driver=null");
        };

        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(expectation);
    }

    public static void waitForCondition(ExpectedCondition expectedCondition) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 60);
        webDriverWait.until(expectedCondition);
    }

    public static void sleepTimeOut(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitTimeOut(int time) {
        getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }

    public <T extends WebElement> void untilClickable(T element) {
        untilClickable(element, COUNT_RETRY);
    }

    public <T extends WebElement> void untilClickable(T element, final int retryCount) {
        int retry = 0;
        while (retry < retryCount) {
            try {
                if (isClickable(element)) {
                    return;
                } else {
                    Sleeper.SYSTEM_SLEEPER.sleep(new Duration(DURATION, DEFAULT_TIME_UNIT));
                }
            } catch (InterruptedException e) {
                error(ERROR_WAITING);
            }
            retry++;
        }
        error(String.format(ELEMENT_NOT_CLICKABLE, retryCount * DURATION));
    }


    /**
     * Usage example:
     * {@code new WaitManager(5, TimeUnit.SECONDS).waitConstTime();}
     */
    public void waitConstTime() {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(timeOut, timeUnitForTimeOut));
        } catch (InterruptedException e) {
            error(ERROR_WAITING);
        }
    }

    public <T extends WebElement> boolean isClickable(T webElement) {
        return webElement.isDisplayed() && webElement.isEnabled();
    }

    public void waitElement(Element element) {
        WebDriverWait waitDriver = new WebDriverWait(getDriver(), 30);
        waitDriver.until(
                ExpectedConditions.elementToBeClickable(element.getWebElement()));
    }

    private void error(String message) {
        LOG.error(message);
    }

    private void debug(String message) {
        LOG.debug(message);
    }

    /**
        Method waiting for 2 conditions to indicate that page is ready for further interactions
        Condition 1 - JS verification than document has ready state
        Condition 2 - Page loading spinner is not displayed
        Notes: best value for sleep after checking elements state is 500, decreasing this value could prevent to instability of tests execution
     */
    public void waitForPageToBeReady() {
        debug("Start of page waiter");
        for (int i = 0; i < COUNT_RETRY_INCYCLE; i++) {
            debug("\n waiting for all conditions. Count:" + i);
            for (int j = 0; j < COUNT_RETRY_INCYCLE; j++) {
                debug("\n waiting till pageIsReady state true. Count:" + j);
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                if (js.executeScript("return document.readyState").toString().equals("complete")) {
                    break;
                }
                try {
                    Thread.sleep(RETRY_CYCLE_SLEEP);
                } catch (Exception e) {
                    error(e.getLocalizedMessage());
                }
            }
        }
        debug("End of page waiter");
    }

    public void fluentElementWait(WebElement webElement) {
        debug("Start of page waiter");
        boolean pageIsReady = false;
        boolean pageIsLoading = true;
        for (int i = 0; i < COUNT_RETRY_INCYCLE; i++) {
            debug("\n waiting for all conditions. Count:" + i);
            for (int j = 0; j < COUNT_RETRY_INCYCLE; j++) {
                debug("\n waiting till pageIsReady state true. Count:" + j);
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                if (js.executeScript("return document.readyState").toString().equals("complete")) {
                    pageIsReady = true;
                    break;
                }
                try {
                    Thread.sleep(RETRY_CYCLE_SLEEP);
                } catch (Exception e) {
                    error(e.getLocalizedMessage());
                }
            }
            if (pageIsReady) {
                for (int k = 0; k < COUNT_RETRY_INCYCLE; k++) {
                    debug("\n waiting till pageIsLoading state false. Count:" + k);
                    Wait<WebDriver> wait = new FluentWait<>(getDriver())
                            .withTimeout(DEFAULT_TIME_OUT, DEFAULT_TIME_UNIT)
                            .pollingEvery(DEFAULT_POLLING, DEFAULT_TIME_UNIT)
                            .ignoring(NoSuchElementException.class);
                    WebElement element = wait.until(driver -> webElement);

                    if (element != null) {
                        try {
                            String statusValue = element.getAttribute("style");
                            if (statusValue.equalsIgnoreCase("display: none;"))
                                pageIsLoading = false;
                            break;
                        } catch (org.openqa.selenium.StaleElementReferenceException e) {
                            debug("spinner element was refreshed, page was overloaded.");
                            pageIsReady = false;
                            continue;
                        }
                    }
                    try {
                        Thread.sleep(RETRY_CYCLE_SLEEP);
                    } catch (Exception e) {
                        error(e.getLocalizedMessage());
                    }
                }
            }
            if (!pageIsLoading & pageIsReady)
                break;
        }
        if (pageIsLoading || !pageIsReady) {
            error("Page wasn't loaded successfully");
        }
        debug("End of page waiter");
    }
}