package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Test;

/**
 * Represents test dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface ITestDao extends IDao<Integer, Test> {
    /**
     * Gets tests.
     *
     * @return the test list.
     */
    List<Test> getTests();

    /**
     * Finds tests by user id.
     *
     * @param id the user id.
     * @return the test list.
     */
    List<Test> findTestsByUserId(Integer id);

    /**
     * Finds tests by subject name.
     *
     * @param subjectName the subject name.
     * @return the test list.
     */
    List<Test> findTestsBySubjectName(String subjectName);
}
