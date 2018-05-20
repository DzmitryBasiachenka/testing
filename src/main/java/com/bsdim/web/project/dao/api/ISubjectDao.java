package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Subject;

/**
 * Represents subject dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface ISubjectDao extends IDao<Integer, Subject> {
    /**
     * Gets subjects.
     *
     * @return the list of subjects.
     */
    List<Subject> getSubjects();

    /**
     * Finds subject by name.
     *
     * @param subjectName the subject name.
     * @return the subject.
     */
    Subject findSubjectByName(String subjectName);
}
