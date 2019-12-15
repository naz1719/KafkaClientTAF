package ui;

import com.project.services.ui.steps.SearchSteps;
import com.project.services.ui.steps.SearchResultSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI {

    @Test
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
