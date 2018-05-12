package com.bsdim.web.project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.util.WebUtil;

public class SubjectAddAction implements IAction {
    private static final String SUBJECT_NAME = "subjectName";
    private static final String SUBJECT_EMPTY = "subjectEmpty";
    private static final String SUBJECT_EMPTY_MESSAGE = "The subject name should not be empty";
    private static final String SUBJECT_EXISTS = "subjectExists";
    private static final String SUBJECT_EXISTS_MESSAGE = "The subject already exists";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String subjectName = req.getParameter(SUBJECT_NAME);

        if (WebUtil.isNotBlank(subjectName)) {
            Subject subject = service.findSubjectByName(subjectName);
            if (subject == null) {
                    subject = new Subject();
                    subject.setSubjectName(subjectName);
                    service.addSubject(subject);
            } else {
                req.setAttribute(SUBJECT_EXISTS, SUBJECT_EXISTS_MESSAGE);
            }
        } else {
            req.setAttribute(SUBJECT_EMPTY, SUBJECT_EMPTY_MESSAGE);
        }
        return new SubjectAction().perform(req, resp);
    }
}
