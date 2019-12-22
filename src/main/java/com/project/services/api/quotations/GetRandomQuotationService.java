package com.project.services.api.quotations;

import com.project.inftrastructure.execution.logger.AllureLogger;
import com.project.inftrastructure.execution.logger.LogHolder;
import com.project.inftrastructure.middlewares.http.BaseHttpCheck;
import com.project.services.api.Endpoints;
import com.project.services.api.quotations.dto.Quote;
import io.qameta.allure.Step;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetRandomQuotationService extends BaseHttpCheck {

    private static GetRandomQuotationService instance;

    private GetRandomQuotationService() {
    }

    public static GetRandomQuotationService getInstance() {
        if (instance == null) {
            instance = new GetRandomQuotationService();
        }
        return instance;
    }

    @Step(value = "REST: Get random quote")
    public Quote getRandomQuote() {
        LogHolder logHolder = new LogHolder();
        Response response;
        Quote randomQuoteResponse;

        try {
            String uri = Endpoints.randomQuotation.getEndPoint();
            RequestSpecification request = given()
                    .relaxedHTTPSValidation()
                    .filters(new ResponseLoggingFilter(LogDetail.ALL,
                                    logHolder.getResponseVar()),
                            new RequestLoggingFilter(LogDetail.ALL, logHolder.getRequestVar()))
                    .contentType(content_type_json);
            response = request.when().get(uri);

            AllureLogger.attachJsone("Request :", logHolder.getRequestStream());
            AllureLogger.attachJsone("Response :", logHolder.getResponseStream());

            validateResponseBasic(response);
            validateResponseStatusCode(response, 200);

            randomQuoteResponse = response.then().extract().as(Quote.class, ObjectMapperType.JACKSON_2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return randomQuoteResponse;
    }
}
