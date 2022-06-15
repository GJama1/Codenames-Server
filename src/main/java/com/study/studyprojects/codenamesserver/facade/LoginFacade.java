package com.study.studyprojects.codenamesserver.facade;

import com.study.studyprojects.codenamesserver.service.UserService;
import com.study.studyprojects.model.Message;
import com.study.studyprojects.model.MessageCodes;
import com.study.studyprojects.model.dto.UserDto;
import com.study.studyprojects.model.mapper.UserDtoMapper;
import com.study.studyprojects.model.param.UserAuthParam;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;

public class LoginFacade {


    public static void loginUser(UserAuthParam userAuthParam, ObjectOutputStream out) throws IOException {

        UserDto userDto = UserService.findUserUsernameAndPassword(userAuthParam.getUsername(), userAuthParam.getPassword()).orElse(null);

        if (userDto == null) {

            Message response = new Message(MessageCodes.UNAUTHORIZED, "Invalid credentials!");
            out.writeObject(response);
            out.flush();

        }
        else {

            Message response = new Message(MessageCodes.OK, UserDtoMapper.mapToJson(userDto));
            out.writeObject(response);
            out.flush();

        }

    }
}
