package models;

import models.Employee;
import models.Manager;
import models.Person;
import models.Secretary;

import java.util.*;

public class Department {
    private String name;
    private int departmentId;
    private int employeesNumber = 0;
    private List<Employee> employees = new LinkedList<>();
    private Set<Person> drivers = new TreeSet<>();
    private Manager manager;
    private Secretary secretary;
    private Location location;

    public Department() {
    }

    public Department(int departmentId, String name, Location location) {
        this.departmentId = departmentId;
        this.name = name;
        this.location = location;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Set<Person> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Person> drivers) {
        this.drivers = drivers;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
        employeesNumber++;
    }

    public boolean removeEmployee(Employee employee) {
        for (Employee e : employees) {
            if (e.getEmployeeName().equals(employee.getName()) && e.getAge() == employee.getAge())
                if (employees.remove(employee))
                    return true;
        }
        return false;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return departmentId == that.departmentId &&
                employeesNumber == that.employeesNumber &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, name, employeesNumber, location);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", employeesNumber=" + employeesNumber +
                ", location=" + location +
                '}';
    }
}
