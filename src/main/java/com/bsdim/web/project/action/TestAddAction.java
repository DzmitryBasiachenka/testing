package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAddAction implements IAction {
    private static final String TEST_ADD_JSP = "test-add.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return TEST_ADD_JSP;
    }
}
