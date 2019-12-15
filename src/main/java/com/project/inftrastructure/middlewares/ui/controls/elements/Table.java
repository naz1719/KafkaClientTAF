package com.project.inftrastructure.middlewares.ui.controls.elements;

import com.project.inftrastructure.middlewares.ui.annotations.ImplementedBy;
import com.project.inftrastructure.middlewares.ui.controls.base.Control;
import com.project.inftrastructure.middlewares.ui.controls.elements.impl.TableControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

@ImplementedBy(TableControl.class)
public interface Table extends Control {
    /**
     * Get count of records in grid
     * @return number of records
     */
    int getRowCount();

    /**
     * Get table content
     * @return content of table
     */
    List<Map<String, String>> getTableContent();

    /**
     * Get button 'Delete'
     * @param vaadinTable data grid
     * @param row row to click on
     * @return button 'Delete' for specified row
     */
    WebElement getDeleteRowButton(Control vaadinTable, WebElement row);

    /**
     * Get button 'Delete'
     *
     * @param vaadinTable data grid
     * @param row row to click on
     * @return button 'Delete' for specified row
     */
    WebElement getDeleteRowIconButton(Control vaadinTable, WebElement row);

    /**
     * Get button 'Edit'
     * @param vaadinTable data grid
     * @param row row to click on
     * @return button 'Edit' for specified row
     */
    WebElement getEditRowButton(Control vaadinTable, WebElement row);

    /**
     * Get button 'Install'
     * @param vaadinTable data grid
     * @param row row to click
     * @return button 'Install' for specified row
     */
    WebElement getInstallRowButton(Control vaadinTable, WebElement row);

    /**
     * Get button 'Download'
     * @param vaadinTable data grid
     * @param row row to click
     * @return button 'Download' for specified row
     */
    WebElement getDownloadRowButton(Control vaadinTable, WebElement row);

    /**
     * Get row
     * @param content row content
     * @return element that represent row
     */
    WebElement getRowByContent(Map<String, String> content);

    /**
     * Get data from one column
     * @param columnName column name
     * @return data from specified column
     */
    List<String> getColumnData(String columnName);

    /**
     * Get last row
     * @return data from specified column
     */
    WebElement getLastRow();

    /**
     * Get rows input
     *
     * @param vaadinTable data grid
     * @param row row
     * @param column column
     * @param placeholder place holder
     * @return button 'Input' for specified row
     */
    WebElement getRowInput(Control vaadinTable, WebElement row, String column, String placeholder);

    /**
     * Click on column to sort
     * @param columnName column name
     * @param driver driver instance
     * @param grid grid with data
     */
    void clickOnColumnToSort(String columnName, WebDriver driver, WebElement grid);

    /**
     * Search data in columns
     * @param columnName column name
     * @param itemToSearch item to search
     * @param driver driver instance
     */
    void searchInColumn(String columnName, String itemToSearch, WebDriver driver);

    /**
     * Get table headers
     * @return list of table headers
     */
    List<WebElement> getHeaderElements();

    /**
     * Get button 'Details'
     * @param vaadinTable data grid
     * @param row row to click
     * @return button 'Details' for specified row
     */
    WebElement getDetailsRowButton(Control vaadinTable, WebElement row);

    WebElement getCellTitleButton(Control vaadinTable, WebElement row, String buttonTitle, WebDriver driver);

    WebElement getCellIconButton(Control vaadinTable, WebElement row, String buttonTitle, WebDriver driver);

    WebElement getCellExport(Control vaadinTable, WebDriver driver);

    WebElement getDownloadExportRowButton(Control vaadinTable, WebElement row, WebDriver driver);

    WebElement getDeleteExportRowButton(Control vaadinTable, WebElement row, WebDriver driver);

    WebElement getCopyExportRowButton(Control vaadinTable, WebElement row, WebDriver driver);

    WebElement getCopyRowButton(Control vaadinTable, WebElement row, String column);
}
