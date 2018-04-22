package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExaminationAction implements IAction {
    private static final String EXAMINATION_JSP = "examination.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return EXAMINATION_JSP;
    }
}
