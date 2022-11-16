package com.abistudio.testspringboot.myuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserDBManager {

    // class ini digunakan untuk melakukan operasi langsung terhadap database
    // konsekuensinya adalah segala kompleksitas yang terjadi akibat penggunaan database
    // muncul di dalam class ini (contoh: terjadinya SQLException dan
    // penggunaan object ResultSet sebagai hasil)

    // class ini dibuat static karena tidak memiliki property (tidak ada "state" yang disimpan)

    public static Vector<MyUser> getAllUsers() throws SQLException {
        String query = "select * from users";
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Vector<MyUser> allUsers = new Vector();

        while (resultSet.next()) {
            MyUser user = new MyUser();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));

            allUsers.add(user);
        }

        return allUsers;
    }

    public static MyUser getUser(String username) throws SQLException {
        String query = "select * from users where username=?";
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            MyUser user = new MyUser();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));

            return user;
        } else {
            return null;
        }
    }

    public static Vector<MyUser> getUserByName(String namePart) throws SQLException {
        String query = "select * from users where firstname like %?% or lastname like %?%";
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, namePart);
        preparedStatement.setString(2, namePart);
        ResultSet resultSet = preparedStatement.executeQuery();

        Vector<MyUser> filteredUsers = new Vector();

        while (resultSet.next()) {
            MyUser user = new MyUser();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));

            filteredUsers.add(user);
        }

        return filteredUsers;
    }

    public static int insertUser(MyUser newUserData) throws SQLException {
        String query = "insert into users(username,password,firstname,lastname) values(?,?,?,?)";
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, newUserData.getUsername());
        preparedStatement.setString(2, newUserData.getPassword());
        preparedStatement.setString(3, newUserData.getFirstname());
        preparedStatement.setString(4, newUserData.getLastname());

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows;
    }

    public static int deleteUser(String username) throws SQLException {
        String query = "delete from users where username=?";
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);

        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows;
    }
}
