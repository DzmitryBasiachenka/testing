package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

public class AdminSubjectEditAction implements IAction {
    private static final String SUBJECT_UPDATED = "subjectUpdated";
    private static final String SUBJECT_UPDATED_MESSAGE = "t.subject.updated.message";
    private static final String SUBJECT_EMPTY = "subjectEmpty";
    private static final String SUBJECT_EMPTY_MESSAGE = "t.subject.empty.message";

    private static Logger sLogger = Logger.getLogger(AdminSubjectEditAction.class);

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
                    sLogger.info("Subject updated");
                    req.setAttribute(SUBJECT_UPDATED, SUBJECT_UPDATED_MESSAGE);
                } else {
                    sLogger.warn(String.format("'%1$s' does not match id pattern of subject", id));
                }
            }
        } else {
            sLogger.warn(String.format("'%1$s' is not correct", subjectInput));
            req.setAttribute(SUBJECT_EMPTY, SUBJECT_EMPTY_MESSAGE);
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
