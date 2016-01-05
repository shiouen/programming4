package be.kdg.prog4.reflection;

import java.sql.*;

public class HsqlDbManager {
    private static final String path = "jdbc:hsqldb:mem:Test";
    private static final String user = "sa";
    private static final String pass = "";

    public HsqlDbManager() { }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        return DriverManager.getConnection(path, user, pass);
    }

    public void execute(String sql) {
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(sql);

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet read(String sql) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            connection.close();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
