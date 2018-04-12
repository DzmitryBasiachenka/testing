package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.AuthorizationAction;
import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.RegistrationAction;
import com.bsdim.web.project.action.WelcomeAction;

public class SecurityFilter implements Filter {
/*    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER_SESSION = "userSession";
    private static final String LOGIN_PAGE = "/WEB-INF/view/login_page.jsp";
    private static final String WRONG_USER = "Wrong login or password. Please, input correct data.";
    private static final String WRONG_USER_MESSAGE = "wrongUserMessage";
    private UserService service = new UserService();*/

    private Map<String, IAction> map;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initMap();
    }

    private void initMap() {
        map = new HashMap<>();
        map.put("/", new WelcomeAction());
        map.put("/login", new AuthorizationAction());
        map.put("/registration", new RegistrationAction());
    }

    //@SuppressWarnings("checkstyle:ReturnCount")
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        /*HttpSession session = ((HttpServletRequest) request).getSession(true);

        if (session.getAttribute(USER_SESSION) == null) {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);

            if (!WebUtil.isNotBlank(login, password)) {
                forwardLoginForm(request, response);
                return;
            }
            User user = service.findByLogin(login);

            if ((user != null) && (user.getPassword().equals(password))) {
                UserSession userSession = createUserSession(user);
                session.setAttribute(USER_SESSION, userSession);
            } else {
                request.setAttribute(WRONG_USER_MESSAGE, WRONG_USER);
                forwardLoginForm(request, response);
                return;
            }
        }*/
        chain.doFilter(req, resp);
    }

    /*private UserSession createUserSession(User user) {
        UserSession userSession = new UserSession();
        userSession.setId(user.getId());
        userSession.setLogin(user.getLogin());
        userSession.setName(user.getName());
        return userSession;
    }*/

    private void forwardLoginForm(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        //request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
