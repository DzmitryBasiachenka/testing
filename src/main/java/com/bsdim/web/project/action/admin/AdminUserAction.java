package com.bsdim.web.project.action.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.RoleService;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class AdminUserAction implements IAction {
    private static final String ADMIN_USER_JSP = "admin-user.jsp";

    private static Logger sLogger = Logger.getLogger(AdminUserAction.class);

    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int userId = Integer.parseInt(id);
            UserRole userRole = userService.readUserRoleById(userId);
            if (userRole != null) {
                List<Role> roles = roleService.getRoles();
                req.setAttribute("user", userRole);
                req.setAttribute("roles", roles);
                return ADMIN_USER_JSP;
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of user", id));
        }
        return new AdminUserListAction().perform(req, resp);
    }
}