package com.bsdim.web.project.action.test;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;

/**
 * The test list action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class TestListAction implements IAction {
    private static final String TEST_LIST_JSP = "test-list.jsp";

    private TestService testService = new TestService();
    private SubjectService subjectService = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        Integer userId = userSession.getId();

        List<Test> tests = testService.findTestsByUserId(userId);
        req.setAttribute("tests", tests);

        List<Subject> subjects = subjectService.getSubjects();
        req.setAttribute("subjects", subjects);

        return TEST_LIST_JSP;
    }
}
