package com.project.inftrastructure.middlewares.ui;

import com.project.inftrastructure.middlewares.ui.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Class responsible for managing Web Driver
 */
public class WebDriverManager {
    private final Logger LOG = LoggerFactory.getLogger(WebDriverManager.class);
    private static final ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private String browserName;

    private WebDriverManager() {
    }

    public static WebDriverManager getInstance() {
        return UiConfigurationInstance.INSTANCE;
    }

    private final static class UiConfigurationInstance {
        private final static WebDriverManager INSTANCE = new WebDriverManager();
    }

    public WebDriver getDriver() {
        if (pool.get() == null) {
            String browserName = getBrowserName();
            LOG.info("Starting new " + browserName + " browser instance");
            WebDriver driver = DriverManager.getDriver(DriverManager.DriverType.valueOf(browserName));
            pool.set(driver);
            pool.get().manage().window().maximize();
        }
        return pool.get();
    }

    private String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void tearDownDriver() {
        Optional<WebDriver> webDriver = Optional.ofNullable(pool.get());
        if (webDriver.isPresent()) {
            WebDriver driver = webDriver.get();
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
                pool.remove();
            }

            LOG.info("Browser has been stopped.");
        } else {
            LOG.info("All browsers have been stopped.");
        }
    }
}
