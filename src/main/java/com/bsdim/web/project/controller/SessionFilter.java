package com.bsdim.web.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.examination.ExaminationExitAction;
import com.bsdim.web.project.action.examination.ExaminationQuestionAction;
import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.question.QuestionAddAction;
import com.bsdim.web.project.action.test.TestExitAction;
import com.bsdim.web.project.session.ExaminationSession;
import com.bsdim.web.project.session.TestSession;
import com.bsdim.web.project.util.ActionUtil;

public class SessionFilter implements Filter {
    private static final String EXAMINATION_TEST_JSP = "examination-test.jsp";
    private static final String QUESTION_ADD_JSP = "question-add.jsp";
    private static final String EXAMINATION_SESSION = "examinationSession";
    private static final String TEST_SESSION = "testSession";

    private Map<String, IAction> mapExamination;
    private Map<String, IAction> mapTest;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initMapExamination();
        initMapTest();
    }

    private void initMapExamination() {
        mapExamination = new HashMap<>();
        mapExamination.put("/examination/question", new ExaminationQuestionAction());
        mapExamination.put("/examination/exit", new ExaminationExitAction());
    }

    private void initMapTest() {
        mapTest = new HashMap<>();
        mapTest.put("/question/add", new QuestionAddAction());
        mapTest.put("/test/exit", new TestExitAction());
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        HttpServletResponse httpServletResponse = (HttpServletResponse)resp;

        HttpSession session = httpServletRequest.getSession();
        ExaminationSession examinationSession = (ExaminationSession) session.getAttribute(EXAMINATION_SESSION);
        TestSession testSession = (TestSession) session.getAttribute(TEST_SESSION);

        if (examinationSession != null) {
            process(httpServletRequest, httpServletResponse, mapExamination, EXAMINATION_TEST_JSP);
        } else if (testSession != null) {
            process(httpServletRequest, httpServletResponse, mapTest, QUESTION_ADD_JSP);
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, Map<String, IAction> map, String defaultPage)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        IAction action = ActionUtil.findAction(servletPath, map);

        String jspName = defaultPage;

        if (action != null) {
            jspName = action.perform(req, resp);
        }

        req.getRequestDispatcher("/WEB-INF/view/" + jspName).forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}
