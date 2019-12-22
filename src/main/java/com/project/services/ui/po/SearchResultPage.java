package com.project.services.ui.po;

import com.project.inftrastructure.middlewares.ui.annotations.Page;
import com.project.inftrastructure.middlewares.ui.controls.elements.Button;
import com.project.services.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

@Page(title = "Google Search Result Page")
public class SearchResultPage extends BasePage<SearchResultPage> {

    @FindBy(xpath = ".//*[@class='rc']")
    private List<Button> webResults;

    public List<Button> getWebResults() {
        return webResults;
    }

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        boolean contains = driver.getTitle().contains("Google");
        Assert.assertTrue(contains);
    }
}
