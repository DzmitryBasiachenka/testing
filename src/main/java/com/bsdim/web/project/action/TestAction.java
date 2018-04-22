package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAction implements IAction {
    private static final String TEST_JSP = "test.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return TEST_JSP;
    }
}
