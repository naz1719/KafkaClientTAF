package com.project.inftrastructure.middlewares.http;

import com.jayway.restassured.response.Response;
import io.qameta.allure.Step;
import org.testng.Assert;

public class BaseHttpCheck {

    protected static String content_type_json = "application/json";
    protected static String content_type_multipart = "multipart/form-data";

    protected void validateResponseBasic(Response response) {
        if (response == null) {
            Assert.fail("FAIL: response is null");
        }
    }

    @Step(value = "Validate that http status code = {expectedStatusCode}")
    protected void validateResponseStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            Assert.fail("FAIL: response StatusCode: " + response.statusCode() + " but expected: " + expectedStatusCode);
        }
    }
}
