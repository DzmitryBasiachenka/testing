package com.bsdim.web.project.action.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.MD5Encoder;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

/**
 * The user add action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class UserAddAction implements IAction {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String DATA_SAVED = "dataSaved";
    private static final String DATA_SAVED_MESSAGE = "t.data.saved.message";
    private static final String LOGIN_EXISTS = "loginExists";
    private static final String LOGIN_EXISTS_MESSAGE = "t.login.exists.message";
    private static final String EMAIL_EXISTS = "emailExists";
    private static final String EMAIL_EXISTS_MESSAGE = "t.email.exists.message";
    private static final String EMAIL_WRONG = "emailWrong";
    private static final String EMAIL_WRONG_MESSAGE = "t.email.wrong.message";
    private static final String PASSWORDS_NOT_EQUALS = "passwordsNotEquals";
    private static final String PASSWORDS_NOT_EQUALS_MESSAGE = "t.password.not.equals.message";
    private static final String USER_EMPTY = "userEmpty";
    private static final String USER_EMPTY_MESSAGE = "t.user.empty.message";

    private static Logger sLogger = Logger.getLogger(UserAddAction.class);

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
            Pattern emailPattern = Pattern.compile("(\\w{3,}@(\\w+\\.)([a-z]{2,4}))");
            Matcher matcher = emailPattern.matcher(email);
            if (matcher.matches()) {
                if (password.equals(confirmPassword)) {
                    User user = service.findByLogin(login);
                    if (user == null) {
                        user = service.findByEmail(email);
                        if (user == null) {
                            user = new User();
                            user.setLogin(ActionUtil.replaceExtraSpaces(login.trim()));
                            user.setPassword(MD5Encoder.generateHash(ActionUtil.replaceExtraSpaces(password.trim())));
                            user.setEmail(email.trim());
                            user.setFirstName(ActionUtil.replaceExtraSpaces(firstName.trim()));
                            user.setLastName(ActionUtil.replaceExtraSpaces(lastName.trim()));

                            service.addUser(user);
                            sLogger.info(String.format("User '%1$s' added", user.getLogin()));
                            req.setAttribute(DATA_SAVED, DATA_SAVED_MESSAGE);
                        } else {
                            sLogger.info("The email exists");
                            req.setAttribute(EMAIL_EXISTS, EMAIL_EXISTS_MESSAGE);
                        }
                    } else {
                        sLogger.info("The login exists");
                        req.setAttribute(LOGIN_EXISTS, LOGIN_EXISTS_MESSAGE);
                    }
                } else {
                    sLogger.info("The passwords fields does not match");
                    req.setAttribute(PASSWORDS_NOT_EQUALS, PASSWORDS_NOT_EQUALS_MESSAGE);
                }
            } else {
                sLogger.info(String.format("Email '%1$s' does not match email pattern", email));
                req.setAttribute(EMAIL_WRONG, EMAIL_WRONG_MESSAGE);
            }
        } else {
            sLogger.warn("The all fields of user form should not be empty");
            req.setAttribute(USER_EMPTY, USER_EMPTY_MESSAGE);
        }
    }
}
