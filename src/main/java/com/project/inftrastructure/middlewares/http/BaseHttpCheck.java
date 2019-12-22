package com.project.inftrastructure.middlewares.http;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class BaseHttpCheck {

    protected static String content_type_json = "application/json";
    protected static String content_type_multipart = "multipart/form-data";

    protected void validateResponseBasic(Response response) {
        Assertions
                .assertThat(response).as("Response")
                .isNotNull();
    }

    @Step(value = "Validate that http status code = {expectedStatusCode}")
    protected void validateResponseStatusCode(Response response, int expectedStatusCode) {
        Assertions
                .assertThat(response.statusCode()).as("Status code")
                .isEqualTo(expectedStatusCode);
    }
}
