package com.project.services.ui;


import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.execution.wait.UIWaitManager;
import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import org.openqa.selenium.WebDriver;

public abstract class BaseSteps {
    protected UIWaitManager waitManager = UIWaitManager.getInstance();
    protected TestLogger LOG = TestLogger.getLogger();
    protected WebDriver webDriver = UiConfiguration.getInstance().getDriver();

    public void error(String message) {
        LOG.error(message);
    }

    public void step(String message) {
        LOG.info(message);
    }
}
