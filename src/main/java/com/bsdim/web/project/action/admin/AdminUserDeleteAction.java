package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class AdminUserDeleteAction implements IAction {
    private static final String USER_DELETED = "userDeleted";
    private static final String USER_DELETED_MESSAGE = "The user deleted";

    private static Logger sLogger = Logger.getLogger(AdminUserDeleteAction.class);

    private UserService service = new UserService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());

        if (ActionUtil.isIdPattern(id)) {
            int userId = Integer.parseInt(id);
            service.deleteUser(userId);
            sLogger.info(String.format("User with id %1$s deleted", userId));
            req.setAttribute(USER_DELETED, USER_DELETED_MESSAGE);
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of user", id));
        }
        return new AdminUserListAction().perform(req, resp);
    }
}
