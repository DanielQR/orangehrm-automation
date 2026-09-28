package com.orangehrm.tests;

import com.orangehrm.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/";
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.createDriver(browser);
        driver.get(BASE_URL);
    }
    protected static final String ADMIN_USERNAME = "Admin";
    protected static final String ADMIN_PASSWORD = "admin123";

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}