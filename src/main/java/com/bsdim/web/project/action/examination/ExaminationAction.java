package com.bsdim.web.project.action.examination;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.service.SubjectService;

/**
 * The abstract entity.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class ExaminationAction implements IAction {
    private static final String EXAMINATION_JSP = "examination.jsp";
    private static final String SUBJECTS = "subjects";

    private SubjectService service = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        List<Subject> subjects = service.getSubjects();
        req.setAttribute(SUBJECTS, subjects);
        return EXAMINATION_JSP;
    }
}
