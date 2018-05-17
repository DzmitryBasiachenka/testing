package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

public class AdminAction implements IAction {
    private static final String ADMIN_JSP = "admin.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return ADMIN_JSP;
    }
}
