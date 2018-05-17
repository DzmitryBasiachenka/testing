package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;

public class AdminSubjectDeleteAction implements IAction {
    private static final String SUBJECT_DELETED = "subjectDeleted";
    private static final String SUBJECT_DELETED_MESSAGE = "The subject deleted";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int subjectId = Integer.parseInt(id);
            service.deleteSubject(subjectId);
            req.setAttribute(SUBJECT_DELETED, SUBJECT_DELETED_MESSAGE);
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
