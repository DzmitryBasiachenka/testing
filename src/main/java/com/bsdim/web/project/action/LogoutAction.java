package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.main.MainAction;
import org.apache.log4j.Logger;

public class LogoutAction implements IAction {
    private static final String USER_SESSION = "userSession";

    private static Logger sLogger = Logger.getLogger(LogoutAction.class);

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session.getAttribute(USER_SESSION) != null) {
            session.invalidate();
            sLogger.info("User session invalidate");
        }
        return new MainAction().perform(req, resp);
    }
}
