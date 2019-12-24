package com.project.services.ui;


import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.middlewares.ui.WebDriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BaseSteps <P extends BasePage<P>> {
    protected TestLogger LOG = TestLogger.getLogger();
    protected WebDriver webDriver;
    protected P page;

    protected BaseSteps() {
        this.webDriver = WebDriverManager.getInstance().getDriver();
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void step(String message) {
        LOG.info(message);
    }
}
