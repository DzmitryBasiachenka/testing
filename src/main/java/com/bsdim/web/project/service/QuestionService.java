package com.bsdim.web.project.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IAnswerDao;
import com.bsdim.web.project.dao.api.IQuestionDao;
import com.bsdim.web.project.dao.sql.AnswerDaoSql;
import com.bsdim.web.project.dao.sql.QuestionDaoSql;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;

public class QuestionService {
    private IQuestionDao questionDao = new QuestionDaoSql();
    private IAnswerDao answerDao = new AnswerDaoSql();

    public void addQuestion(Question question) {
        Connection connection = ConnectionContext.getConnection();
        try {
            connection.setAutoCommit(false);

            Integer questionId = questionDao.create(question);
            if (questionId != null) {
                question.setId(questionId);
                for (Answer answer : question.getAnswers()) {
                    answer.setQuestion(question);
                    answerDao.create(answer);
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

    public Question findById(Integer id) {
        try {
            return questionDao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateQuestion(Question question) {
        try {
            questionDao.update(question);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteQuestion(Integer id) {
        try {
            questionDao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Question> getQuestions() {
        try {
            return questionDao.getQuestions();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Integer> getIdQuestionsByTestId(Integer id) {
        try {
            return questionDao.getIdQuestionsByTestId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Question getQuestion(Integer id) {
        try {
            return questionDao.getQuestion(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
