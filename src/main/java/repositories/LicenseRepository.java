package repositories;

import connectivity.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LicenseRepository {
    private DatabaseConnection connection = DatabaseConnection.getInstance();

    public void saveDriverLicenses(List<String> licenses) {
        int id = new DriverRepository().getLastDriverId();

        System.out.println(id);

        for (String license : licenses) {
            try {
                PreparedStatement statement = connection.getConnection()
                        .prepareStatement("INSERT INTO licenses VALUES (NULL,?,?)");

                statement.setString(1, license);
                System.out.println(id);
                statement.setInt(2, id);

                statement.executeUpdate();
                System.out.println("Driver License saved!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
