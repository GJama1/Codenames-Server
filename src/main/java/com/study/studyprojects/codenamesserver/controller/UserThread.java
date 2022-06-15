package com.study.studyprojects.codenamesserver.controller;

import com.study.studyprojects.codenamesserver.facade.LoginFacade;
import com.study.studyprojects.codenamesserver.facade.SignupFacade;
import com.study.studyprojects.codenamesserver.service.UserService;
import com.study.studyprojects.model.Message;
import com.study.studyprojects.model.MessageCodes;
import com.study.studyprojects.model.mapper.UserAuthMapper;
import com.study.studyprojects.model.param.UserAuthParam;
import lombok.extern.slf4j.Slf4j;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

@Slf4j
public class UserThread extends Thread {


    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public UserThread(Socket socket) {

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

