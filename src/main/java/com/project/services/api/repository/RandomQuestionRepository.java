package com.project.services.api.repository;

import com.project.services.api.quotations.dto.Quote;

public class RandomQuestionRepository {

    public static Quote expectedQuote(){
        return new Quote()
            .setType("success");
    }
}
