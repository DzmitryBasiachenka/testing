package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.main.MainAction;
import com.bsdim.web.project.action.test.TestAction;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.MD5Encoder;
import org.apache.log4j.Logger;

public class LoginAction implements IAction {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_SESSION = "userSession";
    private static final String WRONG_USER = "Wrong login or password. Please, input correct data.";
    private static final String WRONG_USER_MESSAGE = "wrongUserMessage";

    private static Logger sLogger = Logger.getLogger(LoginAction.class);

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {

       HttpSession session = req.getSession(true);

        if (session.getAttribute(USER_SESSION) == null) {
            String login = req.getParameter(LOGIN);
            String password = req.getParameter(PASSWORD);

            password = MD5Encoder.generateHash(password);
            User user = service.findByLogin(login);

            if ((user != null) && (user.getPassword().equals(password))) {
                UserRole userRole = service.readUserRoleById(user.getId());
                UserSession userSession = createUserSession(userRole);
                session.setAttribute(USER_SESSION, userSession);
                sLogger.info(String.format("User session '%1$s' created", userSession.getLogin()));
            } else {
                sLogger.info("Wrong login or password");
                req.setAttribute(WRONG_USER_MESSAGE, WRONG_USER);
            }
        }
        return new MainAction().perform(req, resp);
    }

    private UserSession createUserSession(UserRole userRole) {
        UserSession userSession = new UserSession();
        userSession.setId(userRole.getId());
        userSession.setLogin(userRole.getLogin());
        userSession.setEmail(userRole.getEmail());
        userSession.setFirstName(userRole.getFirstName());
        userSession.setLastName(userRole.getLastName());
        userSession.setRoles(userRole.getRoles());
        return userSession;
    }
}
