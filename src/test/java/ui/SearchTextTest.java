package ui;

import com.project.services.ui.steps.SearchResultSteps;
import com.project.services.ui.steps.SearchSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI {

    @Test(description = "Test google search")
    public void googleSearchTest() {
        SearchSteps searchSteps = new SearchSteps();
        searchSteps
                .get()
                .typeSearchText("SearchText")
                .submitSearchText();

        SearchResultSteps searchResultSteps = new SearchResultSteps();
        int webResultList = searchResultSteps.getWebResults().size();
        Assert.assertNotEquals(webResultList, 0);
    }
}
