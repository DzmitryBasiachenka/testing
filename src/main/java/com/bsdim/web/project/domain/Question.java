package com.bsdim.web.project.domain;

import java.util.List;

public class Question extends Entity {
    private String questionName;
    private Test test;
    private List<Answer> answers;

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ")
                .append(getId())
                .append(" questionName: ")
                .append(questionName)
                .append(" test: ")
                .append(test);
        for (Answer answer : answers) {
            stringBuilder.append(answer);
        }
        return stringBuilder.toString();
    }
}
