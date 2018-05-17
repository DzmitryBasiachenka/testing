package com.bsdim.web.project.action.question;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.test.TestListAction;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;

public class QuestionDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String QUESTION_DELETED = "questionDeleted";
    private static final Object QUESTION_DELETED_MESSAGE = "The question deleted";

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession)session.getAttribute(USER_SESSION);

        String id = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(id)) {
            int questionId = Integer.parseInt(id);
            List<Test> tests = testService.findTestsByUserId(userSession.getId());

            if (tests != null) {
                for (Test test : tests) {
                    for (Question question : test.getQuestions()) {
                        if (question.getId() == questionId) {
                            questionService.deleteQuestion(questionId);
                            req.setAttribute(QUESTION_DELETED, QUESTION_DELETED_MESSAGE);
                            break;
                        }
                    }
                }
            }
        }
        return new TestListAction().perform(req, resp);
    }
}
