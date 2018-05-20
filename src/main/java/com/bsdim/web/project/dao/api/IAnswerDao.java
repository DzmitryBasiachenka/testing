package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Answer;

/**
 * Represents answer dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface IAnswerDao extends IDao<Integer, Answer> {
    /**
     * Gets list of answers.
     *
     * @return the list of answers.
     */
    List<Answer> getAnswers();
}
