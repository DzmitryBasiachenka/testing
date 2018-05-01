package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.WebUtil;

public class UserAddAction implements IAction {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String SAVE_USER = "saveUser";
    private static final String SAVE_USER_MESSAGE = "Data saved";
    private static final String LOGIN_EXISTS = "loginExists";
    private static final String LOGIN_EXISTS_MESSAGE = "The user with such login exists";
    private static final String EMAIL_EXISTS = "emailExists";
    private static final String EMAIL_EXISTS_MESSAGE = "The email already exists";
    private static final String PASSWORDS_NOT_EQUALS = "passwordsNotEquals";
    private static final String PASSWORDS_NOT_EQUALS_MESSAGE = "Password fields do not equals";
    private static final String EMPTY_USER = "emptyUser";
    private static final String EMPTY_USER_MESSAGE = "The all fields of user form should not be empty";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
       addUser(req);
       return new RegistrationAction().perform(req, resp);
    }

    private void addUser(HttpServletRequest req) {
        String login = req.getParameter(LOGIN);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD);
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);

        if (WebUtil.isNotBlank(login, email, password, confirmPassword, firstName, lastName)) {
            if (password.equals(confirmPassword)) {
                User user = service.findByLogin(login);
                if (user == null) {
                    user = service.findByEmail(email);
                    if (user == null) {
                        user = new User();
                        user.setLogin(login.trim());
                        user.setPassword(password.trim());
                        user.setEmail(email.trim());
                        user.setFirstName(firstName.trim());
                        user.setLastName(lastName.trim());

                        service.addUser(user);
                        req.setAttribute(SAVE_USER, SAVE_USER_MESSAGE);
                    } else {
                        req.setAttribute(EMAIL_EXISTS, EMAIL_EXISTS_MESSAGE);
                    }
                } else {
                    req.setAttribute(LOGIN_EXISTS, LOGIN_EXISTS_MESSAGE);
                }
            } else {
                req.setAttribute(PASSWORDS_NOT_EQUALS, PASSWORDS_NOT_EQUALS_MESSAGE);
            }
        } else {
            req.setAttribute(EMPTY_USER, EMPTY_USER_MESSAGE);
        }
    }
}
