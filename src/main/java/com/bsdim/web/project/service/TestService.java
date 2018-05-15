package com.bsdim.web.project.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IAnswerDao;
import com.bsdim.web.project.dao.api.IQuestionDao;
import com.bsdim.web.project.dao.api.ISubjectDao;
import com.bsdim.web.project.dao.api.ITestDao;
import com.bsdim.web.project.dao.sql.AnswerDaoSql;
import com.bsdim.web.project.dao.sql.QuestionDaoSql;
import com.bsdim.web.project.dao.sql.SubjectDaoSql;
import com.bsdim.web.project.dao.sql.TestDaoSql;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;

public class TestService {
    private ITestDao testDao = new TestDaoSql();
    private ISubjectDao subjectDao = new SubjectDaoSql();
    private IQuestionDao questionDao = new QuestionDaoSql();
    private IAnswerDao answerDao = new AnswerDaoSql();

    public void addTest(Test test) {
        Connection connection = ConnectionContext.getConnection();
        try {
            connection.setAutoCommit(false);

            Subject subject = subjectDao.findSubjectByName(test.getSubject().getSubjectName());
            if (subject != null) {
                test.setSubject(subject);
                Integer idTest =  testDao.create(test);
                test.setId(idTest);
                List<Question> questions = test.getQuestions();
                for (Question question : questions) {
                    question.setTest(test);
                    Integer idQuestion = questionDao.create(question);
                    question.setId(idQuestion);
                    for (Answer answer : question.getAnswers()) {
                        answer.setQuestion(question);
                        answerDao.create(answer);
                    }
                }
            } else {
                throw new SQLException();
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Test findById(Integer id) {
        try {
            return testDao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateTest(Test test) {
        try {
            testDao.update(test);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteTest(Integer id) {
        try {
            testDao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Test> getTests() {
        try {
            return testDao.getTests();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /*public Test findTestById(Integer id) {
        try {
            return testDao.findTestById(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }*/

    public List<Test> findTestsByUserId(Integer id) {
        try {
            return testDao.findTestsByUserId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Test> findTestsBySubjectName(String subjectName) {
        try {
            return testDao.findTestsBySubjectName(subjectName);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
