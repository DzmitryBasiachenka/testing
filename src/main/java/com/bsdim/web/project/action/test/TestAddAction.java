package com.bsdim.web.project.action.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.session.TestSession;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

public class TestAddAction implements IAction {
    private static final String TEST_SESSION = "testSession";
    private static final String QUESTION_ADD_JSP = "question-add.jsp";
    private static final String NUMBER_NOT_MATCH = "numberNotMatch";
    private static final String NUMBER_NOT_MATCH_MESSAGE = "t.number.not.match.message";
    private static final String TEST_EMPTY = "testEmpty";
    private static final String TEST_EMPTY_MESSAGE = "t.test.empty.message";

    private static Logger sLogger = Logger.getLogger(TestAddAction.class);

    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        HttpSession session = req.getSession();

        String testName = req.getParameter("testName");
        String subjectSelect = req.getParameter("subjectSelect");
        String countQuestions = req.getParameter("countQuestions");

        if (WebUtil.isNotBlank(testName, subjectSelect, countQuestions)) {
            Pattern numberPattern = Pattern.compile("([1-9][0-9]?|100)");
            Matcher matcher = numberPattern.matcher(countQuestions);
            if (matcher.matches()) {
                TestSession testSession = createTestSession(testName, subjectSelect, countQuestions);
                session.setAttribute(TEST_SESSION, testSession);
                sLogger.info("Test session created");
            } else {
                sLogger.warn("Field count questions does not match number pattern ");
                return redirectToTestAction(NUMBER_NOT_MATCH, NUMBER_NOT_MATCH_MESSAGE);
            }
        } else {
            sLogger.warn("The all fields of test form should not be empty");
            return redirectToTestAction(TEST_EMPTY, TEST_EMPTY_MESSAGE);
        }
        return QUESTION_ADD_JSP;
    }

    private TestSession createTestSession(String testName, String subjectSelect, String countQuestions) {
        TestSession testSession = new TestSession();
        testSession.setTestName(testName);
        testSession.setSubjectName(subjectSelect);
        testSession.setCountQuestions(Integer.parseInt(countQuestions));
        List<Question> questions = new ArrayList<>(testSession.getCountQuestions());
        testSession.setQuestions(questions);
        return testSession;
    }

    private String redirectToTestAction(String attribute, String attributeMessage) {
        req.setAttribute(attribute, attributeMessage);
        return new TestListAction().perform(req, resp);
    }
}
