package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Test;

public interface ITestDao extends IDao<Integer, Test> {
    List<Test> getTests();
    List<Test> findTestsByUserId(Integer id);
    List<Test> findTestsBySubjectName(String subjectName);
}
