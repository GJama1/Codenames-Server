package com.study.studyprojects.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardDto {

    private Long id;

    private String showColor;

    private String actualColor;

    private String word;

    private Boolean clicked;

}
