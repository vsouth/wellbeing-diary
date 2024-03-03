package ru.vsouth.wellbeingdiary.model;

public enum Role {
    USER("USER"),
    ANALYST("ANALYST"),
    ;

    private String name;

    Role(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
