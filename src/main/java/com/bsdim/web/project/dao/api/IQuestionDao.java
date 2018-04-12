package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Question;

public interface IQuestionDao extends IDao<Integer, Question> {
    List<Question> getQuestions();
}
