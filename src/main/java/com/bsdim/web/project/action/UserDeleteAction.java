package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;

public class UserDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);
        service.deleteUser(userSession.getId());
        if(session.getAttribute("userSession") != null) {
            session.invalidate();
        }
        return new MainAction().perform(req, resp);
    }
}
