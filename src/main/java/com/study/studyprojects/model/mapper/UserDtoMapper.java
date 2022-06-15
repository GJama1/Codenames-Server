package com.study.studyprojects.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.studyprojects.model.dto.UserDto;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoMapper {

    public static UserDto toUserDto(ResultSet resultSet) throws SQLException {

        UserDto userDto = new UserDto();

        while (resultSet.next()) {
            userDto.setId(resultSet.getLong("id"));
            userDto.setUsername(resultSet.getString("username"));
        }

        return userDto;
    }

    public static String mapToJson(UserDto userDto) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userDto);
    }

    public static UserDto mapFromJson(String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, UserDto.class);
    }

}
