package com.bsdim.web.project.action.examination;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.ExaminationSession;
import com.bsdim.web.project.util.ActionUtil;
import org.apache.log4j.Logger;

public class ExaminationTestAction implements IAction {
    private static final String EXAMIANTION_SESSION = "examinationSession";

    private static Logger sLogger = Logger.getLogger(ExaminationTestAction.class);

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ExaminationSession examinationSession = (ExaminationSession) session.getAttribute(EXAMIANTION_SESSION);

        if (examinationSession == null) {
            String id = ActionUtil.getIdFromServletPath(req.getServletPath());
            if (ActionUtil.isIdPattern(id)) {
                int testId = Integer.parseInt(id);
                Test test = testService.findById(testId);
                if (test != null) {
                    List<Integer> idQuestions = questionService.getIdQuestionsByTestId(test.getId());

                    examinationSession = new ExaminationSession();
                    examinationSession.setTestId(testId);
                    examinationSession.setTestName(test.getTestName());
                    examinationSession.setIdQuestions(idQuestions);
                    examinationSession.setStartTesting(new Timestamp(System.currentTimeMillis()));

                    session.setAttribute(EXAMIANTION_SESSION, examinationSession);
                    sLogger.info("Examination session created");
                }
            } else {
                sLogger.warn(String.format("'%1$s' does not match of id pattern of test", id));
            }
        }
        return new ExaminationQuestionAction().perform(req, resp);
    }
}
