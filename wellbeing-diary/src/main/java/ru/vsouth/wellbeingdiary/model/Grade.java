package ru.vsouth.wellbeingdiary.model;

public enum Grade {
    AWFUL("AWFUL"),
    BAD("BAD"),
    NORMAL("NORMAL"),
    GOOD("GOOD"),
    EXCELLENT("EXCELLENT"),
    ;

    private String name;

    Grade(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
