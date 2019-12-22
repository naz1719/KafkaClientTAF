package com.project.inftrastructure.execution.wait;

import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.utils.CustomExpectedConditions;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class ElementWaitManager {
    private static final TestLogger LOG = TestLogger.getLogger();
    public final WebElement webElement;
    private Wait<WebDriver> wait;

    public ElementWaitManager(WebElement webElement) {
        this.webElement = webElement;
    }

    /**
     * Selects either the first frame on the page, or the main document when a page contains iframes.
     */
    public static void switchDefaultContent() {
        UiConfiguration.getInstance().getDriver().switchTo().defaultContent();
    }

    /**
     * Wait until {@link WebElement} will be displayed
     *
     * @return boolean of {@link WebElement} displaying state
     */
    public boolean waitForControl(int sec) {
        wait = getWait(sec);
        return wait.until(webDriver -> webElement != null && webElement.isDisplayed());
    }

    public boolean waitForAttribute(String attribute, String value, int sec) {
        wait = getWait(sec);
        return wait.until(webDriver -> webElement != null && webElement.getAttribute(attribute).equals(value));
    }

    /**
     * Get default wait. Using for other internal functions.
     *
     * @param timeout Time in seconds
     * @return {@link FluentWait} with default configuration
     */
    public FluentWait<WebDriver> getWait(int timeout) {
        return new FluentWait<>(UiConfiguration.getInstance().getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void switchFrame() {
        UiConfiguration.getInstance().getDriver().switchTo().frame(webElement);
    }

    /**
     * Find all {@link WebElement}s within the current context using the given mechanism and default implicit wait
     *
     * @param by The location mechanism to use
     * @return A list of all {@link WebElement}s, or an empty list if nothing matches.
     */
    public List<WebElement> findElementsWithTimeout(By by) {
        return findElementsWithTimeout(webElement, by, 1);
    }

    public List<WebElement> findElementsWithTimeout(By by, int sec) {
        return findElementsWithTimeout(webElement, by, sec);
    }

    public static List<? extends Control> findElementsWithTimeout(List<? extends Control> webElementList , int sec) {
        OptionalInt first = IntStream.range(0, sec)
                .filter(i -> {
                    boolean isEmpty = webElementList.size() == 0;
                    DelaySteps.getInstance().delayStep(1);
                    return isEmpty;
                })
                .findFirst();

        if(!first.isPresent()){
            Assertions.fail("Elements not found");
        }
        return webElementList;
    }

    void waitForListVisibility(List<? extends Control> list, int seconds) {
        getWait(seconds).until(CustomExpectedConditions.visibilityOfAllElements(list));
    }

    public Control isElementIsClickable(int sec) {
        getWait(sec).until(ExpectedConditions.elementToBeClickable(webElement));
        return (Control) this;
    }

    public Control isElementIsVisible(int sec) {
        getWait(sec).until(ExpectedConditions.visibilityOf(webElement));
        return (Control) this;
    }

    public Control waitForStaleness(int sec) {
        getWait(sec).until(ExpectedConditions.stalenessOf(webElement));
        return (Control) this;
    }

    public Control clickJS() {
        WebDriver webDriver = UiConfiguration.getInstance().getDriver();
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webElement);
        return (Control) this;
    }

    /**
     * Wait until document ready state will be completed
     *
     * @return boolean of document ready state
     */
    public boolean waitJsToLoad() {
        wait = getWait(300);
        return wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void angularLoads() {
        try {
            WebDriver webDriver = UiConfiguration.getInstance().getDriver();
            boolean angularReady = Boolean.parseBoolean(((JavascriptExecutor) webDriver).executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1").toString());
            ExpectedCondition<Boolean> angularLoad = driver -> angularReady;

            getWait(20).until(angularLoad);
        } catch (WebDriverException ignored) {
            LOG.error("Web driver exception. Can't wait for angular load");
        }
    }

    /**
     * Find all {@link WebElement}s within the current context using the given mechanism  and implicit wait
     *
     * @param webElement Context to use
     * @param by         The locating mechanism to use
     * @param sec        Implicit wait in seconds
     * @return A list of all {@link WebElement}s, or an empty list if nothing matches.
     */
    private List<WebElement> findElementsWithTimeout(WebElement webElement, By by, int sec) {
        List<WebElement> webElements;
        WebDriver driver = UiConfiguration.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
        if (webElement != null) {
            webElements = webElement.findElements(by);
        } else {
            webElements = driver.findElements(by);
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return webElements;
    }
}
