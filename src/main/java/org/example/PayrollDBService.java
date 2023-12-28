package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayrollDBService {
    private static PayrollDBService instance;
    private Connection connection;
    private PreparedStatement preparedStatement;

    // Private constructor to enforce singleton pattern
    private PayrollDBService() {
        // Initialize the database connection
        initializeConnection();
        // Prepare the PreparedStatement for retrieval by name
        prepareStatementForName();
    }

    // Public method to get the singleton instance
    public static synchronized PayrollDBService getInstance() {
        if (instance == null) {
            instance = new PayrollDBService();
        }
        return instance;
    }

    private void initializeConnection() {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/EmployeePayrollService";
        String user = "meshwar";
        String password = "iloveROOT12#$";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatementForName() {
        // Prepare the PreparedStatement for retrieving payroll data by name
        String selectQuery = "SELECT * FROM dummy_table WHERE name=?";
        try {
            preparedStatement = connection.prepareStatement(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectAndSaveToDB(List<EmployeePayrollData> employeePayrollList) {
        // Create a dummy_table if it doesn't exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS dummy_table (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), salary DOUBLE)";
        try (PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery)) {
            createTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Iterate through employeePayrollList and save to the database
        for (EmployeePayrollData employee : employeePayrollList) {
            String insertQuery = "INSERT INTO dummy_table (name, salary) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setDouble(2, employee.getSalary());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Data saved to the database.");
    }

    public List<EmployeePayrollData> readEmployeePayrollDataFromDBByName(String name) {
        List<EmployeePayrollData> retrievedData = new ArrayList<>();
        try {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String employeeName = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                retrievedData.add(new EmployeePayrollData(employeeName, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retrievedData;
    }
}
