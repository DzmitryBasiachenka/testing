package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

/**
 * The admin action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class AdminAction implements IAction {
    private static final String ADMIN_JSP = "admin.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return ADMIN_JSP;
    }
}
