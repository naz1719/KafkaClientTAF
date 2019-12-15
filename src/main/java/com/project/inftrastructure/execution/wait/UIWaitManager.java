package com.project.inftrastructure.execution.wait;

import com.beust.jcommander.internal.Nullable;
import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.elements.Table;
import com.project.inftrastructure.middlewares.ui.utils.CustomExpectedConditions;
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
import java.util.concurrent.TimeUnit;


public class UIWaitManager {
    private static final TestLogger LOG = TestLogger.getLogger();
    private static final UIWaitManager instance = new UIWaitManager();
    protected Wait<WebDriver> wait;

    private UIWaitManager() {
    }

    public static UIWaitManager getInstance() {
        return instance;
    }

    /**
     * Wait until {@link WebElement} will be displayed
     *
     * @param webControl {@link WebElement} that you want to wait
     * @return boolean of {@link WebElement} displaying state
     */

    protected boolean waitForControl(WebElement webControl, int sec) {
        wait = getWait(sec);
        return wait.until(webDriver -> webControl != null && webControl.isDisplayed());
    }

    protected boolean waitForAttribute(WebElement webControl, String attribute, String value, int sec) {
        wait = getWait(sec);
        return wait.until(webDriver -> webControl != null && webControl.getAttribute(attribute).equals(value));
    }

    /**
     * Wait until document ready state will be completed
     *
     * @return boolean of document ready state
     */
    protected boolean waitJsToLoad() {
        wait = getWait(300);
        return wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }

    /**
     * Get default wait. Using for other internal functions.
     *
     * @param timeout Time in seconds
     * @return {@link FluentWait} with default configuration
     */
    protected FluentWait<WebDriver> getWait(int timeout) {
        return new FluentWait<>(UiConfiguration.getInstance().getDriver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }


    public void switchFrame(WebElement frameLocator) {
        UiConfiguration.getInstance().getDriver().switchTo().frame(frameLocator);
    }

    /**
     * Selects either the first frame on the page, or the main document when a page contains iframes.
     */
    public void switchDefaultContent() {
        UiConfiguration.getInstance().getDriver().switchTo().defaultContent();
    }


    /**
     * Find all {@link WebElement}s within the current context using the given mechanism and default implicit wait
     *
     * @param webElement Context to use
     * @param by         The location mechanism to use
     * @return A list of all {@link WebElement}s, or an empty list if nothing matches.
     */
    public List<WebElement> findElementsWithTimeout(@Nullable WebElement webElement, By by) {
        return findElementsWithTimeout(webElement, by, 1);
    }

    /**
     * Find all {@link WebElement}s within the current context using the given mechanism  and implicit wait
     *
     * @param webElement Context to use
     * @param by         The locating mechanism to use
     * @param sec        Implicit wait in seconds
     * @return A list of all {@link WebElement}s, or an empty list if nothing matches.
     */
    public List<WebElement> findElementsWithTimeout(WebElement webElement, By by, int sec) {
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

    public Boolean isElementDisplayed(By by) {
        List<WebElement> listOfElements = UiConfiguration.getInstance().getDriver().findElements(by);
        if (!listOfElements.isEmpty()) {
            return listOfElements.get(0).isDisplayed();
        } else {
            return false;
        }
    }

    public String getPageTitle() {
        return UiConfiguration.getInstance().getDriver().getTitle();
    }

    void waitForListVisibility(List<? extends Control> list, int seconds) {
        getWait(seconds).until(CustomExpectedConditions.visibilityOfAllElements(list));
    }

    public void isElementIsClickable(WebElement webControl, int sec) {
        getWait(sec).until(ExpectedConditions.elementToBeClickable(webControl));
    }

    protected void isElementIsVisible(WebElement webControl, int sec) {
        getWait(sec).until(ExpectedConditions.visibilityOf(webControl));
    }

    protected void waitForStaleness(WebElement webControl, int sec) {
        getWait(sec).until(ExpectedConditions.stalenessOf(webControl));
    }


    public void sortTable(Table table, String columnName, String condition, WebElement el) {
        List<WebElement> columnsList = table.getHeaderElements();
        wait = getWait(300);
        columnsList.stream().filter(it -> it.getText().trim().equals(columnName))
                .forEach(it -> wait.until(CustomExpectedConditions.conditionIsSelected(it, condition, table, columnName, el)));
    }

    public void refresh() {
        WebDriver driver = UiConfiguration.getInstance().getDriver();
        driver.get(driver.getCurrentUrl());
        sleep(10000);
    }

    public void sleep(long sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            LOG.error("Can't stop thread");
        }
    }

    public void clickJS(WebElement element) {
        WebDriver webDriver = UiConfiguration.getInstance().getDriver();
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
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
}
