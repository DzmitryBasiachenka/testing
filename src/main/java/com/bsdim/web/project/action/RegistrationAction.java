package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationAction implements IAction {
    private static final String REGISTRATION_JSP = "registration.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return REGISTRATION_JSP;
    }
}