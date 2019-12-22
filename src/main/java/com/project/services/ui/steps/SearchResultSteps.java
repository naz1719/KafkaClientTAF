package com.project.services.ui.steps;

import com.project.inftrastructure.middlewares.ui.controls.elements.Button;
import com.project.services.ui.BaseSteps;
import com.project.services.ui.po.SearchResultPage;

import java.util.List;

public class SearchResultSteps extends BaseSteps<SearchResultPage> {

    public SearchResultSteps() {
        page = new SearchResultPage(webDriver);
    }

    public List<Button> getWebResults() {
        return page.getWebResults();
    }

    public SearchResultSteps get(){
        page.get();
        return this;
    }
}
