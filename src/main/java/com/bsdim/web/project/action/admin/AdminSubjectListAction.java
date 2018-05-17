package com.bsdim.web.project.action.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;

public class AdminSubjectListAction implements IAction {
    private static final String ADMIN_SUBJECT_LIST_JSP = "admin-subject-list.jsp";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        List<Subject> subjects = service.getSubjects();
        req.setAttribute("subjects", subjects);
        return ADMIN_SUBJECT_LIST_JSP;
    }
}
