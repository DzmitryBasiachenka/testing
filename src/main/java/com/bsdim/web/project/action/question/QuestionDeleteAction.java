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
import org.apache.log4j.Logger;

/**
 * The question delete action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class QuestionDeleteAction implements IAction {
    private static final String USER_SESSION = "userSession";
    private static final String QUESTION_DELETED = "questionDeleted";
    private static final Object QUESTION_DELETED_MESSAGE = "t.question.deleted.message";

    private static Logger sLogger = Logger.getLogger(QuestionDeleteAction.class);

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        String questionIdParameter = ActionUtil.getIdFromServletPath(req.getServletPath());
        if (ActionUtil.isIdPattern(questionIdParameter)) {
            int questionId = Integer.parseInt(questionIdParameter);
            List<Test> tests = testService.findTestsByUserId(userSession.getId());

            if (tests != null) {
                for (Test test : tests) {
                    for (Question question : test.getQuestions()) {
                        if (question.getId() == questionId) {
                            questionService.deleteQuestion(questionId);
                            sLogger.info(String.format("Question '%1$s' deleted", question.getQuestionName()));
                            req.setAttribute(QUESTION_DELETED, QUESTION_DELETED_MESSAGE);
                            break;
                        }
                    }
                }
            }
        } else {
            sLogger.warn(String.format("'%1$s' does not match id pattern of question", questionIdParameter));
        }
        return new TestListAction().perform(req, resp);
    }
}
