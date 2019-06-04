package repositories;

import connectivity.DatabaseConnection;
import models.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerRepository {
    private DatabaseConnection connection =
            DatabaseConnection.getInstance();

    public void saveManager(Manager manager) {
        try {
            Department department = manager.getDepartment();
            Location location = department.getLocation();

            SalaryRepository salaryRepository = new SalaryRepository();
            salaryRepository.saveSalary(manager.getSalary());

            LocationRepository locationRepository = new LocationRepository();
            if(locationRepository.getLocationByAdress(location.getCity(), location.getStreetAdress()) == null)
                locationRepository.saveLocation(location);

            DepartmentRepository departmentRepository = new DepartmentRepository();
            if(departmentRepository.getDepartmentByName(department.getName()) == null)
                departmentRepository.saveDepartment(department,
                        locationRepository.getLocationByAdress(location.getCity(), location.getStreetAdress()).getId());

            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO employee VALUES (NULL,?,?,?,?,?)");

            statement.setString(1, manager.getName().split(" ")[0]);
            statement.setString(2, manager.getName().split(" ")[1]);
            statement.setInt(3, manager.getAge());
            statement.setInt(4, salaryRepository.getLastSalary().getId());
            statement.setInt(5, departmentRepository
                    .getDepartmentByName(manager.getDepartment().getName()).getDepartmentId());

            statement.executeUpdate();
            System.out.println("Manager saved!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
