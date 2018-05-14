package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IQuestionDao;
import com.bsdim.web.project.dao.sql.QuestionDaoSql;
import com.bsdim.web.project.domain.Question;

public class QuestionService {
    private IQuestionDao dao = new QuestionDaoSql();

    public void addQuestion(Question question) {
        try {
            dao.create(question);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Question findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateQuestion(Question question) {
        try {
            dao.update(question);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteQuestion(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Question> getQuestions() {
        try {
            return dao.getQuestions();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Integer> getIdQuestionsByTestId(Integer id) {
        try {
            return dao.getIdQuestionsByTestId(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Question getQuestion(Integer id) {
        try {
            return dao.getQuestion(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
