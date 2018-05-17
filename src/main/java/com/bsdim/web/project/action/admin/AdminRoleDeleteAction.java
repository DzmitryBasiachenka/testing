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

public class AdminRoleDeleteAction implements IAction {
    private static final String ROLE_DELETED = "roleDeleted";
    private static final String ROLE_DELETED_MESSAGE = "The role deleted";

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
                req.setAttribute(ROLE_DELETED, ROLE_DELETED_MESSAGE);
            }
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
