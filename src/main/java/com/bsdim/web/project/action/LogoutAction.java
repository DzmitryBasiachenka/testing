package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements IAction {
    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if(session.getAttribute("userSession") != null) {
            session.invalidate();
        }
        return new MainAction().perform(req, resp);
    }
}
