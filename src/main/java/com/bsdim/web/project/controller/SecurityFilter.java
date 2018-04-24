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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.AboutAction;
import com.bsdim.web.project.action.LoginAction;
import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.RegistrationAction;
import com.bsdim.web.project.action.MainAction;
import com.bsdim.web.project.action.UserAddAction;

public class SecurityFilter implements Filter {
    private static final String ERROR_404_JSP = "error-404.jsp";
    private static final char SLASH = '/';

    private Map<String, IAction> map;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initMap();
    }

    private void initMap() {
        map = new HashMap<>();
        map.put("/", new MainAction());
        map.put("/about", new AboutAction());
        map.put("/login", new LoginAction());
        map.put("/registration", new RegistrationAction());
        map.put("/user/add", new UserAddAction());
        //map.put("/logout", new LogoutAction());
    }

    //@SuppressWarnings("checkstyle:ReturnCount")
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession session = httpServletRequest.getSession(true);

        if(session.getAttribute("userSession") == null) {
            process(httpServletRequest, httpServletResponse);
        } else {
            chain.doFilter(req, resp);
        }
    }

    //TO DO. Form common class for Filter and Servlet to extract of actions
    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        IAction action = findAction(servletPath);

        String jspName = ERROR_404_JSP;

        if (action != null) {
            jspName = action.perform(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/view/" + jspName).forward(req, resp);
    }

    private IAction findAction(String servletPath) {
        IAction action = map.get(servletPath);
        if (action == null) {
            return null;
        } else {
            return action;
        }
    }

    @Override
    public void destroy() {
    }
}
