package com.project.services.ui.steps;


import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import com.project.services.ui.BaseSteps;
import com.project.services.ui.po.SearchPage;
import io.qameta.allure.Step;

public class SearchSteps extends BaseSteps {
    private SearchPage searchPageObject;

    public SearchSteps() {
        searchPageObject = new SearchPage(UiConfiguration.getInstance().getDriver());
    }


    @Step("Type search text")
    public SearchSteps typeSearchText(String searchText) {
        searchPageObject.typeSearchText(searchText);
        return this;
    }

    public SearchSteps submitSearchText() {
        searchPageObject.submit();
        return this;
    }

}
