package com.bsdim.web.project.action.subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.profile.UserEditAction;
import com.bsdim.web.project.action.subject.SubjectAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

public class SubjectAddAction implements IAction {
    private static final String SUBJECT_NAME = "subjectName";
    private static final String SUBJECT_EMPTY = "subjectEmpty";
    private static final String SUBJECT_EMPTY_MESSAGE = "The subject name should not be empty";
    private static final String SUBJECT_EXISTS = "subjectExists";
    private static final String SUBJECT_EXISTS_MESSAGE = "The subject already exists";

    private static Logger sLogger = Logger.getLogger(SubjectAddAction.class);

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String subjectName = req.getParameter(SUBJECT_NAME);

        if (WebUtil.isNotBlank(subjectName)) {
            Subject subject = service.findSubjectByName(subjectName);
            if (subject == null) {
                    subject = new Subject();
                    subject.setSubjectName(ActionUtil.replaceExtraSpaces(subjectName.trim()));
                    service.addSubject(subject);
                    sLogger.info(String.format("Subject '%1$s' added", subject.getSubjectName()));
            } else {
                req.setAttribute(SUBJECT_EXISTS, SUBJECT_EXISTS_MESSAGE);
            }
        } else {
            sLogger.warn(SUBJECT_EMPTY_MESSAGE);
            req.setAttribute(SUBJECT_EMPTY, SUBJECT_EMPTY_MESSAGE);
        }
        return new SubjectAction().perform(req, resp);
    }
}