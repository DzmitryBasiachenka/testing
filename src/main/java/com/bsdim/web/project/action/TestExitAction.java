package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.session.TestSession;

public class TestExitAction implements IAction {
    private static final String TEST_SESSION = "testSession";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        TestSession testSession = (TestSession) session.getAttribute(TEST_SESSION);
        if (testSession != null) {
            session.removeAttribute(TEST_SESSION);
        }
        return new TestListAction().perform(req, resp);
    }
}
