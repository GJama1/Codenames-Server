package com.study.studyprojects.codenamesserver.service;

import com.study.studyprojects.codenamesserver.repository.UserRepository;
import com.study.studyprojects.model.dto.UserDto;
import com.study.studyprojects.model.mapper.UserDtoMapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public class UserService {

    public static boolean usernameAlreadyExists(String username) {

        boolean exists = false;

        try {
            ResultSet resultSet = UserRepository.findUserByUsername(username);

            if (resultSet.isBeforeFirst()) {
                exists = true;
            }

        } catch (SQLException e) {
            log.error("Sql error. Message: {}", e.getMessage());
            e.printStackTrace();
        }

        return exists;
    }

    public static boolean saveUser(String username, String password) {

        boolean saved = false;

        try {
            saved = UserRepository.saveUser(username, password);

        } catch (SQLException e) {
            log.error("Sql error. Message: {}", e.getMessage());
            e.printStackTrace();
        }

        return saved;
    }

    public static Optional<UserDto> findUserUsernameAndPassword(String username, String password) {

       Optional<UserDto> userDto = Optional.empty();

        try {
            ResultSet result = UserRepository.findUserByUsernameAndPassword(username, password);
            if (result.isBeforeFirst()) {
                userDto = Optional.of(UserDtoMapper.toUserDto(result));
            }
        } catch (SQLException e) {
            log.error("Sql error. Message: {}", e.getMessage());
            e.printStackTrace();
        }

        return userDto;
    }

}
