package com.bsdim.web.project.action.examination;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.action.IAction;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.session.ExaminationSession;
import com.bsdim.web.project.session.UserSession;
import org.apache.log4j.Logger;

/**
 * The examination question action.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class ExaminationQuestionAction implements IAction {
    private static final String EXAMINATION_TEST_JSP = "examination-test.jsp";
    private static final String EXAMINATION_SESSION = "examinationSession";
    private static final String TEST_PASSED = "testPassed";
    private static final String TEST_PASSED_MESSAGE = "t.test.passed.message";
    private static final String USER_SESSION = "userSession";

    private static Logger sLogger = Logger.getLogger(ExaminationQuestionAction.class);

    private QuestionService questionService = new QuestionService();
    private StatisticsService statisticsService = new StatisticsService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ExaminationSession examinationSession = (ExaminationSession) session.getAttribute(EXAMINATION_SESSION);
        UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

        if (examinationSession != null) {
            if (req.getServletPath().contains("question")) {
                checkAnswerAtQuestion(examinationSession, req);
            }

            List<Integer> idQuestions = examinationSession.getIdQuestions();
            if (idQuestions.size() == 0) {
                Statistics statistics = fillStatistics(examinationSession, userSession);

                statisticsService.addStatistics(statistics);
                sLogger.info("Statistics added");

                session.removeAttribute(EXAMINATION_SESSION);
                sLogger.info("Examination session deleted");
                req.setAttribute(TEST_PASSED, TEST_PASSED_MESSAGE);
                return new ExaminationAction().perform(req, resp);
            }
            Integer questionId = idQuestions.get(0);
            examinationSession.setQuestion(questionService.getQuestion(questionId));
            idQuestions.remove(0);
        }
        return EXAMINATION_TEST_JSP;
    }

    private void checkAnswerAtQuestion(ExaminationSession examinationSession, HttpServletRequest req) {
        String checkbox1 = req.getParameter("checkbox1");
        String checkbox2 = req.getParameter("checkbox2");
        String checkbox3 = req.getParameter("checkbox3");
        String checkbox4 = req.getParameter("checkbox4");

        List<String> checkboxes = new ArrayList<>();
        checkboxes.add(checkbox1);
        checkboxes.add(checkbox2);
        checkboxes.add(checkbox3);
        checkboxes.add(checkbox4);

        Question question = examinationSession.getQuestion();
        List<Answer> answers = question.getAnswers();

        for (int i = 0; i < answers.size(); i++) {
            Answer answer = answers.get(i);
            if (answer.isCorrectAnswer() != isTrueCheckBox(checkboxes.get(i))) {
                int countIncorrectAnswers = examinationSession.getCountIncorrectAnswers();
                countIncorrectAnswers += 1;
                examinationSession.setCountIncorrectAnswers(countIncorrectAnswers);
                break;
            } else if (i == answers.size() - 1) {
                int countCorrectAnswers = examinationSession.getCountCorrectAnswers();
                countCorrectAnswers += 1;
                examinationSession.setCountCorrectAnswers(countCorrectAnswers);
            }
        }
        sLogger.info(String.format("Question '%1$s' checked", question.getQuestionName()));
    }

    private boolean isTrueCheckBox(String checkbox) {
        return checkbox != null;
    }

    private Statistics fillStatistics(ExaminationSession examinationSession, UserSession userSession) {
        Statistics statistics = new Statistics();

        Test test = new Test();
        test.setId(examinationSession.getTestId());

        User user = new User();
        user.setId(userSession.getId());

        statistics.setTest(test);
        statistics.setCountCorrectAnswers(examinationSession.getCountCorrectAnswers());
        statistics.setCountIncorrectAnswers(examinationSession.getCountIncorrectAnswers());
        statistics.setStartTesting(examinationSession.getStartTesting());
        statistics.setFinishTesting(new Timestamp(System.currentTimeMillis()));
        statistics.setUser(user);

        return statistics;
    }
}
