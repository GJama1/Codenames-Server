package com.study.studyprojects.codenamesserver.repository;

import com.study.studyprojects.codenamesserver.utils.JdbcConnection;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserRepository {

    public static ResultSet findUserByUsername(String username) throws SQLException {

        Connection con = JdbcConnection.getConnection();

        PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE username = ?");
        statement.setString(1, username);

        return statement.executeQuery();
    }

    public static boolean saveUser(String username, String password) throws SQLException {

        Connection con = JdbcConnection.getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        statement.setString(1, username);
        statement.setString(2, password);

        return statement.executeUpdate()>0;
    }

    public static ResultSet findUserByUsernameAndPassword(String username, String password) throws SQLException {

        Connection con = JdbcConnection.getConnection();

        PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);

        return statement.executeQuery();
    }

}
