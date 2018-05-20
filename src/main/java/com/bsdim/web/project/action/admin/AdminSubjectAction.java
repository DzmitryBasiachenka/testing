package com.bsdim.web.project.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

/**
 * The admin subject action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class AdminSubjectAction implements IAction {
    private static final String ADMIN_SUBJECT_JSP = "admin-subject.jsp";

    private static Logger sLogger = Logger.getLogger(AdminSubjectAction.class);

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String subjectIdParameter = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(subjectIdParameter)) {
            int subjectId = Integer.parseInt(subjectIdParameter);
            Subject subject = service.findById(subjectId);
            req.setAttribute("subject", subject);
            return ADMIN_SUBJECT_JSP;
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of subject", subjectIdParameter));
        }
        return new AdminSubjectListAction().perform(req, resp);
    }
}
