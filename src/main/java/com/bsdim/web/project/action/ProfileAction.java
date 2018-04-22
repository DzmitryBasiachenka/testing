package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;

public class ProfileAction implements IAction {
    //private static final String USER_SESSION = "userSession";
    //private static final String USER = "user";
    private static final String PROFILE_JSP = "profile.jsp";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return PROFILE_JSP;
        /*HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
        User user = service.findByLogin(userSession.getLogin());
        req.setAttribute(USER, user);
        return PROFILE_JSP;*/
    }
}
