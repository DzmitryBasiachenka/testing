package com.bsdim.web.project.action.about;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

/**
 * The about action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class AboutAction implements IAction {
    private static final String ABOUT_JSP = "about.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return ABOUT_JSP;
    }
}
