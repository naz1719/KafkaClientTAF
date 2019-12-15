package com.project.services.ui.po;


import com.project.inftrastructure.middlewares.ui.annotations.Page;
import com.project.inftrastructure.middlewares.ui.controls.elements.Input;
import com.project.services.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page(title = "Google Search Page")
public class SearchPage extends BasePage {

    @FindBy(name = "q")
    private Input searchInput;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void typeSearchText(String searchText){
        searchInput.click();
        searchInput.sendText(searchText);
    }

    public void submit(){
        searchInput.submit();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        waitManager.isElementIsClickable(searchInput, 5);
    }
}
