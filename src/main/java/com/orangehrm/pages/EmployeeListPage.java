package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage extends BasePage {

    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By employeeIdInput = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By tableLoader = By.cssSelector(".oxd-table-loader");
    private static final String ROW_BY_EMPLOYEE_ID =
            "//div[contains(@class,'oxd-table-card')][.//div[normalize-space()='%s']]";

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public AddEmployeePage clickAdd() {
        click(addButton);
        return new AddEmployeePage(driver);
    }

    public void searchByEmployeeId(String employeeId) {
        type(employeeIdInput, employeeId);
        click(searchButton);
        waitInvisible(tableLoader);
    }

    public String getRowTextByEmployeeId(String employeeId) {
        By row = By.xpath(String.format(ROW_BY_EMPLOYEE_ID, employeeId));
        return isDisplayed(row) ? getText(row) : "";
    }
}