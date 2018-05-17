package com.bsdim.web.project.action.examination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.examination.ExaminationAction;
import com.bsdim.web.project.session.ExaminationSession;

public class ExaminationExitAction implements IAction {
    private static final String EXAMINATION_SESSION = "examinationSession";

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ExaminationSession examinationSession = (ExaminationSession) session.getAttribute(EXAMINATION_SESSION);
        if (examinationSession != null) {
            session.removeAttribute(EXAMINATION_SESSION);
        }
        return new ExaminationAction().perform(req, resp);
    }
}
