package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.WebUtil;

public class UserEditAction implements IAction {
    private static final String USER_SESSION = "userSession";
    //private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ROLE = "role";
    private static final String SAVE = "save";
    private static final String SAVE_MESSAGE = "Data saved";
    private static final String WRONG = "wrong";
    private static final String WRONG_MESSAGE = "User with the login existed";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        User user = service.findByLogin(userSession.getLogin());

        if(user == null) {
            req.setAttribute(WRONG, null);
            req.setAttribute(SAVE, SAVE_MESSAGE);
            userSession = updateUserSession(userSession, req);
            service.updateUser(userSession);
        } else {
            req.setAttribute(SAVE, null);
            req.setAttribute(WRONG, WRONG_MESSAGE);
        }

        return new ProfileAction().perform(req, resp);
    }

    private UserSession updateUserSession(UserSession userSession, HttpServletRequest req) {
        String newPassword = req.getParameter(NEW_PASSWORD);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD);
        boolean isNotBlank = WebUtil.isNotBlank(newPassword, confirmPassword);

        if(isNotBlank & (newPassword.equals(confirmPassword))) {
            userSession.setPassword(newPassword);
        }

        userSession.setLogin(req.getParameter(LOGIN));
        userSession.setFirstName(req.getParameter(FIRST_NAME));
        userSession.setLastName(req.getParameter(LAST_NAME));

        return userSession;
    }
}
