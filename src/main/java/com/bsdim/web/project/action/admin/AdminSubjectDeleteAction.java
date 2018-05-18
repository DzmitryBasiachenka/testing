package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class AdminSubjectDeleteAction implements IAction {
    private static final String SUBJECT_DELETED = "subjectDeleted";
    private static final String SUBJECT_DELETED_MESSAGE = "The subject deleted";

    private static Logger sLogger = Logger.getLogger(AdminSubjectDeleteAction.class);

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int subjectId = Integer.parseInt(id);
            service.deleteSubject(subjectId);
            sLogger.info(String.format("The subject with id %1$s deleted", subjectId));
            req.setAttribute(SUBJECT_DELETED, SUBJECT_DELETED_MESSAGE);
        } else {
            sLogger.warn(String.format("'%1$s' does not match of id pattern of subject", id));
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
