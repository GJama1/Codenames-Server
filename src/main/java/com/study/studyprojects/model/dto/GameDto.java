package com.study.studyprojects.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    private Long id;

    private Integer redScore;

    private Integer blueScore;

    private UserDto redSpymaster;

    private UserDto redOperative;

    private UserDto blueSpymaster;

    private UserDto blueOperative;

    private List<CardDto> cards;

}
