package com.project.services.ui;


import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.middlewares.ui.annotations.Page;
import com.project.inftrastructure.middlewares.ui.utils.pagefactory.ElementFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Basic Page Object. Every PO object MUST extend BasePage
 */

@Page(title = "Base Page Object")
public abstract class BasePage <P extends BasePage<P>> extends LoadableComponent {
    protected TestLogger LOG = TestLogger.getLogger();
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        ElementFactory.initElements(driver, this);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void step(String message) {
        LOG.info(message);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
