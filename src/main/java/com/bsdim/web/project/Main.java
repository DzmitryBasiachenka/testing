package com.bsdim.web.project;

import com.bsdim.web.project.dao.sql.TestDaoSql;
import com.bsdim.web.project.domain.Test;

public class Main {
    public static void main(String[] args) {
        TestDaoSql testDaoSql = new TestDaoSql();
        for (Test test: testDaoSql.getTests()) {
            System.out.println(test);
        }
    }
}
