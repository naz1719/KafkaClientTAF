package com.project.inftrastructure.execution.wait;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelaySteps {

    private static final DelaySteps instance = new DelaySteps();
    final Logger LOG = LoggerFactory.getLogger(DelaySteps.class);

    private DelaySteps() {
    }

    public static DelaySteps getInstance() {
        return instance;
    }

    @Step(value = "DelayStep: {seconds} seconds")
    public void delayStep(int seconds) {
        LOG.info("[Start] DelayStep:" + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step(value = "DelayStep: {delayDescription} : {seconds} seconds")
    public void delayStep(int seconds, String delayDescription) {
        LOG.info("[Start] DelayStep:" + delayDescription + ": " + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}