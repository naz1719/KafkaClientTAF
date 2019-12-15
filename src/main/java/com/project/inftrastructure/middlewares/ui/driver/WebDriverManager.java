package com.project.inftrastructure.middlewares.ui.driver;


import com.sample.core.testUtils.TestLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class WebDriverManager {

    private static final TestLogger LOG = TestLogger.getLogger();
    private static final ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static final WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        return pool.get() != null ? pool.get() : createAndGetDriver();
    }

    private static WebDriver createAndGetDriver() {
        LOG.info("Starting new browser instance");
        WebDriver driver = webDriverFactory.getDriverInstance();
        pool.set(driver);
        return driver;
    }

    public static void stop() {
        WebDriver driver = getDriver();
        LOG.info("Stopping browser.");
        try {
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                driver.close();
            }
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            removeDriverFromDriverPool();
        }

        LOG.info("Browser has been stopped.");
    }

    public static void load(String path) {
        if (!path.endsWith("/"))
            path += "/";
        getDriver().get(path);
        LOG.info(String.format("Browser navigates to URL = %s", path));
    }

    public static String getTitle() {
        return getDriver().getTitle();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static void alertAccept() {
        getDriver().switchTo().alert().accept();
    }

    public static void alertDismiss() {
        getDriver().switchTo().alert().dismiss();
    }

    public static void alertSendKeys(String keys) {
        getDriver().switchTo().alert().sendKeys(keys);
    }

    public static void doubleClick(WebElement element) {
        new Actions(getDriver()).doubleClick(element).build().perform();
    }

    private static void removeDriverFromDriverPool() {
        pool.remove();
    }

    public static Actions actions() {
        return new Actions(getDriver());
    }

    public static void maximize() {
        getDriver().manage().window().maximize();
    }

    public static void getUrl(String url) {
        Reporter.log("Opening page with url - " + url + "</br>");
        getDriver().get(url);
    }

    public static Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, args);
    }

    public static void highlightElement(WebElement elem) {
        executeScript("arguments[0].style.border='1px solid red'", elem);
    }

    public static void scrollToElement(WebElement el) {
        executeScript("arguments[0].scrollIntoView(false);", el);
    }

    public void makeScreen(String fileName) {
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}