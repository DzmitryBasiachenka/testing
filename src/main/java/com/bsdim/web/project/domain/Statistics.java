package com.bsdim.web.project.domain;

import java.sql.Timestamp;

public class Statistics extends Entity {
    private Test test;
    private Integer countCorrectAnswers;
    private Integer countIncorrectAnswers;
    private Timestamp startTesting;
    private Timestamp finishTesting;
    private User user;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getCountCorrectAnswers() {
        return countCorrectAnswers;
    }

    public void setCountCorrectAnswers(Integer countCorrectAnswers) {
        this.countCorrectAnswers = countCorrectAnswers;
    }

    public Integer getCountIncorrectAnswers() {
        return countIncorrectAnswers;
    }

    public void setCountIncorrectAnswers(Integer countIncorrectAnswers) {
        this.countIncorrectAnswers = countIncorrectAnswers;
    }

    public Timestamp getStartTesting() {
        return startTesting;
    }

    public void setStartTesting(Timestamp startTesting) {
        this.startTesting = startTesting;
    }

    public Timestamp getFinishTesting() {
        return finishTesting;
    }

    public void setFinishTesting(Timestamp finishTesting) {
        this.finishTesting = finishTesting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringBuilder("statisticsId: ")
                .append(getId())
                .append(" testId: ")
                .append(test.getId())
                .append(" countCorrectAnswers: ")
                .append(countCorrectAnswers)
                .append(" countIncorrectAnswers: ")
                .append(countIncorrectAnswers)
                .append(" startTesting: ")
                .append(startTesting)
                .append(" finishTesting: ")
                .append(finishTesting)
                .append(" userId: ")
                .append(user.getId()).toString();
    }
}
