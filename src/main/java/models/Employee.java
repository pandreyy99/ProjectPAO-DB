package models;

import models.Department;
import models.Salary;

import java.util.Objects;

public class Employee extends Person {
    private String job;
    private Salary salary;
    private Department department;

    public Employee(String firstName, String lastName, int age, String job, Salary salary,
                    Department department) {
        super(firstName, lastName, age);
        this.job = job;
        this.salary = salary;
        this.department = department;
    }

    public String getEmployeeName() {
        return super.getName();
    }

    public int getEmployeeAge() {
        return super.getAge();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getJob(), getSalary(), getDepartment());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "job='" + job + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                "} " + super.toString();
    }
}
