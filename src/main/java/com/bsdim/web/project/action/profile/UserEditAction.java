package com.bsdim.web.project.action.profile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.MD5Encoder;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

public class UserEditAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String DATA_SAVED = "dataSaved";
    private static final String DATA_SAVED_MESSAGE = "t.data.saved.message";
    private static final String EMAIL_EXISTS = "emailExists";
    private static final String EMAIL_EXISTS_MESSAGE = "t.email.exists.message";
    private static final String EMAIL_WRONG = "emailWrong";
    private static final String EMAIL_WRONG_MESSAGE = "t.email.wrong.message";
    private static final String PASSWORDS_NOT_EQUALS = "passwordsNotEquals";
    private static final String PASSWORDS_NOT_EQUALS_MESSAGE = "t.password.not.equals.message";
    private static final String USER_EMPTY = "userEmpty";
    private static final String USER_EMPTY_MESSAGE = "t.user.empty.message";

    private static Logger sLogger = Logger.getLogger(UserEditAction.class);

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
                        sLogger.info(String.format("User session of %1$s updated", userSession.getLogin()));
                    }
                } else {
                    sLogger.info("The email exists");
                    req.setAttribute(EMAIL_EXISTS, EMAIL_EXISTS_MESSAGE);
                }
            } else {
                sLogger.info(String.format("Email '%1$s' does not match email pattern", emailPattern));
                req.setAttribute(EMAIL_WRONG, EMAIL_WRONG_MESSAGE);
            }
        } else {
            sLogger.warn(String.format("'%1$s', '%2$s', '%3$s' are not correct", email, firstName, lastName));
            req.setAttribute(USER_EMPTY, USER_EMPTY_MESSAGE);
        }
        return new ProfileAction().perform(req, resp);
    }

    private User updateUser(User user, HttpServletRequest req) {
        String newPassword  = req.getParameter(NEW_PASSWORD);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD);

        if (WebUtil.isNotBlank(newPassword, confirmPassword) & newPassword.equals(confirmPassword)) {
            user.setPassword(MD5Encoder.generateHash(ActionUtil.replaceExtraSpaces(newPassword.trim())));
            sLogger.info(String.format("Password of '%1$s' updated", user.getLogin()));
        } else if (!newPassword.equals(confirmPassword)) {
            req.setAttribute(PASSWORDS_NOT_EQUALS, PASSWORDS_NOT_EQUALS_MESSAGE);
            sLogger.info("Fields of passwords does not match");
            return null;
        }

        user.setEmail(email);
        user.setFirstName(ActionUtil.replaceExtraSpaces(firstName.trim()));
        user.setLastName(ActionUtil.replaceExtraSpaces(lastName.trim()));
        service.updateUser(user);
        sLogger.info(String.format("User '%1$s' updated", user.getLogin()));

        req.setAttribute(DATA_SAVED, DATA_SAVED_MESSAGE);
        return user;
    }

    private void updateUserSession(User user, UserSession userSession) {
        userSession.setLogin(user.getLogin());
        userSession.setEmail(user.getEmail());
        userSession.setFirstName(user.getFirstName());
        userSession.setLastName(user.getLastName());
    }
}
