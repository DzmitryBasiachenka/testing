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

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.LoginAction;
import com.bsdim.web.project.action.about.AboutAction;
import com.bsdim.web.project.action.main.MainAction;
import com.bsdim.web.project.action.registration.RegistrationAction;
import com.bsdim.web.project.action.registration.UserAddAction;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

/**
 * The security filter.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class SecurityFilter implements Filter {
    private static final String ERROR_404_JSP = "error-404.jsp";
    private static final String USER_SESSION = "userSession";
    private static final String TEST_SESSION = "testSession";
    private static final String EXAMINATION_SESSION = "examinationSession";
    private static final String USER_ROLE = "User";
    private static final String ADMIN_ROLE = "Admin";
    private static final String TUTOR_TUTOR = "Tutor";
    private static final String STUDENT_ROLE = "Student";
    private static final char SLASH = '/';

    private static Logger sLogger = Logger.getLogger(SecurityFilter.class);

    private Map<String, IAction> map;
    private Map<String, String> permissions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sLogger.info("Init security filter");
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
        permissions.put("/", USER_ROLE);
        permissions.put("/about", USER_ROLE);
        permissions.put("/login", USER_ROLE);
        permissions.put("/logout", USER_ROLE);
        permissions.put("/profile", USER_ROLE);
        permissions.put("/user/delete", USER_ROLE);
        permissions.put("/user/edit", USER_ROLE);

        permissions.put("/admin", ADMIN_ROLE);
        permissions.put("/admin/role/add", ADMIN_ROLE);
        permissions.put("/admin/role/delete", ADMIN_ROLE);
        permissions.put("/admin/user", ADMIN_ROLE);
        permissions.put("/admin/user/list", ADMIN_ROLE);
        permissions.put("/admin/user/delete", ADMIN_ROLE);
        permissions.put("/admin/subject", ADMIN_ROLE);
        permissions.put("/admin/subject/list", ADMIN_ROLE);
        permissions.put("/admin/subject/edit", ADMIN_ROLE);
        permissions.put("/admin/subject/delete", ADMIN_ROLE);

        permissions.put("/question", TUTOR_TUTOR);
        permissions.put("/question/edit", TUTOR_TUTOR);
        permissions.put("/question/new", TUTOR_TUTOR);
        permissions.put("/question/delete", TUTOR_TUTOR);
        permissions.put("/test/statistics", TUTOR_TUTOR);
        permissions.put("/statistics/delete", TUTOR_TUTOR);
        permissions.put("/subject", TUTOR_TUTOR);
        permissions.put("/subject/add", TUTOR_TUTOR);
        permissions.put("/test", TUTOR_TUTOR);
        permissions.put("/test/add", TUTOR_TUTOR);
        permissions.put("/test/edit", TUTOR_TUTOR);
        permissions.put("/test/delete", TUTOR_TUTOR);
        permissions.put("/test/list", TUTOR_TUTOR);

        permissions.put("/examination", STUDENT_ROLE);
        permissions.put("/examination/test", STUDENT_ROLE);
        permissions.put("/examination/list", STUDENT_ROLE);
        permissions.put("/examination/exit", STUDENT_ROLE);
        permissions.put("/examination/question", STUDENT_ROLE);
        permissions.put("/statistics/list", STUDENT_ROLE);
    }

    @SuppressWarnings("checkstyle:ReturnCount")
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession session = httpServletRequest.getSession(true);

        if (session.getAttribute(USER_SESSION) == null) {
            process(httpServletRequest, httpServletResponse);
        } else if (session.getAttribute(USER_SESSION) != null) {
            if ((session.getAttribute(TEST_SESSION) != null) || (session.getAttribute(EXAMINATION_SESSION) != null)) {
                chain.doFilter(req, resp);
                return;
            }
            UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);
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
                sLogger.info(String.format("%1$s to the %2$s is denied!", userSession.getLogin(), servletPath));
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
