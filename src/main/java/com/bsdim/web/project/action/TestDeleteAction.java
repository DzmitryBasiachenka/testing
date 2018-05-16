package com.bsdim.web.project.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;

public class TestDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String TEST_DELETED = "testDeleted";
    private static final String TEST_DELETED_MESSAGE = "The test deleted";

    private TestService service = new TestService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int testId = Integer.parseInt(id);
            List<Test> tests = service.findTestsByUserId(userSession.getId());
            for (Test test : tests) {
                if (test.getId() == testId) {
                    service.deleteTest(testId);
                    req.setAttribute(TEST_DELETED, TEST_DELETED_MESSAGE);
                    break;
                }
            }
        }
        return new TestListAction().perform(req, resp);
    }
}
