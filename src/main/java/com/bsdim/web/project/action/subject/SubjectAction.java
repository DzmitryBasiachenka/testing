package com.bsdim.web.project.action.subject;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;

/**
 * The subject action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class SubjectAction implements IAction {
    private static final String SUBJECT_JSP = "subject.jsp";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        List<Subject> subjects = service.getSubjects();
        req.setAttribute("subjects", subjects);
        return SUBJECT_JSP;
    }
}
