package com.study.studyprojects.codenamesserver.service;

import com.study.studyprojects.codenamesserver.repository.GameRepository;
import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.mapper.GameDetailsMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Getter
@Setter
@Slf4j
public class GameService {

    private GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public GameService() {
        this.repository = new GameRepository();
    }

    public boolean createGame(long ownerId) {

        boolean result = false;

        try {
            result = repository.createGame(ownerId);
        } catch (SQLException e) {
            log.error("Sql error. Message: {}", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public Optional<GameDetails> findGameByOwnerId(long ownerId) {

        Optional<GameDetails> gameDetails = Optional.empty();

        try {

            ResultSet result = repository.findLatestCreatedGameByOwnerId(ownerId);
            gameDetails = Optional.of(GameDetailsMapper.toGameDetails(result));

        } catch (SQLException e) {
            log.error("Sql error. Message: {}", e.getMessage());
            e.printStackTrace();

        }

        return gameDetails;
    }

}
