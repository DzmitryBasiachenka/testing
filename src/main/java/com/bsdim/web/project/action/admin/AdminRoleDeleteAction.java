package com.bsdim.web.project.action.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class AdminRoleDeleteAction implements IAction {
    private static final String ROLE_DELETED = "roleDeleted";
    private static final String ROLE_DELETED_MESSAGE = "t.role.deleted.message";

    private static Logger sLogger = Logger.getLogger(AdminRoleDeleteAction.class);

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int roleId = Integer.parseInt(id);
            User user = service.findByLogin(login);
            if (user != null) {
                service.deleteUserRole(createUserRole(user, roleId));
                sLogger.info(String.format("For %1$s role with id %2$s deleted ", user.getLogin(), roleId));
                req.setAttribute(ROLE_DELETED, ROLE_DELETED_MESSAGE);
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of role", id));
        }
        return new AdminUserListAction().perform(req, resp);
    }

    private UserRole createUserRole(User user, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setId(user.getId());
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(roleId);
        roles.add(role);
        userRole.setRoles(roles);
        return userRole;
    }
}
