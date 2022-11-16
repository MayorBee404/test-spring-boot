package com.abistudio.testspringboot.myuser;

import java.sql.*;

public class DBConnection {
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://36.95.3.227:3306/SE1";
    static final String USER = "SE1";
    static final String PASS = "basisdatarpl";

    static Connection connection;

    static void connect() throws SQLException {
        //Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connect();
        }

        return connection;
    }

}
