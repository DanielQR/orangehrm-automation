package com.orangehrm.tests;

import com.orangehrm.data.EmployeeDataProvider;
import com.orangehrm.models.Employee;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.EmployeeListPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.SideMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AltaEmpleadoTest extends BaseTest {

    @Test(dataProvider = "employees", dataProviderClass = EmployeeDataProvider.class)
    public void crearEmpleadoYVerificarQueApareceEnElListado(Employee employee) {
        LoginPage loginPage = new LoginPage(driver);
        SideMenu sideMenu = new SideMenu(driver);

        loginPage.login(ADMIN_USERNAME, ADMIN_PASSWORD);
        Assert.assertTrue(sideMenu.isVisible(), "No se pudo iniciar sesión");

        EmployeeListPage employeeList = sideMenu.goToPim();

        AddEmployeePage addEmployee = employeeList.clickAdd();
        addEmployee.enterFirstName(employee.getFirstName());
        addEmployee.enterMiddleName(employee.getMiddleName());
        addEmployee.enterLastName(employee.getLastName());
        addEmployee.enterEmployeeId(employee.getEmployeeId());
        addEmployee.enableCreateLoginDetails();
        addEmployee.enterUsername(employee.getUsername());
        addEmployee.enterPassword(employee.getPassword());
        addEmployee.selectStatus(employee.getStatus());
        addEmployee.clickSave();
        Assert.assertTrue(addEmployee.isSaved(), "No se pudo guardar el empleado " + employee);

        employeeList = sideMenu.goToPim();
        employeeList.searchByEmployeeId(employee.getEmployeeId());

        String resultRow = employeeList.getRowTextByEmployeeId(employee.getEmployeeId());
        Assert.assertFalse(resultRow.isEmpty(), "El empleado " + employee + " no aparece en la grilla");
        Assert.assertTrue(resultRow.contains(employee.getFirstName()), "La grilla no muestra el nombre esperado");
        Assert.assertTrue(resultRow.contains(employee.getLastName()), "La grilla no muestra el apellido esperado");
    }
}