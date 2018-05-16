package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;

public class SecurityFilter implements Filter {
    private static final String ERROR_404_JSP = "error-404.jsp";
    private static final String USER_SESSION = "userSession";
    private static final String TEST_SESSION = "testSession";
    private static final String EXAMINATION_SESSION = "examinationSession";
    private static final char SLASH = '/';

    private Map<String, IAction> map;
    private Map<String, String> permissions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initMap();
        initPermissionsMap();
    }

    private void initMap() {
        map = new HashMap<>();
        map.put("/", new MainAction());
        map.put("/about", new AboutAction());
        map.put("/login", new LoginAction());
        map.put("/registration", new RegistrationAction());
        map.put("/user/add", new UserAddAction());
    }

    private void initPermissionsMap() {
        permissions = new HashMap<>();
        permissions.put("/", "User");
        permissions.put("/about", "User");
        permissions.put("/login", "User");
        permissions.put("/logout", "User");
        permissions.put("/profile", "User");
        permissions.put("/user/delete", "User");
        permissions.put("/user/edit", "User");

        permissions.put("/admin", "Admin");
        permissions.put("/admin/user/list", "Admin");
        permissions.put("/admin/user/delete", "Admin");
        permissions.put("/admin/subject/list", "Admin");

        permissions.put("/question", "Tutor");
        permissions.put("/question/edit", "Tutor");
        permissions.put("/question/new", "Tutor");
        permissions.put("/question/delete", "Tutor");
        permissions.put("/subject", "Tutor");
        permissions.put("/subject/add", "Tutor");
        permissions.put("/test", "Tutor");
        permissions.put("/test/add", "Tutor");
        permissions.put("/test/edit", "Tutor");
        permissions.put("/test/delete", "Tutor");
        permissions.put("/test/list", "Tutor");

        permissions.put("/examination", "Student");
        permissions.put("/examination/test", "Student");
        permissions.put("/examination/list", "Student");
        permissions.put("/examination/exit", "Student");
        permissions.put("/examination/question", "Student");
        permissions.put("/statistics/delete", "Student");
        permissions.put("/statistics/list", "Student");
    }

    //@SuppressWarnings("checkstyle:ReturnCount")
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession session = httpServletRequest.getSession(true);

        if(session.getAttribute(USER_SESSION) == null) {
            process(httpServletRequest, httpServletResponse);
        } else if (session.getAttribute(USER_SESSION) != null) {
            if ((session.getAttribute(TEST_SESSION) != null)|| (session.getAttribute(EXAMINATION_SESSION) != null)) {
                chain.doFilter(req, resp);
                return;
            }
            UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);
            List<Role> roles = userSession.getRoles();
            String servletPath = httpServletRequest.getServletPath();
            String roleName = findRole(servletPath);
            if (roleName != null) {
                for (Role role : roles) {
                    if (roleName.equals(role.getRoleName())) {
                        chain.doFilter(req, resp);
                        return;
                    }
                }
            }
            httpServletRequest.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        IAction action = ActionUtil.findAction(servletPath, map);

        String jspName = ERROR_404_JSP;

        if (action != null) {
            jspName = action.perform(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/view/" + jspName).forward(req, resp);
    }

    /*private IAction findAction(String servletPath) {
        IAction action = map.get(servletPath);
        if (action == null) {
            return null;
        } else {
            return action;
        }
    }*/

    private String findRole(String servletPath) {
        while (!servletPath.isEmpty()) {
            String roleName = permissions.get(servletPath);
            if (roleName == null) {
                int index = servletPath.lastIndexOf(SLASH, servletPath.length());
                servletPath = servletPath.substring(0, index);
            } else {
                return roleName;
            }
        }
        return null;
    }

    @Override
    public void destroy() {
    }
}
