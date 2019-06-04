package repositories;

import connectivity.DatabaseConnection;
import models.Location;
import models.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRepository {
    private DatabaseConnection connection =
            DatabaseConnection.getInstance();

    public void saveLocation(Location location) {
        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO location VALUES (NULL,?,?)");

            statement.setString(1, location.getCity());
            statement.setString(2, location.getStreetAdress());

            statement.executeUpdate();
            System.out.println("Location saved!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Location getLocationByAdress(String city, String address){
        Location location = new Location();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * FROM location WHERE city = ? and streetAddress = ?");
            statement.setString(1, city);
            statement.setString(2, address);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                location.setId(resultSet.getInt("ID"));
                location.setCity(resultSet.getString("City"));
                location.setStreetAdress(resultSet.getString("StreetAddress"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (location.getId() != 0)
            return location;
        else return null;
    }
}
