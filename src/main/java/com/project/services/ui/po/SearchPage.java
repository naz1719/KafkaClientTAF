package com.project.services.ui.po;


import com.project.inftrastructure.middlewares.ui.annotations.Page;
import com.project.inftrastructure.middlewares.ui.controls.elements.Input;
import com.project.services.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static org.testng.AssertJUnit.assertTrue;

@Page(title = "Google Search Page")
public class SearchPage extends BasePage<SearchPage> {

    @FindBy(name = "q")
    private Input searchInput;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void typeSearchText(String searchText){
        searchInput
                .clearAndType(searchText);
    }

    public void submit(){
        searchInput.submit();
    }

    @Override
    protected void load() {
        String url = "https://www.google.com.ua/";
        step("Navigate to " + url);
        driver.get(url);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue("Not on the issue entry page: " + url, url.endsWith("ua/"));
    }
}
