package ui;

import com.project.services.ui.bo.SearchBO;
import org.testng.annotations.Test;

public class SearchTextTest extends BaseUI{

    @Test
    public void test(){
        SearchBO searchBO = new SearchBO();
        searchBO.openPortal("https://www.google.com.ua/");
        searchBO
                .typeSearchText("SearchText")
                .submitSearchText();
    }
}
