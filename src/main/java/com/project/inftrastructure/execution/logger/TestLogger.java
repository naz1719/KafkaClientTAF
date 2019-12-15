package com.project.inftrastructure.execution.logger;

import com.project.inftrastructure.utils.property.ApplicationPropNames;
import com.project.inftrastructure.utils.property.ApplicationPropertiesInitializer;
import io.qameta.allure.Step;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class TestLogger {
    private static final String LOG_LEVEL = ApplicationPropertiesInitializer.getProperty(
            ApplicationPropNames.LOG_LEVEL_TYPE.getValue());
    private static TestLogger logger;
    private Logger LOG;

    private TestLogger(String testName, String className) {
        LOG = Logger.getLogger(testName);
        LOG.setLevel(Level.toLevel(LOG_LEVEL));
        BasicConfigurator.resetConfiguration();

        FileAppender fileAppender = new FileAppender();
        String fileName = String.format(UITestListener.LOG_OUTPUT_PATH, System.getProperty("user.dir"), className, testName);
        fileAppender.setFile(fileName);
        fileAppender.setLayout(new PatternLayout("[%-5p] %d{HH:mm:ss} %c: %m%n"));
        fileAppender.setAppend(false);
        fileAppender.setName("FileAppender");
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.activateOptions();
        LOG.addAppender(fileAppender);

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new PatternLayout("[%-5p] %d{HH:mm:ss} %c: %m%n"));
        consoleAppender.setName("ConsoleAppender");
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.activateOptions();
        LOG.addAppender(consoleAppender);
    }

    public static TestLogger getLogger(String testName, String className) {
        if (logger == null) {
            logger = new TestLogger(testName, className);
        }
        return logger;
    }

    public static TestLogger getLogger() {
        if (logger == null) {
            System.out.println("getLogger(name) is not executed, logger name is not set up");
        }
        return logger;
    }

    @Step("{0}")
    public void info(Object message) {
        LOG.info(message);
    }

    public void debug(Object message) {
        LOG.debug(message);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void drop() {
        if (logger != null)
            logger = null;
    }
}
