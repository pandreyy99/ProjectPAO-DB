package repositories;

import connectivity.DatabaseConnection;
import models.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryRepository {
    private DatabaseConnection connection =
            DatabaseConnection.getInstance();

    public void saveSalary(Salary salary) {
        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO salary VALUES (NULL,?,?,?)");

            statement.setDouble(1, salary.getSalariuBrut());
            statement.setDouble(2, salary.getCommissionPct());
            statement.setDouble(3, salary.getSalariuNet());

            statement.executeUpdate();
            System.out.println("Salary saved!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Salary getLastSalary(){
        Salary salary = new Salary();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * FROM salary order by id desc limit 1");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                salary.setId(resultSet.getInt("ID"));
                salary.setSalariuBrut(resultSet.getDouble("SalariuBrut"));
                salary.setCommissionPct(resultSet.getDouble("commission_pct"));
                salary.setSalariuNet(resultSet.getDouble("SalariuNet"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salary;
    }

    public void updateSalary(Salary newSalary, int salaryId){
        String query = "UPDATE salary SET salariuBrut = ?, commission_pct = ?, salariuNet = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setDouble(1, newSalary.getSalariuBrut());
            statement.setDouble(2, newSalary.getCommissionPct());
            statement.setDouble(3, newSalary.getSalariuNet());
            statement.setInt(4, salaryId);

            statement.executeUpdate();
            System.out.println("Salary updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
