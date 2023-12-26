package org.example;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeePayrollService {
    private ArrayList<EmployeePayrollData> employeePayrollList;

    // Constructor that takes an ArrayList parameter
    public EmployeePayrollService(ArrayList<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }
    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner scanner = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(scanner);
        employeePayrollService.writeEmployeePayrollData();
    }
    private void readEmployeePayrollData(Scanner scanner){
        System.out.println("ENter employee ID: ");
        int id = scanner.nextInt();
        System.out.println("ENter employee name: ");
        String name = scanner.nextLine();
        System.out.println("ENter employee salary: ");
        double salary = scanner.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));

    }
    private void writeEmployeePayrollData(){
        System.out.println("Writing to console"+employeePayrollList);
    }

    }
