package com.study.studyprojects.model;

import com.study.studyprojects.model.dto.CardDto;
import com.study.studyprojects.model.dto.UserDto;
import com.study.studyprojects.model.enums.GameTurn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDetails {

    public Long id;

    public Long ownerId;

    private Integer redScore;

    private Integer blueScore;

    private GameTurn turn;

    private Boolean started;

    private String winner;

    private UserDto redSpymaster;

    private UserDto redOperative;

    private UserDto blueSpymaster;

    private UserDto blueOperative;

    private List<CardDto> operativeCards;

    private List<CardDto> spymasterCards;

}
