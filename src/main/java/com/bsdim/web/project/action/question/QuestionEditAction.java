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
import com.bsdim.web.project.service.AnswerService;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;
import org.apache.log4j.Logger;

/**
 * The question edit action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class QuestionEditAction implements IAction {
    private static final String QUESTION_EDITED = "questionEdited";
    private static final String QUESTION_EDITED_MESSAGE = "t.question.edited.message";
    private static final String QUESTION_EMPTY = "questionEmpty";
    private static final String QUESTION_EMPTY_MESSAGE = "t.question.empty.message";

    private static Logger sLogger = Logger.getLogger(QuestionEditAction.class);

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();
    private AnswerService answerService = new AnswerService();

    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String questionName = req.getParameter("questionName");

        String checkbox1 = req.getParameter("checkbox1");
        String checkbox2 = req.getParameter("checkbox2");
        String checkbox3 = req.getParameter("checkbox3");
        String checkbox4 = req.getParameter("checkbox4");

        List<String> checkboxes = new ArrayList<>();
        checkboxes.add(checkbox1);
        checkboxes.add(checkbox2);
        checkboxes.add(checkbox3);
        checkboxes.add(checkbox4);

        String answer1 = req.getParameter("answer1");
        String answer2 = req.getParameter("answer2");
        String answer3 = req.getParameter("answer3");
        String answer4 = req.getParameter("answer4");

        List<String> answerNames = new ArrayList<>();
        answerNames.add(answer1);
        answerNames.add(answer2);
        answerNames.add(answer3);
        answerNames.add(answer4);

        if (WebUtil.isNotBlank(questionName, answer1, answer2, answer3, answer4)) {
            HttpSession session = req.getSession();
            UserSession userSession = (UserSession) session.getAttribute("userSession");

            String questionIdParameter = ActionUtil.getIdFromServletPath(req.getServletPath());
            if (ActionUtil.isIdPattern(questionIdParameter)) {
                int questionId = Integer.parseInt(questionIdParameter);
                List<Test> tests = testService.findTestsByUserId(userSession.getId());

                if (tests != null) {
                    for (Test test : tests) {
                        List<Question> questions = test.getQuestions();
                        for (Question question : questions) {
                            if (question.getId() == questionId) {
                                if (!question.getQuestionName().equals(questionName)) {
                                    question.setQuestionName(questionName);
                                    questionService.updateQuestion(question);
                                    sLogger.info(String.format("Question '%1$s' updated", question.getQuestionName()));
                                }
                                List<Answer> answers = question.getAnswers();
                                for (int i = 0; i < answers.size(); i++) {
                                    Answer answer = editAnswer(answers.get(i), checkboxes.get(i), answerNames.get(i));
                                    answerService.updateAnswer(answer);
                                    sLogger.info(String.format("Answer '%1$s' updated", answer.getAnswerName()));
                                }
                                req.setAttribute(QUESTION_EDITED, QUESTION_EDITED_MESSAGE);
                                break;
                            }
                        }
                    }
                }
            } else {
                sLogger.warn(String.format("'%1$s' does not match id pattern of question", questionIdParameter));
            }
        } else {
            sLogger.warn(QUESTION_EMPTY_MESSAGE);
            req.setAttribute(QUESTION_EMPTY, QUESTION_EMPTY_MESSAGE);
        }
        return new TestListAction().perform(req, resp);
    }

    private Answer editAnswer(Answer answer, String checkbox, String answerName) {
        answer.setAnswerName(answerName);
        if (checkbox == null) {
            answer.setCorrectAnswer(false);
        } else {
            answer.setCorrectAnswer(true);
        }
        return answer;
    }
}
