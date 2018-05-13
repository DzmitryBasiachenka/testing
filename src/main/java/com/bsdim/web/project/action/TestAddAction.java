package com.bsdim.web.project.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.session.TestSession;
import com.bsdim.web.project.util.WebUtil;

public class TestAddAction implements IAction {
    private static final String TEST_SESSION = "testSession";
    private static final String QUESTION_ADD_JSP = "question-add.jsp";
    private static final String NUMBER_NOT_MATCH = "numberNotMatch";
    private static final String NUMBER_NOT_MATCH_MESSAGE = "Please input correct count(from 1 to 100) of the questions";
    private static final String TEST_EMPTY = "testEmpty";
    private static final String TEST_EMPTY_MESSAGE = "The all fields of test form should not be empty";

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private String testName;
    private String subjectSelect;
    private String countQuestions;

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        HttpSession session = req.getSession();

        testName = req.getParameter("testName");
        subjectSelect = req.getParameter("subjectSelect");
        countQuestions = req.getParameter("countQuestions");

        if (WebUtil.isNotBlank(testName, subjectSelect, countQuestions)) {
            Pattern emailPattern = Pattern.compile("([1-9][0-9]?|100)");
            Matcher matcher = emailPattern.matcher(countQuestions);
            if (matcher.matches()) {
                TestSession testSession = createTestSession();
                session.setAttribute(TEST_SESSION, testSession);
            } else {
                return redirectToTestAction(NUMBER_NOT_MATCH, NUMBER_NOT_MATCH_MESSAGE);
            }
        } else {
            return redirectToTestAction(TEST_EMPTY, TEST_EMPTY_MESSAGE);
        }
        return QUESTION_ADD_JSP;
    }

    private TestSession createTestSession() {
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
