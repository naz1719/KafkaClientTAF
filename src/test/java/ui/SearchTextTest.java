package ui;

import com.project.services.ui.steps.SearchSteps;
import com.project.services.ui.steps.SearchResultSteps;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class SearchTextTest extends BaseUI {

    @Test
    @Description("Test google search")
    public void googleSearchTest() {
        SearchSteps searchSteps = new SearchSteps();
        searchSteps.openPortal("https://www.google.com.ua/");
        searchSteps
                .typeSearchText("SearchText")
                .submitSearchText();

        SearchResultSteps searchResultSteps = new SearchResultSteps();
        Assert.assertEquals(searchResultSteps.getWebResults().size(), 0);
    }
}
