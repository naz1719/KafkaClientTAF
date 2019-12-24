package com.project.inftrastructure.utils.property;

public enum ApplicationPropNames {
    IMPLICITLY_WAIT("project.implicitWait");
    private String prop;

    ApplicationPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }
}
