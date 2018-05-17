package com.bsdim.web.project.action.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.UserService;

public class AdminUserListAction implements IAction {
    private static final String ADMIN_USER_LIST_JSP = "admin-user-list.jsp";
    private static final String USERS = "users";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        List<UserRole> userRoles = service.getUsers();
        req.setAttribute(USERS, userRoles);
        return ADMIN_USER_LIST_JSP;
    }
}
