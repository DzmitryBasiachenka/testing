package com.bsdim.web.project.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.AnswerService;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;

public class TestAction implements IAction {
    private static final String TEST_JSP = "test.jsp";

    private SubjectService subjectService = new SubjectService();
    //private QuestionService questionService = new QuestionService();
    private TestService testService = new TestService();
    //private AnswerService answerService = new AnswerService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        Integer userId = userSession.getId();

        List<Test> tests = testService.findTestByUserId(userId);
        req.setAttribute("tests", tests);

        List<Subject> subjects = subjectService.getSubjects();
        req.setAttribute("subjects", subjects);
        return TEST_JSP;
    }
}
