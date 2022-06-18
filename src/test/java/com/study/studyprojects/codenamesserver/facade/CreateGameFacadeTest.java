package com.study.studyprojects.codenamesserver.facade;

import com.study.studyprojects.codenamesserver.service.GameService;
import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.Message;
import com.study.studyprojects.model.enums.GameTurn;
import com.study.studyprojects.model.param.CreateGameParam;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ObjectOutputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateGameFacadeTest {


    private static CreateGameFacade facade;
    private static ObjectOutputStream out = mock(ObjectOutputStream.class);
    private static GameService service = mock(GameService.class);

    @BeforeAll
    @SneakyThrows
    static void setUp() {

        doNothing().when(out).writeObject(any(Message.class));
        doNothing().when(out).flush();

        when(service.createGame(eq(1L))).thenReturn(true);
        when(service.createGame(eq(2L))).thenReturn(false);
        when(service.createGame(eq(3L))).thenReturn(true);

        when(service.findGameByOwnerId(eq(1L))).thenReturn(Optional.of(
                new GameDetails(
                        1L,
                        1L,
                        9,
                        8,
                        GameTurn.RED,
                        false,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )


        ));

        when(service.findGameByOwnerId(eq(3L))).thenReturn(Optional.empty());

        facade = new CreateGameFacade(out, service);

    }

    @Test
    @SneakyThrows
    void createGame_TestIfReturnsGameDetails() {

        CreateGameParam param = new CreateGameParam(1L);

        Optional<GameDetails> result = facade.createGame(param);

        verify(service, times(1)).createGame(eq(1L));
        verify(out, times(1)).writeObject(any(Message.class));
        verify(out, times(1)).flush();

        assertTrue(result.isPresent());

        GameDetails gameDetails = result.get();

        assertEquals(1L, gameDetails.getId());
        assertEquals(1L, gameDetails.getOwnerId());
        assertEquals(9, gameDetails.getRedScore());
        assertEquals(8, gameDetails.getBlueScore());
        assertEquals(GameTurn.RED, gameDetails.getTurn());

    }

    @Test
    @SneakyThrows
    void createGame_TestIfReturnsEmptyOptionalWhenServiceReturnedFalse() {

        CreateGameParam param = new CreateGameParam(2L);

        Optional<GameDetails> result = facade.createGame(param);

        verify(service, times(1)).createGame(eq(2L));

        assertFalse(result.isPresent());

    }

    @Test
    @SneakyThrows
    void createGame_TestIfReturnsEmptyOptionalWhenServiceReturnsEmptyOptional() {

        CreateGameParam param = new CreateGameParam(3L);

        Optional<GameDetails> result = facade.createGame(param);

        verify(service, times(1)).createGame(eq(3L));

        assertFalse(result.isPresent());

    }
}