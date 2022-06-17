package com.study.studyprojects.codenamesserver.facade;

import com.study.studyprojects.codenamesserver.service.GameService;
import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.Message;
import com.study.studyprojects.model.MessageCodes;
import com.study.studyprojects.model.dto.GameDto;
import com.study.studyprojects.model.mapper.GameMapper;
import com.study.studyprojects.model.param.CreateGameParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

@Getter
@Setter
public class CreateGameFacade {

    private ObjectOutputStream out;
    private GameService gameService;

    public CreateGameFacade(ObjectOutputStream out) {
        this.out = out;
        this.gameService = new GameService();
    }

    public CreateGameFacade(ObjectOutputStream out, GameService gameService) {
        this.out = out;
        this.gameService = gameService;
    }

    public Optional<GameDetails> createGame(CreateGameParam createGameParam) throws IOException {

        long ownerId = createGameParam.getOwnerId();

        boolean result = gameService.createGame(ownerId);

        Optional<GameDetails> gameDetails = Optional.empty();

        if (!result) {

            Message message = new Message(MessageCodes.INTERNAL_SERVER_ERROR, "Game not created");

            out.writeObject(message);
            out.flush();

            return gameDetails;
       }
       else {

          gameDetails = gameService.findGameByOwnerId(ownerId);

          if (gameDetails.isEmpty()) {

              Message message = new Message(MessageCodes.INTERNAL_SERVER_ERROR, "Game not found");

              out.writeObject(message);
              out.flush();

              return gameDetails;
          }
          else {

              GameDto dto = GameMapper.mapGameDetailsToDto(gameDetails.get(), null);
              String body = GameMapper.mapToJson(dto);

              Message message = new Message(MessageCodes.CREATED, body);

              out.writeObject(message);
              out.flush();

              return gameDetails;
          }

       }

    }
}
