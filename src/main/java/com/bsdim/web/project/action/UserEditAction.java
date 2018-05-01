package com.bsdim.web.project.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.MD5Encoder;
import com.bsdim.web.project.util.WebUtil;

public class UserEditAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String SAVE_USER = "saveUser";
    private static final String SAVE_USER_MESSAGE = "Data saved";
    private static final String EMAIL_EXISTS = "emailExists";
    private static final String EMAIL_EXISTS_MESSAGE = "The email already exists";
    private static final String EMAIL_WRONG = "emailWrong";
    private static final String EMAIL_WRONG_MESSAGE = "The email is not valid";
    private static final String PASSWORDS_NOT_EQUALS = "passwordsNotEquals";
    private static final String PASSWORDS_NOT_EQUALS_MESSAGE = "The password fields are not equals";
    private static final String EMPTY_USER = "emptyUser";
    private static final String EMPTY_USER_MESSAGE = "The all fields of user form should not be empty";

    private String email;
    private String firstName;
    private String lastName;
    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        email = req.getParameter(EMAIL);
        firstName = req.getParameter(FIRST_NAME);
        lastName = req.getParameter(LAST_NAME);
        if (WebUtil.isNotBlank(email, firstName, lastName)) {
            Pattern emailPattern = Pattern.compile("(\\w{3,}@(\\w+\\.)([a-z]{2,4}))");
            Matcher matcher = emailPattern.matcher(email);
            if (matcher.matches()) {
                User user = null;
                if (!email.equals(userSession.getEmail())) {
                    user = service.findByEmail(email);
                }
                if (user == null) {
                    user = service.findByLogin(req.getParameter(LOGIN));
                    user = updateUser(user, req);
                    if (user == null) {
                        return new ProfileAction().perform(req, resp);
                    } else {
                        updateUserSession(user, userSession);
                    }
                } else {
                    req.setAttribute(EMAIL_EXISTS, EMAIL_EXISTS_MESSAGE);
                }
            } else {
                req.setAttribute(EMAIL_WRONG, EMAIL_WRONG_MESSAGE);
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
            user.setPassword(MD5Encoder.generateHash(newPassword));
        } else if (!newPassword.equals(confirmPassword)) {
            req.setAttribute(PASSWORDS_NOT_EQUALS, PASSWORDS_NOT_EQUALS_MESSAGE);
            return null;
        }

        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        service.updateUser(user);

        req.setAttribute(SAVE_USER, SAVE_USER_MESSAGE);
        return user;
    }

    private void updateUserSession(User user, UserSession userSession) {
        userSession.setLogin(user.getLogin());
        userSession.setEmail(user.getEmail());
        userSession.setFirstName(user.getFirstName());
        userSession.setLastName(user.getLastName());
    }
}
