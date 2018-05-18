package com.bsdim.web.project.action.test;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.question.QuestionAction;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

public class TestEditAction implements IAction {
    private static final String TEST_EDITED = "testEdited";
    private static final String TEST_EDITED_MESSAGE = "The test edited";
    private static final String TEST_NAME_EMPTY = "testNameEmpty";
    private static final String TEST_NAME_EMPTY_MESSAGE = "The field of test name should not be empty";

    private static Logger sLogger = Logger.getLogger(TestEditAction.class);

    private TestService testService = new TestService();
    private SubjectService subjectService = new SubjectService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute("userSession");

        String testNameParameter = req.getParameter("testName");
        String subjectSelectParameter = req.getParameter("subjectSelect");
        String questionNameParameter = req.getParameter("questionSelect");

        if (WebUtil.isNotBlank(testNameParameter)) {
            String id = ActionUtil.getIdFromServletPath(req.getServletPath());
            if (ActionUtil.isIdPattern(id)) {
                int testId = Integer.parseInt(id);
                List<Test> tests = testService.findTestsByUserId(userSession.getId());
                if (tests != null) {
                    for (Test test : tests) {
                        if (test.getId() == testId) {
                            if (!test.getTestName().equals(testNameParameter) || !test.getSubject().getSubjectName().equals(subjectSelectParameter)) {
                                test.setTestName(testNameParameter);
                                if (!subjectSelectParameter.equals(test.getSubject().getSubjectName())) {
                                    Subject subject = subjectService.findSubjectByName(subjectSelectParameter);
                                    test.setSubject(subject);
                                }
                                testService.updateTest(test);
                                sLogger.info(String.format("Test '%1$s' updated", test.getTestName()));
                                req.setAttribute(TEST_EDITED, TEST_EDITED_MESSAGE);
                            }

                            for (Question question : test.getQuestions()) {
                                if (question.getQuestionName().equals(questionNameParameter)) {
                                    req.setAttribute("question", question);
                                    return new QuestionAction().perform(req, resp);
                                }
                            }
                            break;
                        }
                    }
                }
            } else {
                sLogger.warn(String.format("'%1$s' does not match id pattern of test", id));
            }
        } else {
            sLogger.warn(TEST_NAME_EMPTY_MESSAGE);
            req.setAttribute(TEST_NAME_EMPTY, TEST_NAME_EMPTY_MESSAGE);
        }
        return new TestListAction().perform(req, resp);
    }
}
