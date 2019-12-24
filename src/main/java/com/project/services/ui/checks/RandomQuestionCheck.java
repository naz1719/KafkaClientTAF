package com.project.services.ui.checks;

import com.project.services.api.quotations.dto.Quote;
import com.project.services.api.quotations.dto.Value;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

public class RandomQuestionCheck {
    private static final RandomQuestionCheck instance = new RandomQuestionCheck();

    private RandomQuestionCheck() {
    }

    public static RandomQuestionCheck getInstance() {
        return instance;
    }

    @Step(value = "Check random question")
    public void validateRandomQuestion(Quote actual, Quote expected) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.getType()).as("check type").isEqualTo(expected.getType());
        Value value = actual.getValue();
        softAssertions.assertThat(value.getId()).as("check Quote.Value id").isBetween(1L,100L);
        softAssertions.assertThat(value.getQuote()).as("check Quote.Value id").isNotBlank();
        softAssertions.assertAll();
    }
}
