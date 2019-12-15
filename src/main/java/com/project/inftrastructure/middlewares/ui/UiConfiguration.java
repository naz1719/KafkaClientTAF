package com.project.inftrastructure.middlewares.ui;

import com.project.inftrastructure.middlewares.ui.utils.DriverManager;
import org.openqa.selenium.WebDriver;

/**
 * Class responsible for managing UI config data
 */
public class UiConfiguration {

    private WebDriver driver;
    private String browserName;

    private UiConfiguration() {
    }

    public static UiConfiguration getInstance() {
        return UiConfigurationInstance.INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver(DriverManager.DriverType.valueOf(getBrowserName()));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private final static class UiConfigurationInstance {
        private final static UiConfiguration INSTANCE = new UiConfiguration();
    }
}
