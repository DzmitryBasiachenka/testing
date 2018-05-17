package com.bsdim.web.project.action.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

public class MainAction implements IAction {
    private static final String MAIN_JSP = "main.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return MAIN_JSP;
    }
}
