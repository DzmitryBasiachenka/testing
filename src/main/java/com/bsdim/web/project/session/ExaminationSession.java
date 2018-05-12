package com.bsdim.web.project.session;

import java.sql.Timestamp;
import java.util.List;

import com.bsdim.web.project.domain.Question;

public class ExaminationSession {
    private Integer testId;
    private String testName;
    private Question question;
    private List<Integer> idQuestions;
    private int countCorrectAnswers;
    private int countIncorrectAnswers;
    private Timestamp startTesting;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Integer> getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(List<Integer> idQuestions) {
        this.idQuestions = idQuestions;
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
}
