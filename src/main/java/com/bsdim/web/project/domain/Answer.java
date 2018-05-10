package com.bsdim.web.project.domain;

public class Answer extends Entity {
    private String answerName;
    private boolean correctAnswer;
    private Question question;

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return new StringBuilder("answerId: ")
                .append(getId())
                .append(" answerName: ")
                .append(answerName)
                .append(" correctAnswer: ")
                .append(correctAnswer)
                .append(" questionId: ")
                .append(question.getId()).toString();
    }
}
