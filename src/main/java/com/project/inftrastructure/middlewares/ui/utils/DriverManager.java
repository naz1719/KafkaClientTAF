package com.project.inftrastructure.middlewares.ui.utils;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumMap;
import java.util.function.Supplier;

public class DriverManager {

    private DriverManager(){}

    private static final EnumMap<DriverType, Supplier<WebDriver>> driverMap = new EnumMap<>(DriverType.class);

    /**
     * return instance of Chrome driver
     */
   private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
      WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
      return new ChromeDriver(setChromeOption());
   };

   /**
     * return instance of Remote driver
     */
   private static final Supplier<WebDriver> remoteDriverSupplier = () -> {
       URL gridUrl;
       try {
           gridUrl = new URL("http://hub:4444/wd/hub");
       } catch (MalformedURLException e) {
           throw new RuntimeException(e);
       }
       return new RemoteWebDriver(gridUrl,setChromeOption());
   };

    /**
     * return instance of Chrome driver in headless mode
     */
    private static final Supplier<WebDriver> chromeHeadlessDriverSupplier = () -> {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        return new ChromeDriver(setChromeOption().setHeadless(true));
    };

    /**
     * return instance of Edge driver
     */
    private static final Supplier<WebDriver> edgeDriverSupplier = () -> {
        WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
        return new EdgeDriver();
    };

    /**
     * return instance of FF driver
     */
    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        return new FirefoxDriver();
    };

    /**
     * add all driver to map
     */
    static{
        driverMap.put(DriverType.REMOTE, remoteDriverSupplier);
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
        driverMap.put(DriverType.HEADLESS_CHROME, chromeHeadlessDriverSupplier);
        driverMap.put(DriverType.FIREFOX, firefoxDriverSupplier);
        driverMap.put(DriverType.EDGE, edgeDriverSupplier);
    }

    public enum DriverType{
        HEADLESS_CHROME,
        CHROME,
        EDGE,
        FIREFOX,
        REMOTE
    }

    /**
     *
     * @param browser type of browser  {@link DriverType}
     * @return instance of driver
     */
    public static WebDriver getDriver(DriverType browser){
        return driverMap.get(browser).get();
    }

    /**
     * @return set of Chrome options
     */
    private static ChromeOptions setChromeOption(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        return options;
    }
}
