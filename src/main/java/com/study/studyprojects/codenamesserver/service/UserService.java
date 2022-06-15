package com.study.studyprojects.codenamesserver.service;

import com.study.studyprojects.codenamesserver.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public static boolean usernameAlreadyExists(String username) {

        boolean exists = false;

        try {
            ResultSet resultSet = UserRepository.findUserByUsername(username);

            if (resultSet.isBeforeFirst()) {
                exists = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exists;
    }

    public static boolean saveUser(String username, String password) {

        boolean saved = false;

        try {
            saved = UserRepository.saveUser(username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return saved;
    }

}
