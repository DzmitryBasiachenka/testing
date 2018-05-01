package com.bsdim.web.project.action;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.WebUtil;

public class UserEditAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String LOGIN = "login";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String SAVE_USER = "saveUser";
    private static final String SAVE_USER_MESSAGE = "Data saved";
    private static final String PASSWORDS_NOT_MATCH = "passwordsNotMatch";
    private static final String PASSWORDS_NOT_MATCH_MESSAGE = "Password fields do not match or empty";
    private static final String EMPTY_USER = "emptyUser";
    private static final String EMPTY_USER_MESSAGE = "The all fields of user form should not be empty";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        if (WebUtil.isNotBlank(firstName, lastName)) {
            User user = service.findByLogin(req.getParameter(LOGIN));
            user = updateUser(user, req);
            if (user == null) {
                return new ProfileAction().perform(req, resp);
            } else {
                //updateUserSession(user, userSession);
            }
        } else {
            req.setAttribute(EMPTY_USER, EMPTY_USER_MESSAGE);
        }
        return new ProfileAction().perform(req, resp);
    }

    private User updateUser(User user, HttpServletRequest req) {
        String newPassword  = req.getParameter(NEW_PASSWORD);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD);

        if (WebUtil.isNotBlank(newPassword, confirmPassword) && newPassword.equals(confirmPassword)) {
            user.setPassword(newPassword);
        } else {
            req.setAttribute(PASSWORDS_NOT_MATCH, PASSWORDS_NOT_MATCH_MESSAGE);
            return null;
        }

        user.setFirstName(req.getParameter(FIRST_NAME));
        user.setLastName(req.getParameter(LAST_NAME));
        service.updateUser(user);

        req.setAttribute(SAVE_USER, SAVE_USER_MESSAGE);
        return user;
    }

    /*private UserSession updateUserSession(User user, UserSession userSession) {
        userSession.setLogin(user.getLogin());
        userSession.setFirstName(user.getFirstName());
        userSession.setLastName(user.getLastName());
        userSession.setRole(user.getRole());

        return userSession;
    }*/
}
