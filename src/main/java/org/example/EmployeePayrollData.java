package org.example;

public class EmployeePayrollData {

    public String name;
    public double salary;

    public EmployeePayrollData(String name, Double salary){
        this.name=name;
        this.salary=salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "name= " +name+ " salary= " +salary;
    }
}
