package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    private ArrayList<EmployeePayrollData> employeePayrollList;
    private static EmployeePayrollService instance;

    // Private constructor to enforce singleton pattern
    private EmployeePayrollService() {
        this.employeePayrollList = new ArrayList<>();
    }

    // Public method to get the singleton instance
    public static synchronized EmployeePayrollService getInstance() {
        if (instance == null) {
            instance = new EmployeePayrollService();
        }
        return instance;
    }

    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = EmployeePayrollService.getInstance();
        Scanner scanner = new Scanner(System.in);

        // Read employee data
        employeePayrollService.readEmployeePayrollData(scanner);

        // Write employee data to console
        employeePayrollService.writeEmployeePayrollData();

        // Connect to the database and save employee data
        PayrollDBService payrollDBService = PayrollDBService.getInstance();
        payrollDBService.connectAndSaveToDB(employeePayrollService.getEmployeePayrollList());

        // Retrieve employee data from the database using PreparedStatement
        List<EmployeePayrollData> retrievedData = payrollDBService.readEmployeePayrollDataFromDBByName("Terisa");
        System.out.println("Retrieved data for Terisa from the database: " + retrievedData);

        // Update salary for Asita
        String employeeNameToUpdate = "Asita";
        double newSalary = 3000000.00;
        employeePayrollService.updateEmployeeSalary(employeeNameToUpdate, newSalary);

        // Print updated employee data
        employeePayrollService.writeEmployeePayrollData();

        scanner.close(); // Close the scanner to avoid resource leak
    }

    private void readEmployeePayrollData(Scanner scanner) {
        System.out.println("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.println("Enter employee salary: ");
        double salary = scanner.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(name, salary));
    }

    private void writeEmployeePayrollData() {
        System.out.println("Employee Payroll Data: " + employeePayrollList);
    }

    private void updateEmployeeSalary(String employeeName, double newSalary) {
        // Update salary in the ArrayList
        for (EmployeePayrollData employee : employeePayrollList) {
            if (employee.getName().equals(employeeName)) {
                employee.setSalary(newSalary);
                break; // Assuming each employee has a unique name
            }
        }
    }

    // Getter for employeePayrollList
    public List<EmployeePayrollData> getEmployeePayrollList() {
        return employeePayrollList;
    }
}


