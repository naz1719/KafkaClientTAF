package com.project.services.ui.steps;


import com.project.services.ui.BaseSteps;
import com.project.services.ui.po.SearchPage;

public class SearchSteps extends BaseSteps<SearchPage> {

    public SearchSteps() {
        page = new SearchPage(webDriver);
    }

    public SearchSteps typeSearchText(String searchText) {
        page.typeSearchText(searchText);
        return this;
    }

    public SearchSteps submitSearchText() {
        page.submit();
        return this;
    }

    public SearchSteps get(){
        page.get();
        return this;
    }
}
