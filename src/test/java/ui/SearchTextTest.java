package ui;

import com.project.inftrastructure.dataprovider.GoogleSearchDataProvider;
import com.project.services.ui.steps.SearchResultSteps;
import com.project.services.ui.steps.SearchSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI {

    @Test(description = "Test google search", dataProvider = "itemsToSearch",dataProviderClass = GoogleSearchDataProvider.class)
    public void googleSearchTest(String itemToSearch) {
        SearchSteps searchSteps = new SearchSteps().get();
        searchSteps
                .typeSearchText(itemToSearch)
                .submitSearchText();

        SearchResultSteps searchResultSteps = new SearchResultSteps().get();
        int webResultList = searchResultSteps.getWebResults().size();
        Assertions.assertThat(webResultList).isGreaterThan(100);
    }
}
