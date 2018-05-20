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
import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

/**
 * The question service.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class QuestionService {
    private static Logger sLogger = Logger.getLogger(QuestionService.class);

    private IQuestionDao questionDao = new QuestionDaoSql();
    private IAnswerDao answerDao = new AnswerDaoSql();

    /**
     * Addsquestion.
     *
     * @param question the question.
     */
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
                sLogger.error("Add question connection rollback error!");
                throw new TestingRuntimeException("Add question connection rollback error!", exp);
            }
            sLogger.error("Add question error!");
            throw new TestingRuntimeException("Add question error!", e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds question by question id.
     *
     * @param id the question id.
     * @return the question.
     */
    public Question findById(Integer id) {
        try {
            return questionDao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Updates question.
     *
     * @param question the question.
     */
    public void updateQuestion(Question question) {
        try {
            questionDao.update(question);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes question.
     *
     * @param id the question id.
     */
    public void deleteQuestion(Integer id) {
        try {
            questionDao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets questions.
     *
     * @return the question list.
     */
    public List<Question> getQuestions() {
        try {
            return questionDao.getQuestions();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets id questions by test id.
     *
     * @param id the test id.
     * @return the id list.
     */
    public List<Integer> getIdQuestionsByTestId(Integer id) {
        try {
            return questionDao.getIdQuestionsByTestId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets question.
     *
     * @param id the question id.
     * @return the question.
     */
    public Question getQuestion(Integer id) {
        try {
            return questionDao.getQuestion(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
