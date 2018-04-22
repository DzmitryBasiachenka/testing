package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatisticsAction implements IAction {
    private static final String STATISTICS_JSP = "statistics.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return STATISTICS_JSP;
    }
}
