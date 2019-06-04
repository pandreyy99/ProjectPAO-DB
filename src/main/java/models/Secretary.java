package models;

import models.Department;
import models.Salary;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Secretary extends Person {
    private int id;
    private boolean looksPretty;
    private String job = "Secretary";
    private List<String> activities = new LinkedList<>();
    private Salary salary;
    private Department department;

    public Secretary(String firsName, String lastName, int age, Salary salary,
                     boolean looksPretty) {
        super(firsName, lastName, age);
        this.looksPretty = looksPretty;
        this.salary = salary;
    }

    public Secretary() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean lookPretty() {
        return looksPretty;
    }

    public void setLooksPretty(boolean looksPretty) {
        this.looksPretty = looksPretty;
    }

    public String getJob() {
        return job;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
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
    public String toString() {
        return "Secretary{" +
                "looksPretty=" + looksPretty +
                ", salary=" + salary +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Secretary)) return false;
        Secretary secretary = (Secretary) o;
        return looksPretty == secretary.looksPretty &&
                Objects.equals(job, secretary.job) &&
                Objects.equals(activities, secretary.activities) &&
                Objects.equals(salary, secretary.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(looksPretty, job, activities, salary);
    }

    public void addActivity(String activity) {
        activities.add(activity);
    }

    public void removeActivity(String activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }
}
