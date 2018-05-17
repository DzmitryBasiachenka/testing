package com.bsdim.web.project.action.examination;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.service.TestService;

public class ExaminationListAction implements IAction {
    private static final String EXAMINATION_LIST_JSP = "examination-list.jsp";
    private static final String SUBJECT_SELECT = "subjectSelect";
    private static final String SUBJECT_NAME = "subjectName";
    private static final String TESTS_BY_SUBJECT_NAME = "testsBySubjectName";

    private SubjectService subjectService = new SubjectService();
    private TestService testService = new TestService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String subjectSelect = req.getParameter(SUBJECT_SELECT);
        Subject subject = subjectService.findSubjectByName(subjectSelect);

        if (subject != null) {
            List<Test> testsBySubjectName = testService.findTestsBySubjectName(subject.getSubjectName());
            req.setAttribute(SUBJECT_NAME, subject.getSubjectName());
            req.setAttribute(TESTS_BY_SUBJECT_NAME, testsBySubjectName);
        }
        return EXAMINATION_LIST_JSP;
    }
}
