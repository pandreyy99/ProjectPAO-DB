package repositories;

import connectivity.DatabaseConnection;
import models.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRepository {
    private DatabaseConnection connection =
            DatabaseConnection.getInstance();
    private static int id = 0;

    public void setId(){
        String query = "select count(*) from person";
        try {
            PreparedStatement rowStatement = connection.getConnection().prepareStatement(query);

            ResultSet rs = rowStatement.executeQuery();
            while(rs.next()){
                id = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int savePerson(Person person) {
        this.setId();

        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO person VALUES (NULL,?,?,?)");

            statement.setString(1, person.getName().split(" ")[0]);
            statement.setString(2, person.getName().split(" ")[1]);
            statement.setInt(3, person.getAge());

            statement.executeUpdate();
            id++;
            System.out.println("Person saved!");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}