package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IAnswerDao;
import com.bsdim.web.project.dao.sql.AnswerDaoSql;
import com.bsdim.web.project.domain.Answer;

/**
 * The answer service.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class AnswerService {
    private IAnswerDao dao = new AnswerDaoSql();

    /**
     * Adds the answer.
     *
     * @param answer the answer.
     * @return the answer id.
     */
    public Integer addAnswer(Answer answer) {
        try {
            return dao.create(answer);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds answer by answer id.
     *
     * @param id the answer id.
     * @return the answer.
     */
    public Answer findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Updates the answer.
     *
     * @param answer the answer.
     */
    public void updateAnswer(Answer answer) {
        try {
            dao.update(answer);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes the answer.
     *
     * @param id the answer id.
     */
    public void deleteAnswer(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets answers.
     *
     * @return the list of answers.
     */
    public List<Answer> getAnswers() {
        try {
            return dao.getAnswers();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
