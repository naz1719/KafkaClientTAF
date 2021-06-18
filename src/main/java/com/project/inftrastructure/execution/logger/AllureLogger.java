package com.project.inftrastructure.execution.logger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rishchenko on 21.08.2017.
 */
public class AllureLogger {
    final static Logger LOG = LoggerFactory.getLogger(AllureLogger.class);

    public static boolean logToslf4j;
    public static boolean logSoapCallToAllure;


    // 1. ----------- Common  --------
    private static byte[] attach(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }

    // 3. ----------- Attache Json --------
    @Attachment(value = "{name}", type = "application/json")
    public static byte[] attachJson(String name, ByteArrayOutputStream stream) {
        logToConsole(stream);
        return attach(stream);
    }

    // 4. ----------- Log test information --------
    @Attachment(value = "Log", type = "text/plain")
    public static byte[] attachLog(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step(value = "{log}")
    public static void logInfo(String log) {
    }

    private static void logToConsole(ByteArrayOutputStream stream) {
        if (logToslf4j) {
            LOG.info("\n-------------\n" + stream.toString() + "\n");
        }
    }
}
