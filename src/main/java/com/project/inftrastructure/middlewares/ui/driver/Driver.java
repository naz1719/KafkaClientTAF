package com.project.inftrastructure.middlewares.ui.driver;

public enum Driver {
    CHROME("chrome"),
    IE("internet explorer"),
    FIREFOX("firefox"),
    REMOTE_WEB_DRIVER("remote web driver"),
    HTML_UNIT_DRIVER("html unit driver"),
    GHOST_DRIVER("ghost driver"),
    NULL("null driver");

    private String driverValue;

    Driver(String driverValue) {
        this.driverValue = driverValue;
    }

    public static Driver getDriverType(String driverValue) {
        for (Driver drivers : Driver.values()) {
            if (drivers.getDriverValue().equalsIgnoreCase(driverValue)) {
                return drivers;
            }
        }
        return NULL;
    }

    public String getDriverValue() {
        return driverValue;
    }
}
