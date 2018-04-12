package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.IAnswerDao;
import com.bsdim.web.project.dao.sql.AnswerDaoSql;
import com.bsdim.web.project.domain.Answer;

public class AnswerService {
    private IAnswerDao dao = new AnswerDaoSql();

    public void addAnswer(Answer answer) {
        dao.create(answer);
    }

    public Answer findById(Integer id) {
        return dao.read(id);
    }

    public void updateAnswer(Answer answer) {
        dao.update(answer);
    }

    public void deleteAnswer(Integer id) {
        dao.delete(id);
    }

    public List<Answer> getAnswers() {
        return dao.getAnswers();
    }
}
