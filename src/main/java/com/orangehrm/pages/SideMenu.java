package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenu extends BasePage {

    private final By pimOption = By.xpath("//a[contains(@href,'viewPimModule')]");

    public SideMenu(WebDriver driver) {
        super(driver);
    }

    public boolean isVisible() {
        return isDisplayed(pimOption);
    }

    public EmployeeListPage goToPim() {
        click(pimOption);
        return new EmployeeListPage(driver);
    }
}