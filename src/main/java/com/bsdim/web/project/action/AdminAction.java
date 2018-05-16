package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAction implements IAction {
    private static final String ADMIN_JSP = "admin.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return ADMIN_JSP;
    }
}
