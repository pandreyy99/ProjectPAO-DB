package repositories;

import connectivity.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ActivitiesRepository {
    private DatabaseConnection connection = DatabaseConnection.getInstance();

    public void saveActivities(List<String> activities) {
        int id = new SecretaryRepository().getLastSecretaryId();

        System.out.println(id);

        for (String activity : activities) {
            try {
                PreparedStatement statement = connection.getConnection()
                        .prepareStatement("INSERT INTO activities VALUES (NULL,?,?)");

                statement.setString(1, activity);
                statement.setInt(2, id);

                statement.executeUpdate();
                System.out.println("Secretary activity saved!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveActivityToAGivenSecretary(String activity, int secreatryID) {
            try {
                PreparedStatement statement = connection.getConnection()
                        .prepareStatement("INSERT INTO activities VALUES (NULL,?,?)");

                statement.setString(1, activity);
                statement.setInt(2, secreatryID);

                statement.executeUpdate();
                System.out.println("Secretary activity saved!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
