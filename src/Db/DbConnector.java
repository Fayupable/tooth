package Db;
import Db.Exception.DbConnectionException;
import Db.Exception.DbExceptionHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/tooth?useSSL=false&serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="12345678";
    private static Connection conn;
    DbExceptionHandler dbExceptionHandler = new DbExceptionHandler();


    public static Connection getConnection() throws DbConnectionException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(DbConnector.class.getName()).info("Connection established");
            return conn;
        } catch (SQLException e) {
            Logger.getLogger(DbConnector.class.getName()).severe("Connection failed: " + e.getMessage());
            throw new DbConnectionException("Failed to establish database connection.", e);
        }
    }

    public static Connection closeConnection() throws DbConnectionException {
        if (conn != null) {
            try {
                conn.close();
                Logger.getLogger(DbConnector.class.getName()).info("Connection closed");
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
                throw new DbConnectionException("Failed to close database connection.");
            }
        }
        return conn;
    }
}
