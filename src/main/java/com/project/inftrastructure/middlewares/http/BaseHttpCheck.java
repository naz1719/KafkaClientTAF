package com.project.inftrastructure.middlewares.http;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class BaseHttpCheck {

    protected static String content_type_json = "application/json";
    protected static String content_type_multipart = "multipart/form-data";

    protected void validateResponseBasic(Response response) {
        if (response == null) {
            Assertions.fail("FAIL: response is null");
        }
    }

    @Step(value = "Validate that http status code = {expectedStatusCode}")
    protected void validateResponseStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            Assertions.fail("FAIL: response StatusCode: " + response.statusCode() + " but expected: " + expectedStatusCode);
        }
    }
}
