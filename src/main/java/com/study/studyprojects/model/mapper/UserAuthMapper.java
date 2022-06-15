package com.study.studyprojects.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.studyprojects.model.param.UserAuthParam;

import java.io.IOException;

public class UserAuthMapper {

    public static String mapToJson(UserAuthParam object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);

    }

    public static UserAuthParam mapFromJson(String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, UserAuthParam.class);

    }

}
