package com.bsdim.web.project.action.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

/**
 * The profile action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class ProfileAction implements IAction {
    private static final String PROFILE_JSP = "profile.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return PROFILE_JSP;
    }
}
