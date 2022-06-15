package com.study.studyprojects.codenamesserver.utils;

import com.study.studyprojects.codenamesserver.model.common.Message;
import lombok.extern.slf4j.Slf4j;

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
                    Message message = msg.get();


                }

            }

        }
        catch (Exception e) {
            log.error("Error consuming message. Message: {}, StackTrace: {}", e.getMessage(), e.getStackTrace());
        }



    }


    private Optional<Message> consumeMessage() throws Exception {

        return Optional.of((Message) in.readObject());
    }

}

