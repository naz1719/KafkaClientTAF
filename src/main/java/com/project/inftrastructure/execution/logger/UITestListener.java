package com.project.inftrastructure.execution.logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class UITestListener implements ITestListener {
    public static final String LOG_OUTPUT_PATH = "%s/output/logs/%s/%s.log";

    private TestLogger LOG;
    private String testName;
    private String className;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        testName = iTestResult.getName().split(" ")[0];
        className = iTestResult.getTestClass().getRealClass().getSimpleName();
        LOG = TestLogger.getLogger(testName, className);
        LOG.info("Start...");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info(" [PASSED]");
        File file = new File(String.format(LOG_OUTPUT_PATH, System.getProperty("user.dir"), className, testName));
        AllureLogger.attachLog(file);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.info("[FAILED]");
        LOG.info(iTestResult.getThrowable().fillInStackTrace());
        AllureLogger.captureScreenshot();
        File file = new File(String.format(LOG_OUTPUT_PATH, System.getProperty("user.dir"), className, testName));
        AllureLogger.attachLog(file);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        testName = iTestResult.getName().split(" ")[0];
        className = iTestResult.getTestClass().getRealClass().getSimpleName();
        LOG = TestLogger.getLogger(testName, className);
        LOG.info("[SKIPPED]");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        AllureLogger.captureScreenshot();
        File file = new File(String.format(LOG_OUTPUT_PATH, System.getProperty("user.dir"), className, testName));
        AllureLogger.attachLog(file);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        if (LOG != null) {
            LOG.drop();
        }
    }
}
