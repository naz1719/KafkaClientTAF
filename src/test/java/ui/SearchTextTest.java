package ui;

import com.project.services.ui.bo.SearchBO;
import com.project.services.ui.bo.SearchResultBO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI {

    @Test
    public void googleSearchTest() {
        SearchBO searchBO = new SearchBO();
        searchBO.openPortal("https://www.google.com.ua/");
        searchBO
                .typeSearchText("SearchText")
                .submitSearchText();

        SearchResultBO searchResultBO = new SearchResultBO();
        Assert.assertEquals(searchResultBO.getWebResults().size(), 0);
    }
}
