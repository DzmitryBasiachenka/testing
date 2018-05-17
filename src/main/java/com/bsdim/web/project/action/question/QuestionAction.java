package com.bsdim.web.project.action.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;

public class QuestionAction implements IAction {
    private static final String QUESTION_JSP = "question.jsp";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        return QUESTION_JSP;
    }
}
