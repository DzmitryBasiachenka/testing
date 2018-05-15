package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.ISubjectDao;
import com.bsdim.web.project.dao.sql.SubjectDaoSql;
import com.bsdim.web.project.domain.Subject;

public class SubjectService {
    private ISubjectDao dao = new SubjectDaoSql();

    public Integer addSubject(Subject subject) {
        try {
            return dao.create(subject);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Subject findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateSubject(Subject subject) {
        try {
            dao.update(subject);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteSubject(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Subject> getSubjects() {
        try {
            return dao.getSubjects();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Subject findSubjectByName(String subjectName) {
        try {
            return dao.findSubjectByName(subjectName);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
