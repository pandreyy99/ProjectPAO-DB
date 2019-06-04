package repositories;

import connectivity.DatabaseConnection;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SecretaryRepository {
    private DatabaseConnection connection = DatabaseConnection.getInstance();

    public void saveSecretary(Secretary secretary) {
        try {
            Salary salary = secretary.getSalary();
            SalaryRepository salaryRepository = new SalaryRepository();
            salaryRepository.saveSalary(secretary.getSalary());

            Location location = secretary.getDepartment().getLocation();
            LocationRepository locationRepository = new LocationRepository();
            if(locationRepository.getLocationByAdress(location.getCity(), location.getStreetAdress()) == null)
                locationRepository.saveLocation(location);

            Department department = secretary.getDepartment();
            DepartmentRepository departmentRepository = new DepartmentRepository();
            if(departmentRepository.getDepartmentByName(department.getName()) == null)
                departmentRepository.saveDepartment(department,
                        locationRepository.getLocationByAdress(location.getCity(), location.getStreetAdress()).getId());

            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO secretary VALUES (NULL,?,?,?,?,?,?)");

            statement.setString(1, secretary.getName().split(" ")[0]);
            statement.setString(2, secretary.getName().split(" ")[1]);
            statement.setInt(3, secretary.getAge());
            statement.setBoolean(4, secretary.lookPretty());
            statement.setInt(5, salaryRepository.getLastSalary().getId());
            statement.setInt(6, departmentRepository.getDepartmentByName(department.getName()).getDepartmentId());

            statement.executeUpdate();
            System.out.println("Secretary saved!");

            ActivitiesRepository activitiesRepository = new ActivitiesRepository();
            activitiesRepository.saveActivities(secretary.getActivities());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastSecretaryId(){
        Driver driver = new Driver();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * FROM secretary");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.last())
                driver.setId(resultSet.getInt("ID"));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver.getId();
    }

    public int getSecretaryByName(String firstName, String lastName){
        Secretary secretary = new Secretary();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("select * from secretary where firstname = ? and lastname = ?");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.first())
                secretary.setId(resultSet.getInt("ID"));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return secretary.getId();
    }
}
