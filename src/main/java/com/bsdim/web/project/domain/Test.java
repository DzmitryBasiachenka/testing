package com.bsdim.web.project.domain;

public class Test extends Entity {
    private String testName;
    private Subject subject;
    private User user;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testName='" + testName + '\'' +
                ", subject=" + subject +
                ", user=" + user +
                '}';
    }
}
