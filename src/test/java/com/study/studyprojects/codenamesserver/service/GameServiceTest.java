package com.study.studyprojects.codenamesserver.service;

import com.study.studyprojects.codenamesserver.repository.GameRepository;
import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.enums.GameTurn;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mockito;
import org.postgresql.jdbc.PgResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {


    private static GameService service;

    private static GameRepository repository = mock(GameRepository.class);

    @BeforeAll
    @SneakyThrows
    static void setUp() {

        when(repository.createGame(eq(1L))).thenReturn(true);
        when(repository.createGame(eq(2L))).thenReturn(false);

        service = new GameService(repository);

    }


    @Test
    @SneakyThrows
    void createGame_TestIfReturnsTrueWhenRepositoryReturnsTrue() {

        boolean result = service.createGame(1L);

        verify(repository, times(1)).createGame(eq(1L));

        assertTrue(result);
    }

    @Test
    @SneakyThrows
    void createGame_TestIfReturnsFalseWhenRepositoryReturnsFalse() {

        boolean result = service.createGame(2L);

        verify(repository, times(1)).createGame(eq(2L));

        assertFalse(result);
    }

}