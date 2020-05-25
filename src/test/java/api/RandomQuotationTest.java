package api;

import com.project.services.api.quotations.GetRandomQuotationService;
import com.project.services.api.quotations.dto.Quote;
import com.project.services.api.RandomQuestionCheck;
import com.project.services.api.repository.RandomQuestionRepository;
import org.testng.annotations.Test;

public class RandomQuotationTest {

    @Test(description = "Test on getting random quote")
    public void randomQuotationTest(){
        // arrange
        GetRandomQuotationService randomQuotationService = GetRandomQuotationService.getInstance();
        RandomQuestionCheck randomQuestionCheck = RandomQuestionCheck.getInstance();
        Quote expected = RandomQuestionRepository.expectedQuote();
        // act
        Quote actual = randomQuotationService.getRandomQuote();
        // assert
        randomQuestionCheck.validateRandomQuestion(actual, expected);
    }
}
