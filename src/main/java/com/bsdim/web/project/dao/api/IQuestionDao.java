package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Question;

/**
 * Represents question dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface IQuestionDao extends IDao<Integer, Question> {
    /**
     * Gets questions.
     *
     * @return the list of questions.
     */
    List<Question> getQuestions();

    /**
     * Gets id questions by test id.
     *
     * @param id the test id.
     * @return the list of id questions.
     */
    List<Integer> getIdQuestionsByTestId(Integer id);

    /**
     * Gets question.
     *
     * @param id the question id.
     * @return the question.
     */
    Question getQuestion(Integer id);
}
