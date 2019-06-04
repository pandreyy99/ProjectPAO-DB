package models;

import models.Salary;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Driver extends Person {
    private int id;
    private Set<String> driverLicenseCategories = new TreeSet<>();
    private String job = "Driver";
    private Salary salary;

    public Driver(String firsName, String lastName, int age, Salary salary,
                  List<String> driverLicenseCategories) {
        super(firsName, lastName, age);
        this.salary = salary;
        this.driverLicenseCategories.addAll(driverLicenseCategories);
    }

    public Driver() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public Salary getSalary() {
        return this.salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public void addDriverLicenseCategory(String category) {
        this.driverLicenseCategories.add(category);
    }

    public Set<String> getDriverLicenseCategories() {
        return driverLicenseCategories;
    }

    public String getAllDriverLicense() {
        String tmp = "";
        for (String license : this.driverLicenseCategories) {
            tmp.concat(license);
            tmp.concat(" ");
        }
        return tmp;
    }

    public void busted() {
        this.driverLicenseCategories.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), driverLicenseCategories, getJob(), salary);
    }


}
