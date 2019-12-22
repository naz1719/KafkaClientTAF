package ui;

import com.project.services.ui.steps.SearchResultSteps;
import com.project.services.ui.steps.SearchSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI {

    @Test(description = "Test google search")
    public void googleSearchTest() {
        SearchSteps searchSteps = new SearchSteps().get();
        searchSteps
                .typeSearchText("SearchText")
                .submitSearchText();

        SearchResultSteps searchResultSteps = new SearchResultSteps().get();
        int webResultList = searchResultSteps.getWebResults().size();
        Assert.assertNotEquals(webResultList, 0);
    }
}
