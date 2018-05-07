package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Subject;

public interface ISubjectDao extends IDao<Integer, Subject> {
    List<Subject> getSubjects();
    Subject findSubjectByName(String subjectName);
}
