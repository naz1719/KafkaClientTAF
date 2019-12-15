package com.project.services.ui.bo;

import com.project.inftrastructure.middlewares.ui.UiConfiguration;
import com.project.inftrastructure.middlewares.ui.controls.elements.Button;
import com.project.services.ui.BaseBO;
import com.project.services.ui.po.SearchResultPage;

import java.util.List;

public class SearchResultBO extends BaseBO {
    private SearchResultPage searchResultPage;

    public SearchResultBO() {
        searchResultPage = new SearchResultPage(UiConfiguration.getInstance().getDriver());
    }

    public List<Button> getWebResults() {
        return searchResultPage.getWebResults();
    }
}
