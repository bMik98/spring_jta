import org.fluttercode.datafactory.impl.DataFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class General {

    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static void main(String[] args) {
        DataFactory dataFactory = new DataFactory();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();

            for (int i = 0; i < 400; i++) {
                String dataQuery = "INSERT INTO User( name, lastname, age, phone, email, city, street, number_house, number_apartment) VALUES('" +
                        dataFactory.getName() + "','" +
                        dataFactory.getLastName() + "','" +
                        dataFactory.getNumberBetween(1, 80) + "','" +
                        dataFactory.getNumberBetween(0, 999999) + "','" +
                        dataFactory.getEmailAddress() + "','" +
                        dataFactory.getCity() + "','" +
                        dataFactory.getStreetName() + "','" +
                        dataFactory.getNumberBetween(1, 500) + "','" +
                        dataFactory.getNumberBetween(1, 2000) + "');";
                System.out.println(dataQuery);
                statement.executeUpdate(dataQuery);
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
