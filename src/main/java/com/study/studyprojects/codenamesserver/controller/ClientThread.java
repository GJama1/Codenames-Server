package com.study.studyprojects.codenamesserver.controller;

import com.study.studyprojects.codenamesserver.facade.CreateGameFacade;
import com.study.studyprojects.codenamesserver.facade.LoginFacade;
import com.study.studyprojects.codenamesserver.facade.SignupFacade;
import com.study.studyprojects.model.GameDetails;
import com.study.studyprojects.model.Message;
import com.study.studyprojects.model.MessageCodes;
import com.study.studyprojects.model.dto.UserDto;
import com.study.studyprojects.model.mapper.CreateGameMapper;
import com.study.studyprojects.model.mapper.UserAuthMapper;
import com.study.studyprojects.model.param.CreateGameParam;
import com.study.studyprojects.model.param.UserAuthParam;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

@Slf4j
@Getter
@Setter
public class ClientThread extends Thread {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private GameDetails gameDetails;

    public ClientThread(Socket socket) {

        this.socket = socket;

        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {

            log.error("Error creating input/output streams. Message: {}, StackTrace: {}", e.getMessage(), e.getStackTrace());

        }

        start();

    }

    @Override
    public void run() {

        try {

            while(socket.isConnected()) {

                Optional<Message> msg = consumeMessage();
                if(msg.isPresent()) {

                    log.debug("Received message: {}", msg.get());
                    Message request = msg.get();

                    if (request.getCode().equals(MessageCodes.SIGN_UP_REQUEST)) {

                        UserAuthParam userAuthParam = UserAuthMapper.mapFromJson(request.getBody());
                        SignupFacade.signUpUser(userAuthParam, out);
                    }
                    else if (request.getCode().equals(MessageCodes.LOGIN_REQUEST)) {

                        UserAuthParam userAuthParam = UserAuthMapper.mapFromJson(request.getBody());
                        LoginFacade.loginUser(userAuthParam, out);
                    }
                    else if (request.getCode().equals(MessageCodes.CREATE_GAME_REQUEST)) {

                        CreateGameParam createGameParam = CreateGameMapper.mapFromJson(request.getBody());

                        CreateGameFacade createGameFacade = new CreateGameFacade(out);

                        Optional<GameDetails> game = createGameFacade.createGame(createGameParam);

                        game.ifPresent(details -> this.gameDetails = details);

                    }


                }

            }

        }
        catch (Exception e) {
            log.error("Error consuming message. Message: {}", e.getMessage());
        }

    }


    private Optional<Message> consumeMessage() throws Exception {

        return Optional.of((Message) in.readObject());
    }

}

