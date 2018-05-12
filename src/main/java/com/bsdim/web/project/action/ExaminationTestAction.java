package com.bsdim.web.project.action;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.ExaminationSession;

public class ExaminationTestAction implements IAction {
    private static final String EXAMIANTION_SESSION = "examinationSession";
    private static final char SLASH = '/';

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ExaminationSession examinationSession = (ExaminationSession) session.getAttribute(EXAMIANTION_SESSION);

        if (examinationSession == null) {
            String servletPath = req.getServletPath();
            int index = servletPath.lastIndexOf(SLASH, servletPath.length());
            int testId = Integer.parseInt(servletPath.substring(index + 1));

            Test test = testService.findById(testId);
            if (test != null) {
                List<Integer> idQuestions = questionService.getIdQuestionsByTestId(test.getId());

                examinationSession = new ExaminationSession();
                examinationSession.setTestId(testId);
                examinationSession.setTestName(test.getTestName());
                examinationSession.setIdQuestions(idQuestions);
                examinationSession.setStartTesting(new Timestamp(System.currentTimeMillis()));

                session.setAttribute(EXAMIANTION_SESSION, examinationSession);
            }
        }
        return new ExaminationQuestionAction().perform(req, resp);
    }
}
