package com.orangehrm.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangehrm.models.Employee;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeDataProvider {

    private static final String EMPLOYEES_FILE = "/data/employees.json";

    @DataProvider(name = "employees")
    public static Object[][] employees() throws IOException {
        List<Employee> employees = readEmployeesFromFile();

        Object[][] data = new Object[employees.size()][1];
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            makeUnique(employee);
            data[i][0] = employee;
        }
        return data;
    }

    private static List<Employee> readEmployeesFromFile() throws IOException {
        try (InputStream file = EmployeeDataProvider.class.getResourceAsStream(EMPLOYEES_FILE)) {
            if (file == null) {
                throw new IllegalStateException("No se encontró el archivo " + EMPLOYEES_FILE);
            }
            return new ObjectMapper().readValue(file, new TypeReference<List<Employee>>() {});
        }
    }

    private static void makeUnique(Employee employee) {
        String suffix = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        String baseName = employee.getFirstName();

        employee.setFirstName(baseName + suffix);
        employee.setEmployeeId("E" + suffix);
        employee.setUsername(baseName.toLowerCase() + suffix);
    }
}