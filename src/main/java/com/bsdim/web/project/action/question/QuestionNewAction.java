package com.bsdim.web.project.action.question;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.action.test.TestListAction;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

public class QuestionNewAction implements IAction {
    private static final String QUESTION_SAVED = "questionSaved";
    private static final String QUESTION_SAVED_MESSAGE = "t.question.saved.message";
    private static final String QUESTION_EMPTY = "questionEmpty";
    private static final String QUESTION_EMPTY_MESSAGE = "t.question.empty.message";

    private static Logger sLogger = Logger.getLogger(QuestionNewAction.class);

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");

        String questionName = req.getParameter("questionName");

        String checkbox1 = req.getParameter("checkbox1");
        String checkbox2 = req.getParameter("checkbox2");
        String checkbox3 = req.getParameter("checkbox3");
        String checkbox4 = req.getParameter("checkbox4");

        String answer1 = req.getParameter("answer1");
        String answer2 = req.getParameter("answer2");
        String answer3 = req.getParameter("answer3");
        String answer4 = req.getParameter("answer4");

        if (WebUtil.isNotBlank(questionName, answer1, answer2, answer3, answer4)) {
            String id = ActionUtil.getIdFromServletPath(req.getServletPath());
            if (ActionUtil.isIdPattern(id)) {
                int testId = Integer.parseInt(id);
                List<Test> tests = testService.findTestsByUserId(userSession.getId());
                if (tests != null) {
                    for (Test test : tests) {
                        if (test.getId() == testId) {
                            List<Answer> answers = new ArrayList<>();
                            answers.add(createAnswer(answer1, checkbox1));
                            answers.add(createAnswer(answer2, checkbox2));
                            answers.add(createAnswer(answer3, checkbox3));
                            answers.add(createAnswer(answer4, checkbox4));

                            Question question = createQuestion(questionName, answers);
                            question.setTest(test);

                            questionService.addQuestion(question);
                            sLogger.info(String.format("Question '%1$s' added", question.getQuestionName()));
                            req.setAttribute(QUESTION_SAVED, QUESTION_SAVED_MESSAGE);
                        }
                    }
                }
            } else {
                sLogger.warn(String.format("'%1$s' does not match id pattern of test", id));
            }
        } else {
            sLogger.warn(QUESTION_EMPTY_MESSAGE);
            req.setAttribute(QUESTION_EMPTY, QUESTION_EMPTY_MESSAGE);
        }


        return new TestListAction().perform(req, resp);
    }

    private Question createQuestion(String questionName, List<Answer> answers) {
        Question question = new Question();
        question.setQuestionName(ActionUtil.replaceExtraSpaces(questionName.trim()));

        question.setAnswers(answers);
        return question;
    }

    private Answer createAnswer(String answerName, String checkbox) {
        Answer answer = new Answer();
        answer.setAnswerName(ActionUtil.replaceExtraSpaces(answerName.trim()));
        if (checkbox == null) {
            answer.setCorrectAnswer(false);
        } else {
            answer.setCorrectAnswer(true);
        }
        return answer;
    }
}
