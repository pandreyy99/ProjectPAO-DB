package repositories;

import connectivity.DatabaseConnection;
import models.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRepository {
    private DatabaseConnection connection =
            DatabaseConnection.getInstance();

    public void saveDepartment(Department department, int locationId) {
        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO departments VALUES (NULL,?,?,?)");


                statement.setInt(1, department.getEmployeesNumber());
                statement.setString(2, department.getName());
                statement.setInt(3, locationId);
                statement.executeUpdate();
                System.out.println("Department saved!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Department getDepartmentByName(String name){
        Department department = new Department();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * FROM departments WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                department.setDepartmentId(resultSet.getInt("ID"));
                department.setEmployeesNumber(resultSet.getInt("employeeNumber"));
                department.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(department.getDepartmentId() != 0)
            return department;
        else return null;
    }
}
