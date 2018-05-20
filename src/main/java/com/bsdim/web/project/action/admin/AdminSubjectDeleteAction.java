package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

/**
 * The admin subject delete action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class AdminSubjectDeleteAction implements IAction {
    private static final String SUBJECT_DELETED = "subjectDeleted";
    private static final String SUBJECT_DELETED_MESSAGE = "t.subject.deleted.message";

    private static Logger sLogger = Logger.getLogger(AdminSubjectDeleteAction.class);

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String subjectIdParameter = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(subjectIdParameter)) {
            int subjectId = Integer.parseInt(subjectIdParameter);
            service.deleteSubject(subjectId);
            sLogger.info(String.format("The subject with id %1$s deleted", subjectId));
            req.setAttribute(SUBJECT_DELETED, SUBJECT_DELETED_MESSAGE);
        } else {
            sLogger.warn(String.format("'%1$s' does not match of id pattern of subject", subjectIdParameter));
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
