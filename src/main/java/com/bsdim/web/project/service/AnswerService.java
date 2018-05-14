package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IAnswerDao;
import com.bsdim.web.project.dao.sql.AnswerDaoSql;
import com.bsdim.web.project.domain.Answer;

public class AnswerService {
    private IAnswerDao dao = new AnswerDaoSql();

    public void addAnswer(Answer answer) {
        try {
            dao.create(answer);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Answer findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateAnswer(Answer answer) {
        try {
            dao.update(answer);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteAnswer(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Answer> getAnswers() {
        try {
            return dao.getAnswers();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
