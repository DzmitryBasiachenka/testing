package com.bsdim.web.project.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.session.TestSession;
import org.apache.log4j.Logger;

public class TestExitAction implements IAction {
    private static final String TEST_SESSION = "testSession";

    private static Logger sLogger = Logger.getLogger(TestExitAction.class);

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        TestSession testSession = (TestSession) session.getAttribute(TEST_SESSION);
        if (testSession != null) {
            session.removeAttribute(TEST_SESSION);
            sLogger.info("Test session removed");
        }
        return new TestListAction().perform(req, resp);
    }
}
