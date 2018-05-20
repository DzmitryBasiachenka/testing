package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Represents interface for actions.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface IAction {
    /**
     * Performs action.
     *
     * @param req the servlet request.
     * @param resp the servlet response.
     * @return jsp name.
     */
    String perform(HttpServletRequest req, HttpServletResponse resp);
}
