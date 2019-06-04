import models.*;
import repositories.DriverRepository;
import repositories.EmployeeRepository;
import repositories.SecretaryRepository;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<String> driverCategories = new LinkedList<>();
//        driverCategories.add("A");
//        driverCategories.add("B");
//        Driver driver = new Driver("Pazvantes", "Chioru", 25,
//                                    new Salary(2500, 0), driverCategories);
//        DriverRepository driverRepository = new DriverRepository();
//        driverRepository.saveDriver(driver);
        Location location = new Location("Braila", "Str. Armoniei");
        Department department = new Department(1, "IT", location);
//        Employee e = new Employee("Paraschiv", "Andrei", 19, "Soft Dev",
//                            new Salary(3000, 0.5), department);
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        employeeRepository.saveEmployee(e);

        List<String> activities = new LinkedList<>();
        activities.add("Do something!");
        activities.add("Eat");

        Secretary secretary = new Secretary("Nicoleta", "Sapun", 23,
                new Salary(2500,10), true);
        secretary.setDepartment(department);
        secretary.setActivities(activities);
        System.out.println(secretary.toString());
        SecretaryRepository secretaryRepository = new SecretaryRepository();
        secretaryRepository.saveSecretary(secretary);
    }
}
