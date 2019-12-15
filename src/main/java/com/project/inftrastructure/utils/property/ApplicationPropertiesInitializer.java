package com.project.inftrastructure.utils.property;

import java.util.Properties;

public class ApplicationPropertiesInitializer {
    private static final String APPLICATION_PROPERTIES_FILE = "configuration.properties";
    private static ApplicationPropertiesInitializer instance;

    private static Properties PROPERTIES;

    private ApplicationPropertiesInitializer() {
    }

    static {
            instance = new ApplicationPropertiesInitializer();
            PROPERTIES = PropertyLoader.loadProperties(ApplicationPropertiesInitializer.APPLICATION_PROPERTIES_FILE);
    }

    public static String getProperty(String key) {
        return PropertyLoader.getProperty(key, PROPERTIES);
    }
}
