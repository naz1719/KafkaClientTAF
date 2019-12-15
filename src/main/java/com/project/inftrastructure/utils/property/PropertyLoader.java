package com.project.inftrastructure.utils.property;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertyLoader {

    public static Properties loadProperties(String filename) {
        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            String error = "Can't load properties file [" + filename + "]";
            throw new UncheckedIOException(e);
        }
    }

    public static String getProperty(String key, Properties properties) {
        String propertyName = System.getenv(key);
        if (StringUtils.isBlank(propertyName)) {
            String propertyValue = properties.getProperty(key);
            if (propertyValue == null) {
                String error = "Can't get property value [" + propertyName + "] Check if this property exists in file.";
                throw new RuntimeException(error);
            }
            return propertyValue;
        }
        return propertyName;
    }
}
