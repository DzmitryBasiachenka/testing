package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.IQuestionDao;
import com.bsdim.web.project.dao.sql.QuestionDaoSql;
import com.bsdim.web.project.domain.Question;

public class QuestionService {
    private IQuestionDao dao = new QuestionDaoSql();

    public void addQuestion(Question question) {
        dao.create(question);
    }

    public Question findById(Integer id) {
        return dao.read(id);
    }

    public void updateQuestion(Question question) {
        dao.update(question);
    }

    public void deleteQuestion(Integer id) {
        dao.delete(id);
    }

    public List<Question> getQuestions() {
        return dao.getQuestions();
    }

    public List<Integer> getIdQuestionsByTestId(Integer id) {
        return dao.getIdQuestionsByTestId(id);
    }

    public Question getQuestion(Integer id) {
        return dao.getQuestion(id);
    }
}
