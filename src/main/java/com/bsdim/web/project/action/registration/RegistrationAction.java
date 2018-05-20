package com.bsdim.web.project.action.registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

/**
 * The registration action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class RegistrationAction implements IAction {
    private static final String REGISTRATION_JSP = "registration.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return REGISTRATION_JSP;
    }
}
