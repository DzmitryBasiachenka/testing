package com.bsdim.web.project.action.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

/**
 * The main action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class MainAction implements IAction {
    private static final String MAIN_JSP = "main.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return MAIN_JSP;
    }
}
