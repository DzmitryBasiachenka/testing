package com.bsdim.web.project.action.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.RoleService;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class AdminRoleAddAction implements IAction {
    private static final String ROLE_ADDED = "roleAdded";
    private static final String ROLE_ADDED_MESSAGE = "The role added";
    private static final String ROLE_EXISTS = "roleExists";
    private static final String ROLE_EXISTS_MESSAGE = "The user has the role";

    private static Logger sLogger = Logger.getLogger(AdminRoleAddAction.class);

    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("userId");
        String roleSelect = req.getParameter("roleSelect");

        if (ActionUtil.isIdPattern(id)) {
            int userId = Integer.parseInt(id);

            UserRole user = userService.readUserRoleById(userId);
            if (user != null) {
                Role role = roleService.findRoleByRoleName(roleSelect);

                List<Role> roles = user.getRoles();
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i).getId().equals(role.getId())) {
                        req.setAttribute(ROLE_EXISTS, ROLE_EXISTS_MESSAGE);
                        sLogger.info(String.format("%1$s has the %2$s role", user.getLogin(), role.getRoleName()));
                        return new AdminUserListAction().perform(req, resp);
                    } else if (i == roles.size() - 1) {
                        List<Role> roleList = new ArrayList<>();
                        roleList.add(role);
                        user.setRoles(roleList);
                        userService.createUserRoles(user);
                        sLogger.info(String.format("Role '%1$s' added for %2$s", role.getRoleName(), user.getLogin()));
                        req.setAttribute(ROLE_ADDED, ROLE_ADDED_MESSAGE);
                    }
                }
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of role", id));
        }
        return new AdminUserListAction().perform(req, resp);
    }
}
