package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.main.MainAction;
import com.bsdim.web.project.session.UserSession;
import org.apache.log4j.Logger;

/**
 * The logout action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class LogoutAction implements IAction {
    private static final String USER_SESSION = "userSession";

    private static Logger sLogger = Logger.getLogger(LogoutAction.class);

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        if (userSession != null) {
            String login = userSession.getLogin();
            session.invalidate();
            sLogger.info(String.format("User session '%1$s' invalidate", login));
        }
        return new MainAction().perform(req, resp);
    }
}
