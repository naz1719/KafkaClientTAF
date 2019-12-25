package com.project.inftrastructure.dataprovider;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.List;

public class GoogleSearchDataProvider {

    @DataProvider(name = "itemsToSearch")
    public static Object[][] getFileEntry(ITestContext context) {
//        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);

        List<String> items = Arrays.asList("item1", "item2");

        Object[][] objArray = new Object[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = items.get(i);
        }
        return objArray;
    }
}
