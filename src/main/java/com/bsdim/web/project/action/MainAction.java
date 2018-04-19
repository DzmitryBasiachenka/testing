package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction implements IAction {
    private static final String MAIN_JSP = "main.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return MAIN_JSP;
    }
}
