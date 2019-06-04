package repositories;

import connectivity.DatabaseConnection;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverRepository {
    private DatabaseConnection connection = DatabaseConnection.getInstance();

    public void saveDriver(Driver driver) {
        try {
            SalaryRepository salaryRepository = new SalaryRepository();
            salaryRepository.saveSalary(driver.getSalary());

            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO driver VALUES (NULL,?,?,?,?)");

            statement.setString(1, driver.getName().split(" ")[0]);
            statement.setString(2, driver.getName().split(" ")[1]);
            statement.setInt(3, driver.getAge());
            statement.setInt(4, salaryRepository.getLastSalary().getId());

            statement.executeUpdate();
            System.out.println("Driver saved!");

            LicenseRepository licenseRepository = new LicenseRepository();
            List<String> licensesList = new LinkedList<>();
            licensesList.addAll(driver.getDriverLicenseCategories());
            licenseRepository.saveDriverLicenses(licensesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastDriverId(){
        Driver driver = new Driver();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * FROM driver");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.last())
                driver.setId(resultSet.getInt("ID"));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver.getId();
    }
}
