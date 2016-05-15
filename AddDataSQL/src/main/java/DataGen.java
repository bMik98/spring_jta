import org.fluttercode.datafactory.impl.DataFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Shevchenko on 15.05.2016.
 */
public class DataGen {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/springjta";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "root";
    static final int RECORDS_NUMBER = 10000;

    public static void main(String[] args) {
        new DataGen().run();
    }

    public void run() {
        DataFactory dataFactory = new DataFactory();
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Long startTime = System.nanoTime();
            createUserTable(conn, dataFactory, RECORDS_NUMBER);
            createProductTable(conn, dataFactory, RECORDS_NUMBER);
            createOrdersTable(conn, dataFactory, RECORDS_NUMBER);
            System.out.println("spent time = " + (System.nanoTime()-startTime)/1000000000.0 + " s");
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

    private void createUserTable(Connection conn, DataFactory dataFactory, int number) throws SQLException, ParseException {
        final String INSERT_USER = "INSERT INTO " +
                "user(ftiny, fsmall, fint, fbig, fdouble, fdate, fyear, fchar, fvchar, fdec) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        createFieldsTable(conn, dataFactory, number, INSERT_USER);
    }

    private void createProductTable(Connection conn, DataFactory dataFactory, int number) throws SQLException, ParseException {
        final String INSERT_PRODUCT = "INSERT INTO " +
                "product(ftiny, fsmall, fint, fbig, fdouble, fdate, fyear, fchar, fvchar, fdec) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        createFieldsTable(conn, dataFactory, number, INSERT_PRODUCT);

    }

    private void createOrdersTable(Connection conn, DataFactory dataFactory, int number) throws SQLException, ParseException {
        final String INSERT_ORDER = "INSERT INTO " +
                "user(ftiny, fsmall, fint, fbig, fdouble, fdate, fyear, fchar, fvchar, fdec) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        createFieldsTable(conn, dataFactory, number, INSERT_ORDER);
    }

    private void printInsertProgress(int count, String table) {
        if ((count % 100 == 0) && (count > 0)) {
            System.out.printf("%d %s records...%n", count, table);
        }
    }

    private void createFieldsTable(Connection conn, DataFactory dataFactory, int number, String query) throws SQLException, ParseException{
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < number; i++) {
            stmt.setByte(1, (byte) dataFactory.getNumberBetween(-128, 127));
            stmt.setInt(2, dataFactory.getNumberBetween(-32768, 32767));
            stmt.setInt(3, dataFactory.getNumberBetween(0, 32767));
            stmt.setLong(4, dataFactory.getNumberBetween(0, 32767));
            stmt.setDouble(5, dataFactory.getNumberBetween(0, 32767));
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            stmt.setTimestamp(6, new Timestamp(dataFactory
                    .getDateBetween(sdf.parse("01-01-1976 00:00:00"),sdf.parse("15-05-2016 23:59:59"))
                    .getTime()));
            stmt.setInt(7, dataFactory.getNumberBetween(1901, 2155));
            stmt.setString(8, dataFactory.getStreetName().substring(0, 1));
            stmt.setString(9, dataFactory.getStreetName().substring(0, 1));
            stmt.setBigDecimal(10, new BigDecimal(dataFactory.getNumberBetween(0, 32767)));
            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        System.out.printf("%d records done!%n", number);
        stmt.close();
    }
}
