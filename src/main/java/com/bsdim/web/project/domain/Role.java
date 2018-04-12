package com.bsdim.web.project.domain;

public enum Role {
    ADMIN("admin"),
    TUTOR("tutor"),
    STUDENT("student");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    @Override
    public String toString() {
        return name;
    }
}
