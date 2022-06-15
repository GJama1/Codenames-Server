package com.study.studyprojects.codenamesserver;

import com.study.studyprojects.codenamesserver.controller.UserThread;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class Main
{

    static Set<UserThread> userThreads = new HashSet<>();

    public static void main( String[] args )
    {

        try(InputStream is = Main.class.getResourceAsStream("/application.properties")) {

            Properties props = new Properties();
            props.load(is);

            int port = Integer.parseInt(props.getProperty("server.port"));

            log.info("Starting server on port {}", port);

            ServerSocket socket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = socket.accept();

                log.info("New user connected");
                userThreads.add(new UserThread(clientSocket));
            }

        }catch (IOException e) {
            log.error("Error reading properties file. Message: {}, StackTrace: {}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
        }


    }
}
