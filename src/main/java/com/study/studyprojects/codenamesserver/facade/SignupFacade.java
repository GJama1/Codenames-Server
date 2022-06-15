package com.study.studyprojects.codenamesserver.facade;

import com.study.studyprojects.codenamesserver.service.UserService;
import com.study.studyprojects.model.Message;
import com.study.studyprojects.model.MessageCodes;
import com.study.studyprojects.model.param.UserAuthParam;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SignupFacade {

    public static void signUpUser(UserAuthParam userAuthParam, ObjectOutputStream out) throws IOException {

        boolean usernameAlreadyExists = UserService.usernameAlreadyExists(userAuthParam.getUsername());

        if (usernameAlreadyExists) {

            Message response = new Message(MessageCodes.CONFLICT, "Username already exists");
            out.writeObject(response);
            out.flush();

        }
        else {

            boolean saved = UserService.saveUser(userAuthParam.getUsername(), userAuthParam.getPassword());

            if (saved) {
                Message response = new Message(MessageCodes.CREATED, "User created");
                out.writeObject(response);
                out.flush();
            }
            else {
                Message response = new Message(MessageCodes.INTERNAL_SERVER_ERROR, "Failed to create user");
                out.writeObject(response);
                out.flush();
            }

        }

    }

}
