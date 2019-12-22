package api;

import com.project.services.api.quotations.GetRandomQuotationService;
import com.project.services.api.quotations.dto.Quote;
import org.testng.annotations.Test;

public class RandomQuotationTest {

    @Test(description = "Test on getting random quote")
    public void randomQuotationTest(){
        Quote randomQuote = GetRandomQuotationService.getInstance().getRandomQuote();
    }
}
