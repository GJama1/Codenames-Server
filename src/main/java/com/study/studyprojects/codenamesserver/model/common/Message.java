package com.study.studyprojects.codenamesserver.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Message implements Serializable {

    private String code;
    private String body;

}
