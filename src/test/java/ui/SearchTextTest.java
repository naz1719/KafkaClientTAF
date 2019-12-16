package ui;

import com.project.services.ui.steps.SearchResultSteps;
import com.project.services.ui.steps.SearchSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI {

    @Test(description = "Test google search")
    public void googleSearchTest() {
        SearchSteps searchSteps = SearchSteps.getInstance();
        searchSteps.openPortal("https://www.google.com.ua/");
        searchSteps
                .typeSearchText("SearchText")
                .submitSearchText();

        SearchResultSteps searchResultSteps = new SearchResultSteps();
        Assert.assertNotEquals(searchResultSteps.getWebResults().size(), 0);
    }
}
