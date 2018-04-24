package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;

public class RegistrationAction implements IAction {
    private static final String REGISTRATION_JSP = "registration.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return REGISTRATION_JSP;
    }
}
