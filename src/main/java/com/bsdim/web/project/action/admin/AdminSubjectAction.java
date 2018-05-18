package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class AdminSubjectAction implements IAction {
    private static final String ADMIN_SUBJECT_JSP = "admin-subject.jsp";

    private static Logger sLogger = Logger.getLogger(AdminSubjectAction.class);

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int subjectId = Integer.parseInt(id);
            Subject subject = service.findById(subjectId);
            req.setAttribute("subject", subject);
            return ADMIN_SUBJECT_JSP;
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of subject", id));
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
