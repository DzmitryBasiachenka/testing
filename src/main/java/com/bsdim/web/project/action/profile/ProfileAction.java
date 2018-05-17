package com.bsdim.web.project.action.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;

public class ProfileAction implements IAction {
    private static final String PROFILE_JSP = "profile.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return PROFILE_JSP;
    }
}
