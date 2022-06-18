package com.study.studyprojects.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.dto.CardDto;
import com.study.studyprojects.model.dto.GameDto;

import java.util.List;

public class GameMapper {

    public static String mapToJson(GameDto object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);

    }

    public static GameDto mapFromJson(String json) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, GameDto.class);

    }

    public static GameDto mapGameDetailsToDto(GameDetails gameDetails, List<CardDto> cards) {

        return new GameDto(
                gameDetails.getId(),
                gameDetails.getRedScore(),
                gameDetails.getBlueScore(),
                gameDetails.getRedSpymaster(),
                gameDetails.getRedOperative(),
                gameDetails.getBlueSpymaster(),
                gameDetails.getBlueOperative(),
                cards
        );
    }

}
