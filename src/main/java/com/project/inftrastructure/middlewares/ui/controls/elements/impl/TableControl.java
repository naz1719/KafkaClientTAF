package com.project.inftrastructure.middlewares.ui.controls.elements.impl;

import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.base.WebControl;
import com.project.inftrastructure.middlewares.ui.controls.elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;



public class TableControl extends WebControl implements Table {

    public TableControl(WebElement element, String name, String page) {
        super(element, name, page);
    }

    public int getRowCount() {
        return webElement.findElements(By.tagName("tr")).size();
    }

    public List<String> getHeader() {
        ArrayList<String> result = new ArrayList<>();
        webElement.findElements(By.tagName("th")).stream().forEach(we -> result.add(we.getText()));
        return result;
    }

    public WebElement getDeleteRowButton(Control vaadinTable, WebElement row) {
        return getCellButton(vaadinTable, row, "Delete");
    }

    @Override
    public WebElement getDeleteRowIconButton(Control vaadinTable, WebElement row) {
        return getCellIconButton(vaadinTable, row, "delete");
    }

    public WebElement getInstallRowButton(Control vaadinTable, WebElement row) {
        return getCellButton(vaadinTable, row, "Install");
    }

    public WebElement getDownloadRowButton(Control vaadinTable, WebElement row) {
        return getCellButton(vaadinTable, row, "Download");
    }

    public WebElement getEditRowButton(Control vaadinTable, WebElement row) {
        return getCellButton(vaadinTable, row, "Edit");
    }

    public WebElement getDownloadExportRowButton(Control vaadinTable, WebElement row, WebDriver driver) {
        return getCellTitleButton(vaadinTable, row, "Download", driver);
    }

    public WebElement getDeleteExportRowButton(Control vaadinTable, WebElement row, WebDriver driver) {
        return getCellIconButton(vaadinTable, row, "delete", driver);
    }

    public WebElement getCopyExportRowButton(Control vaadinTable, WebElement row, WebDriver driver) {
        return getCellTitleButton(vaadinTable, row, "Copy download link to clipboard", driver);
    }

    @Override
    public WebElement getCopyRowButton(Control vaadinTable, WebElement row, String column) {
        return getCellButton(vaadinTable, row, "Copy download link to clipboard", column);
    }

    public WebElement getRowByContent(Map<String, String> rowContent) {
        List<WebElement> elements = webElement.findElements(By.cssSelector("#items > tr"));
        String[] columns = getTableHeader();
        final WebElement[] found = new WebElement[1];
        elements.stream().forEach(row -> {
            Map<String, String> rowMap = rowToMap(columns, row);
            if (rowMap.entrySet().containsAll(rowContent.entrySet())) {
                found[0] = row;
            }
        });
        return found[0];
    }

    public List<Map<String, String>> getTableContent() {
        ArrayList result = new ArrayList();
        List<WebElement> elements = webElement.findElements(By.cssSelector("#items > tr"));
        String[] columns = getTableHeader();
        elements.forEach(row -> {
            Map<String, String> rowMap = rowToMap(columns, row);
            result.add(rowMap);
        });
        return result;
    }

    public WebElement getLastRow() {
        List<WebElement> elements = webElement.findElements(By.cssSelector("#items > tr"));
        return elements.get(elements.size() - 1);
    }

    @Override
    public WebElement getRowInput(Control vaadinTable, WebElement row, String column, String placeholder) {
        return vaadinTable
                .findElement(By.cssSelector("vaadin-grid-cell-content[slot='" + getSlotNames(row).get(column) + "'] input[placeholder='" + placeholder + "']"));
    }

    public List<String> getColumnData(String columnName) {
        List<String> columnData = new ArrayList<>();
        String[] columns = getTableHeader();
        List<String> list = Arrays.asList(columns);
        int index = list.indexOf(columnName) + 1;
        List<WebElement> elements = webElement.findElements(By.cssSelector("#items > tr > td:nth-of-type(" + index + ")"));
        elements.forEach(element ->
                columnData.add(element.getText())
        );
        return columnData;
    }

    public List<WebElement> getHeaderElements() {
        return webElement.findElements(By.cssSelector("#header > tr:not([hidden])> th"));
    }

    @Override
    public void clickOnColumnToSort(String columnName, WebDriver driver, WebElement el) {
        List<WebElement> columnsList = getHeaderElements();
        WebElement btnSort;
        for (WebElement column : columnsList) {
            if (column.getText().trim().equals(columnName)) {
                btnSort = el.findElement(By.cssSelector("vaadin-grid-cell-content[slot='" + createXpathFromId(column) + "']  vaadin-grid-sorter iron-icon"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSort);
            }
        }
    }

    @Override
    public void searchInColumn(String columnName, String itemToSearch, WebDriver driver) {
        List<WebElement> columnsList = getHeaderElements();
        for (WebElement column : columnsList) {
            if (column.getText().equals(columnName)) {
                createXpathFromId(column);
                driver.findElement(By.xpath("//vaadin-grid-cell-content[@slot = '"
                        + createXpathFromId(column) + "']/vaadin-grid-sorter//iron-input/input")).sendKeys(itemToSearch);
            }
        }
    }

    public WebElement getDetailsRowButton(Control vaadinTable, WebElement row) {
        return vaadinTable.findElement(By.xpath("//vaadin-grid-cell-content[@slot='" + getDetailsSlotName(row) + "']//*[@id='seeDetail']"));
    }

    private String createXpathFromId(WebElement column) {
        String id = column.getAttribute("id");
        return id.substring(0, 16)
                + "-content"
                + id.substring(16);
    }

    private WebElement getCellButton(Control vaadinTable, WebElement row, String buttonTitle) {
        return vaadinTable.findElement(By.xpath("//vaadin-grid-cell-content[@slot='" + getActionsSlotName(row) + "']//paper-icon-button[@title='" + buttonTitle + "']"));
    }
    
    private WebElement getCellIconButton(Control vaadinTable, WebElement row, String iconName) {
        return vaadinTable.findElement(By.cssSelector("vaadin-grid-cell-content[slot='" + getActionsSlotName(row) + "'] paper-icon-button[icon='" + iconName + "']"));
    }

    private WebElement getCellButton(Control vaadinTable, WebElement row, String buttonTitle, String column) {
        return vaadinTable.findElement(By.xpath("//vaadin-grid-cell-content[@slot='" + getSlotNames(row).get(column) + "']//paper-icon-button[@title='" + buttonTitle + "']"));
    }

    public WebElement getCellExport(Control vaadinTable, WebDriver driver) {
        return vaadinTable.expandRootElement(driver.findElement(By.xpath("//export-db")), driver);
    }

    public WebElement getCellTitleButton(Control vaadinTable, WebElement row, String buttonTitle, WebDriver driver) {
        return getCellExport(vaadinTable, driver)
                .findElement(By.cssSelector("vaadin-grid-cell-content[slot='" + getActionsSlotName(row) + "'] paper-icon-button[title='" + buttonTitle + "']"));
    }

    public WebElement getCellIconButton(Control vaadinTable, WebElement row, String buttonTitle, WebDriver driver) {
        return getCellExport(vaadinTable, driver)
                .findElement(By.cssSelector("vaadin-grid-cell-content[slot='" + getActionsSlotName(row) + "'] paper-icon-button[icon='" + buttonTitle + "']"));
    }

    private String[] getTableHeader() {
        List<WebElement> columnsList = getHeaderElements();
        String[] columns = new String[columnsList.size()];
        for (int i = 0; i < columnsList.size(); i++) {
            columns[i] = columnsList.get(i).getText().trim();
        }
        return columns;
    }

    private Map<String, String> rowToMap(String[] columns, WebElement row) {
        Map<String, String> rowMap = new LinkedHashMap<>();
        String[] cells = row.getText().split("\n");
        for (int i = 0; i < columns.length; i++) {
            if (i < cells.length) {
                rowMap.put(columns[i], cells[i]);
            } else {
                rowMap.put(columns[i], "");
            }
        }
        return rowMap;
    }

    private String getActionsSlotName(WebElement row) {
        return getSlotNames(row).get("Actions");
    }

    private String getDetailsSlotName(WebElement row) {
        return getSlotNames(row).get("Details");
    }

    private Map<String, String> getSlotNames(WebElement row) {
        Map<String, String> cellMap = new HashMap<>();
        List<WebElement> cells = row.findElements(By.tagName("td"));
        String[] columns = getTableHeader();
        for (int i = 0; i < columns.length; i++)
            cellMap.put(columns[i], cells.get(i).findElement(By.tagName("slot")).getAttribute("name"));
        return cellMap;
    }
}
