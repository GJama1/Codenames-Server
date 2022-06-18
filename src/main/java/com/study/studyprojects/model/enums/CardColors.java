package com.study.studyprojects.model.enums;

public enum CardColors {

    RED("RED", "#d63b1c"),
    BLUE("BLUE", "#337aff"),
    BLACK("BLACK", "#000000"),
    WHITE("WHITE", "#ffffff");

    private final String colorName;
    private final String colorHex;

    CardColors(String colorName, String colorHex) {
        this.colorName = colorName;
        this.colorHex = colorHex;
    }

    public String getColorName() {
        return colorName;
    }
    public String getColorHex() {
        return colorHex;
    }

}
