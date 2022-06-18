package com.study.studyprojects.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.studyprojects.model.param.CreateGameParam;

public class CreateGameMapper {

    public static String mapToJson(CreateGameParam object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);

    }

    public static CreateGameParam mapFromJson(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, CreateGameParam.class);

    }

}
