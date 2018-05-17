package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;

public class AdminSubjectEditAction implements IAction {
    private static final String SUBJECT_UPDATED = "subjectUpdated";
    private static final String SUBJECT_UPDATED_MESSAGE = "The subject updated";
    private static final String SUBJECT_EMPTY = "subjectEmpty";
    private static final String SUBJECT_EMPTY_MESSAGE = "The field of subject name should not be empty";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String subjectInput = req.getParameter("subjectInput");
        String subjectCurrentName = req.getParameter("subjectCurrentName");

        if (WebUtil.isNotBlank(subjectInput, subjectCurrentName)) {
            if (!subjectInput.equals(subjectCurrentName)) {
                String id = ActionUtil.getIdFromServletPath(req.getServletPath());
                if (ActionUtil.isIdPattern(id)) {
                    int subjectId = Integer.parseInt(id);
                    Subject subject = new Subject();
                    subject.setId(subjectId);
                    subject.setSubjectName(subjectInput);
                    service.updateSubject(subject);
                    req.setAttribute(SUBJECT_UPDATED, SUBJECT_UPDATED_MESSAGE);
                }
            }
        } else {
            req.setAttribute(SUBJECT_EMPTY, SUBJECT_EMPTY_MESSAGE);
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
