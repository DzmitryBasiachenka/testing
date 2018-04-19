package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AboutAction implements IAction {
    private static final String ABOUT_JSP = "about.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return ABOUT_JSP;
    }
}
