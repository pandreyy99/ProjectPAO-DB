package models;

import models.Department;
import models.Salary;

import java.util.Objects;

public class Manager extends Person {
    private String job = "Manager";
    private Salary salary;
    private Department department;

    public Manager(String firsName, String lastName, int age, Salary salary, Department department) {
        super(firsName, lastName, age);
        this.salary = salary;
        this.department = department;
    }

    public String getJob() {
        return job;
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

    @Override
    public String toString() {
        return "Manager{" +
                super.getName() + '\'' +
                "Department " + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(job, manager.job) &&
                Objects.equals(salary, manager.salary) &&
                Objects.equals(department, manager.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(job, salary, department);
    }
}
