package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.ISubjectDao;
import com.bsdim.web.project.dao.sql.SubjectDaoSql;
import com.bsdim.web.project.domain.Subject;

/**
 * The subject service.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class SubjectService {
    private ISubjectDao dao = new SubjectDaoSql();

    /**
     * Adds subject.
     *
     * @param subject the subject.
     * @return the subject id.
     */
    public Integer addSubject(Subject subject) {
        try {
            return dao.create(subject);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds subject by subject id.
     *
     * @param id the subject id.
     * @return the subject.
     */
    public Subject findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Updates subject.
     *
     * @param subject the subject.
     */
    public void updateSubject(Subject subject) {
        try {
            dao.update(subject);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes subject.
     *
     * @param id the subject id.
     */
    public void deleteSubject(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets subjects.
     *
     * @return the subject list.
     */
    public List<Subject> getSubjects() {
        try {
            return dao.getSubjects();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds subject by subject name.
     *
     * @param subjectName the subject name.
     * @return the subject
     */
    public Subject findSubjectByName(String subjectName) {
        try {
            return dao.findSubjectByName(subjectName);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
