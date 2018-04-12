package com.bsdim.web.project.domain;

public class Question extends Entity {
    private String questionName;
    private Test test;

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

    @Override
    public String toString() {
        return "Question{" +
                "questionName='" + questionName + '\'' +
                ", test=" + test +
                '}';
    }
}
