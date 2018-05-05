package com.bsdim.web.project.domain;

public class Subject extends Entity {
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return new StringBuilder("id: ")
                .append(getId())
                .append(" subjectName: ")
                .append(subjectName).toString();
    }
}
