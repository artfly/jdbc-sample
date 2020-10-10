package com.home.jdbc.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCHelper {

    static {
        try {
            Class.forName(JDBCConstants.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found");
        }
    }

    private static Connection connection;

    // TODO: guess it was a bug initially
    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(JDBCConstants.URL, JDBCConstants.USERNAME, JDBCConstants.PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }
}
