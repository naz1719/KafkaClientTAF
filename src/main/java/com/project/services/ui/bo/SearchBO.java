package com.project.services.ui.bo;


import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import com.project.services.ui.BaseBO;
import com.project.services.ui.po.SearchPage;

public class SearchBO extends BaseBO {
    private SearchPage searchPageObject;

    public SearchBO() {
        searchPageObject = new SearchPage(UiConfiguration.getInstance().getDriver());
    }

    public SearchBO typeSearchText(String searchText) {
        step("Search '" + searchText + "' in Google");
        searchPageObject.typeSearchText(searchText);
        return this;
    }

    public SearchBO submitSearchText() {
        step("Submit search text");
        searchPageObject.submit();
        return this;
    }

}
