package com.project.inftrastructure.middlewares.ui.driver;

import com.project.inftrastructure.utils.property.ApplicationPropNames;
import com.project.inftrastructure.utils.property.ApplicationPropertiesInitializer;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class WebDriverFactory {

    public WebDriver getDriverInstance() {
        String browser = ApplicationPropertiesInitializer.getProperty(ApplicationPropNames.BROWSER.getValue());
        Driver driverType = Driver.getDriverType(browser);
        String hubURLSystemProperty = ApplicationPropertiesInitializer.getProperty(ApplicationPropNames.HUB_URL.getValue());

        if (hubURLSystemProperty != null && !hubURLSystemProperty.isEmpty()) {
            driverType = Driver.REMOTE_WEB_DRIVER;
        }

        return getDriverInstance(driverType);
    }

    public WebDriver getDriverInstance(Driver driver) {
        long implicitTimeOut = Long.parseLong(ApplicationPropertiesInitializer.getProperty(
                    ApplicationPropNames.IMPLICITLY_WAIT_TIMEOUT.getValue()));

        switch (driver) {

            case CHROME:
                String chromeDriverPath = ApplicationPropertiesInitializer.getProperty(
                        ApplicationPropNames.CHROME_DRIVER_PATH.getValue());
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                WebDriver chrome = new ChromeDriver(getChromeCapabilities());
                chrome.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
                return chrome;

            case IE:
                String internetExplorerDriverPath = ApplicationPropertiesInitializer.getProperty(
                        ApplicationPropNames.INTERNET_EXPLORER32_PATH.getValue());
                System.setProperty("webdriver.ie.driver", internetExplorerDriverPath);
                WebDriver ieDriver = new InternetExplorerDriver(getInternetExplorerCapabilities());
                ieDriver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
                ieDriver.manage().window().maximize();
                return ieDriver;

            case FIREFOX:
                String firefoxDriverPath = ApplicationPropertiesInitializer.getProperty(
                        ApplicationPropNames.FIREFOX_DRIVER_PATH.getValue());
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                WebDriver firefoxDriver = new FirefoxDriver(getFirefoxCapabilities());
                firefoxDriver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
                return firefoxDriver;
            case REMOTE_WEB_DRIVER:
                String hubURL = ApplicationPropertiesInitializer.getProperty(
                        ApplicationPropNames.HUB_URL.getValue());
                WebDriver remoteDriver;
                URL url;
                try {
                    url = new URL(hubURL);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                remoteDriver = new RemoteWebDriver(url, getChromeCapabilities());
                remoteDriver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
                remoteDriver.manage().window().maximize();
                return remoteDriver;

        }
        throw new RuntimeException("Unsupported driver type " + driver);
    }

    private DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.ACCEPT);
        chromeCapabilities.setCapability("browserConnectionEnabled", true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return chromeCapabilities;
    }

    private DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        return firefoxCapabilities;
    }

    private DesiredCapabilities getInternetExplorerCapabilities() {
        DesiredCapabilities internetExplorerCapabilities = DesiredCapabilities.internetExplorer();
        internetExplorerCapabilities.setCapability("webdriver.ie.version", "11");
        internetExplorerCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        internetExplorerCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        internetExplorerCapabilities.setCapability("ignoreProtectedModeSettings", true);
        return internetExplorerCapabilities;
    }
}