package repositories;

import connectivity.DatabaseConnection;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
    private DatabaseConnection connection =
            DatabaseConnection.getInstance();

    public void saveEmployee(Employee employee) {
        try {
            Department department = employee.getDepartment();
            Location location = department.getLocation();

            SalaryRepository salaryRepository = new SalaryRepository();
            salaryRepository.saveSalary(employee.getSalary());

            LocationRepository locationRepository = new LocationRepository();
            if(locationRepository.getLocationByAdress(location.getCity(), location.getStreetAdress()) == null)
                locationRepository.saveLocation(location);

            DepartmentRepository departmentRepository = new DepartmentRepository();
            if(departmentRepository.getDepartmentByName(department.getName()) == null)
                departmentRepository.saveDepartment(department,
                        locationRepository.getLocationByAdress(location.getCity(), location.getStreetAdress()).getId());

                PreparedStatement statement = connection.getConnection()
                        .prepareStatement("INSERT INTO employee VALUES (NULL,?,?,?,?,?,?)");

                statement.setString(1, employee.getEmployeeName().split(" ")[0]);
                statement.setString(2, employee.getEmployeeName().split(" ")[1]);
                statement.setInt(3, employee.getEmployeeAge());
                statement.setString(4, employee.getJob());
                statement.setInt(5, salaryRepository.getLastSalary().getId());
                statement.setInt(6, departmentRepository
                        .getDepartmentByName(employee.getDepartment().getName()).getDepartmentId());

                statement.executeUpdate();
                System.out.println("Employee saved!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteEmployee(Employee employee){
        String query = "delele from employee where firstname = ? and lastname = ? and age = ?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, employee.getName().split(" ")[0]);
            statement.setString(2, employee.getName().split(" ")[1]);
            statement.setInt(3, employee.getAge());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSalaryId(Employee employee){
        String query = "SELECT * FROM employee WHERE firstname = ? and lastname = ?";
        PreparedStatement statement = null;

        Salary salary = new Salary();
        try {
            statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, employee.getName().split(" ")[0]);
            statement.setString(2, employee.getName().split(" ")[1]);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.first())
                salary.setId(resultSet.getInt("salaryId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salary.getId();
    }
}