package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {

    private final By firstNameInput = By.name("firstName");
    private final By middleNameInput = By.name("middleName");
    private final By lastNameInput = By.name("lastName");
    private final By employeeIdInput = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
    private final By createLoginDetailsSwitch = By.cssSelector(".oxd-switch-input");
    private final By usernameInput = By.xpath("//label[text()='Username']/../following-sibling::div/input");
    private final By passwordInput = By.xpath("//label[text()='Password']/../following-sibling::div/input");
    private final By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By formLoader = By.cssSelector(".oxd-form-loader");
    private static final String STATUS_OPTION = "//label[normalize-space()='%s']";

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterMiddleName(String middleName) {
        type(middleNameInput, middleName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void enterEmployeeId(String employeeId) {
        wait.until(d -> {
            String value = d.findElement(employeeIdInput).getDomProperty("value");
            return value != null && !value.isEmpty();
        });
        type(employeeIdInput, employeeId);
    }

    public void enableCreateLoginDetails() {
        waitInvisible(formLoader);
        click(createLoginDetailsSwitch);
        waitVisible(usernameInput);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void selectStatus(String status) {
        click(By.xpath(String.format(STATUS_OPTION, status)));
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
        type(confirmPasswordInput, password);
    }

    public void clickSave() {
        click(saveButton);
    }

    public boolean isSaved() {
        try {
            wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}