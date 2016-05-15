import org.fluttercode.datafactory.impl.DataFactory;

import java.sql.*;

public class DataGenerator {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/springjta";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "root";
    static final int RECORDS_NUMBER = 10000;

    public static void main(String[] args) {
        new DataGenerator().run();
    }

    public void run() {
        DataFactory dataFactory = new DataFactory();
        Connection conn = null;
        try {
            System.out.print("Registering JDBC Driver... ");
            Class.forName(JDBC_DRIVER);
            System.out.println("done!");
            System.out.print("Connecting to a database... ");
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("done!");
            System.out.println("Inserting records into 'user'");
            createUserTable(conn, dataFactory, RECORDS_NUMBER);
            System.out.println("Inserting records into 'product'");
            createProductTable(conn, dataFactory, RECORDS_NUMBER);
            System.out.println("Inserting records into 'orders'");
            createOrdersTable(conn, dataFactory, RECORDS_NUMBER);
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.printf("%nFinished!%n");
    }

    private void createUserTable(Connection conn, DataFactory dataFactory, int number) throws SQLException {
        final String INSERT_USER = "INSERT INTO " +
                "user(name, lastname, age, phone, email, city, street, house_num, apartment_num) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(INSERT_USER);
        for (int i = 0; i < number; i++) {
            viewInsertProgress(i, "user");
            stmt.setString(1, dataFactory.getName());
            stmt.setString(2, dataFactory.getLastName());
            stmt.setInt(3, dataFactory.getNumberBetween(1, 80));
            stmt.setInt(4, dataFactory.getNumberBetween(0, 999999));
            stmt.setString(5, dataFactory.getEmailAddress());
            stmt.setString(6, dataFactory.getCity());
            stmt.setString(7, dataFactory.getStreetName());
            stmt.setInt(8, dataFactory.getNumberBetween(1, 500));
            stmt.setInt(9, dataFactory.getNumberBetween(1, 2000));
            stmt.executeUpdate();
        }
        viewInsertProgress(number, "user");
        stmt.close();
    }

    private void createProductTable(Connection conn, DataFactory dataFactory, int number) throws SQLException {
        final String INSERT_PRODUCT = "INSERT INTO product(product_num, product_name, product_rest) VALUES(?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(INSERT_PRODUCT);
        for (int i = 0; i < number; i++) {
            viewInsertProgress(i, "product");
            stmt.setString(1, dataFactory.getRandomChars(8, 12));
            stmt.setString(2, dataFactory.getRandomWord());
            stmt.setInt(3, dataFactory.getNumberBetween(1, 20000));
            stmt.executeUpdate();
        }
        viewInsertProgress(number, "user");
        stmt.close();
    }

    private void createOrdersTable(Connection conn, DataFactory dataFactory, int number) throws SQLException {
        final String INSERT_ORDER = "INSERT INTO " +
                "orders(order_num, order_date, customer, product_num, product_name, quantity) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER);
        for (int i = 0; i < number; i++) {
            viewInsertProgress(i, "orders");
            stmt.setString(1, dataFactory.getRandomChars(6, 6));
            stmt.setDate(2, new java.sql.Date(dataFactory.getBirthDate().getTime()));
            stmt.setString(3, dataFactory.getLastName());
            stmt.setString(4, dataFactory.getRandomChars(10, 10));
            stmt.setString(5, dataFactory.getRandomWord());
            stmt.setInt(6, dataFactory.getNumberBetween(1, 200));
            stmt.executeUpdate();
        }
        viewInsertProgress(number, "orders");
        stmt.close();
    }

    private void viewInsertProgress(int count, String table) {
        if ((count % 100 == 0) && (count > 0)) {
            System.out.printf("%d %s records...%n", count, table);
        }
    }
}
