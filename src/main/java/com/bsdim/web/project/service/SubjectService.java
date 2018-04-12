package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.ISubjectDao;
import com.bsdim.web.project.dao.sql.SubjectDaoSql;
import com.bsdim.web.project.domain.Subject;

public class SubjectService {
    private ISubjectDao dao = new SubjectDaoSql();

    public void addSubject(Subject subject) {
        dao.create(subject);
    }

    public Subject findById(Integer id) {
        return dao.read(id);
    }

    public void updateSubject(Subject subject) {
        dao.update(subject);
    }

    public void deleteSubject(Integer id) {
        dao.delete(id);
    }

    public List<Subject> getSubjects() {
        return dao.getSubjects();
    }
}
