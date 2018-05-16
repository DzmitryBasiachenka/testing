package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.ActionUtil;

public class AdminUserDeleteAction implements IAction {
    private static final String USER_DELETED = "userDeleted";
    private static final String USER_DELETED_MESSAGE = "The user deleted";

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());

        if (ActionUtil.isIdPattern(id)) {
            int userId = Integer.parseInt(id);
            service.deleteUser(userId);
            req.setAttribute(USER_DELETED, USER_DELETED_MESSAGE);
        }
        return new AdminUserListAction().perform(req, resp);
    }
}
