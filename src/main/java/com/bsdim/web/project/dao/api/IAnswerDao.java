package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Answer;

public interface IAnswerDao extends IDao<Integer, Answer> {
    List<Answer> getAnswers();
}
