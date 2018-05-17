package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;

public class AdminSubjectAction implements IAction {
    private static final String ADMIN_SUBJECT_JSP = "admin-subject.jsp";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int subjectId = Integer.parseInt(id);
            Subject subject = service.findById(subjectId);
            req.setAttribute("subject", subject);
            return ADMIN_SUBJECT_JSP;
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
