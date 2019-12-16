package com.project.services.ui.steps;


import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import com.project.services.ui.BaseSteps;
import com.project.services.ui.po.SearchPage;

public class SearchSteps extends BaseSteps {
    private static final SearchSteps instance = new SearchSteps();
    private SearchSteps() {
        searchPageObject = new SearchPage(UiConfiguration.getInstance().getDriver());
    }
    public static SearchSteps getInstance() {
        return instance;
    }

    private SearchPage searchPageObject;

    public SearchSteps typeSearchText(String searchText) {
        searchPageObject.typeSearchText(searchText);
        return this;
    }

    public SearchSteps submitSearchText() {
        searchPageObject.submit();
        return this;
    }

}
