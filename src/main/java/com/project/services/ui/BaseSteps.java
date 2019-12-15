package com.project.services.ui;


import com.project.inftrastructure.execution.logger.TestLogger;
import com.project.inftrastructure.execution.wait.UIWaitManager;
import com.project.inftrastructure.middlewares.ui.UiConfiguration;

public abstract class BaseSteps {
    protected UIWaitManager waitManager = UIWaitManager.getInstance();
    protected TestLogger LOG = TestLogger.getLogger();

    public void openPortal(String url) {
        step("Go to " + url);
        UiConfiguration.getInstance().getDriver().get(url);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void step(String message) {
        LOG.info(message);
    }
}