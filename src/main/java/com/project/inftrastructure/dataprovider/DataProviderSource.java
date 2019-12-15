package com.project.inftrastructure.dataprovider;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DataProviderSource {

    @DataProvider(name = "webSite", parallel = true)
    public static Object[][] getFileEntry(ITestContext context) {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);

        List<Integer> list = IntStream.range(0, 3).boxed().collect(Collectors.toList());
        Object[][] objArray = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = list.get(i);
        }
        return objArray;
    }
}
