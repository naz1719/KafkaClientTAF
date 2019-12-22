package com.project.inftrastructure.utils.property;

public enum ApplicationPropNames {
    BROWSER("BROWSER"),
    IMPLICITLY_WAIT_TIMEOUT("IMPLICITLY_WAIT_TIMEOUT"),
    HUB_URL("HUB_URL"),

//  Drivers paths
    CHROME_DRIVER_PATH("CHROME_DRIVER_PATH"),
    INTERNET_EXPLORER32_PATH("INTERNET_EXPLORER32_PATH"),
    FIREFOX_DRIVER_PATH("FIREFOX_DRIVER_PATH");

    private String prop;

    ApplicationPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }
}
