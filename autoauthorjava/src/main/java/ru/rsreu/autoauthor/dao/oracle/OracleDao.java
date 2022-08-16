package ru.rsreu.autoauthor.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDao {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "sys as sysdba";
    private static final String PASS = "123";

    private static final OracleDao instance = new OracleDao();

    public static OracleDao instance() {
        return instance;
    }

    public Connection connection;

    private OracleDao() {
        try {
            DriverManager.registerDriver(new oracle.    jdbc.OracleDriver());
            createConnection();
            System.out.println(this.connection);
        } catch (SQLException e) {
            System.out.println("Connection error!");
        }
    }

    public void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public OracleGroupDao getCategoryDAO() {
        return new OracleGroupDao();
    }

    public OracleAuthorDao getAuthorDAO() {
        return new OracleAuthorDao();
    }

    public OracleRoleDao getRoleDAO() {
        return new OracleRoleDao();
    }

    public OracleUserDao getUserDAO() {
        return new OracleUserDao();
    }

    public OracleFileDao getFileDAO() {
        return new OracleFileDao();
    }

    public OracleModelDao getModelDAO() {
        return new OracleModelDao();
    }
}
