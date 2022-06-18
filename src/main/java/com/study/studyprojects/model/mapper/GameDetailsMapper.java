package com.study.studyprojects.model.mapper;

import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.enums.GameTurn;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDetailsMapper {

    public static GameDetails toGameDetails(ResultSet resultSet) throws SQLException {

        GameDetails gameDetails = new GameDetails();

        while (resultSet.next()) {
            gameDetails.setId(resultSet.getLong("id"));
            gameDetails.setOwnerId(resultSet.getLong("owner_id"));
            gameDetails.setTurn(GameTurn.RED);
            gameDetails.setRedScore(9);
            gameDetails.setBlueScore(8);
            gameDetails.setStarted(false);
        }

        return gameDetails;
    }

}
