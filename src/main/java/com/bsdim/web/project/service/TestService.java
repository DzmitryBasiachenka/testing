package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.ITestDao;
import com.bsdim.web.project.dao.sql.TestDaoSql;
import com.bsdim.web.project.domain.Test;

public class TestService {
    private ITestDao dao = new TestDaoSql();

    public void addTest(Test test) {
        dao.create(test);
    }

    public Test findById(Integer id) {
        return dao.read(id);
    }

    public void updateTest(Test test) {
        dao.update(test);
    }

    public void deleteTest(Integer id) {
        dao.delete(id);
    }

    public List<Test> getTests() {
        return dao.getTests();
    }

    public List<Test> findTestsByUserId(Integer id) {
        return dao.findTestsByUserId(id);
    }
}
