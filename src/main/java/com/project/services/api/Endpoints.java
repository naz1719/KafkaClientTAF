package com.project.services.api;

public enum  Endpoints {
    randomQuotation("randomQuotation",
            "https://gturnquist-quoters.cfapps.io/api/random");

    private String prop;
    private String endPoint;


    Endpoints(String prop, String endPoint) {
        this.prop = prop;
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getValue() {
        return prop;
    }
}
