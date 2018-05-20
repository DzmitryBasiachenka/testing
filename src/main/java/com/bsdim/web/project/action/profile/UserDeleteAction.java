package com.bsdim.web.project.action.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.main.MainAction;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;
import org.apache.log4j.Logger;

/**
 * The user delete action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class UserDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String ACCOUNT_DELETED = "accountDeleted";
    private static final String ACCOUNT_DELETED_MESSAGE = "t.account.deleted.message";

    private static Logger sLogger = Logger.getLogger(UserDeleteAction.class);

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        service.deleteUser(userSession.getId());

        req.setAttribute(ACCOUNT_DELETED, ACCOUNT_DELETED_MESSAGE);
        sLogger.info(String.format("User '%1$s' deleted", userSession.getLogin()));
        session.invalidate();
        return new MainAction().perform(req, resp);
    }
}
