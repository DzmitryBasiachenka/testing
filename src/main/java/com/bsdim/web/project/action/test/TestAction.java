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
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class TestAction implements IAction {
    private static final String TEST_JSP = "test.jsp";
    private static final String USER_SESSION = "userSession";

    private static Logger sLogger = Logger.getLogger(TestAction.class);

    private TestService testService = new TestService();
    private SubjectService subjectService = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int testId = Integer.parseInt(id);
            List<Test> tests = testService.findTestsByUserId(userSession.getId());
            if (tests != null) {
                for (Test test : tests) {
                    if (test.getId() == testId) {
                        req.setAttribute("test", test);

                        List<Subject> subjects = subjectService.getSubjects();
                        req.setAttribute("subjects", subjects);
                        return TEST_JSP;
                    }
                }
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of test", id));
        }
        return new TestListAction().perform(req, resp);
    }
}
