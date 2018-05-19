package com.bsdim.web.project.action.test;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class TestDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String TEST_DELETED = "testDeleted";
    private static final String TEST_DELETED_MESSAGE = "t.test.deleted.message";

    private static Logger sLogger = Logger.getLogger(TestDeleteAction.class);

    private TestService service = new TestService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int testId = Integer.parseInt(id);
            List<Test> tests = service.findTestsByUserId(userSession.getId());
            for (Test test : tests) {
                if (test.getId() == testId) {
                    service.deleteTest(testId);
                    sLogger.info(String.format("Test '%1$s' deleted", test.getTestName()));
                    req.setAttribute(TEST_DELETED, TEST_DELETED_MESSAGE);
                    break;
                }
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of test", id));
        }
        return new TestListAction().perform(req, resp);
    }
}
