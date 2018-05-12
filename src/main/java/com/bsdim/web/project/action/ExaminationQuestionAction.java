package com.bsdim.web.project.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.StatisticsService;
import com.bsdim.web.project.session.ExaminationSession;
import com.bsdim.web.project.session.UserSession;

public class ExaminationQuestionAction implements IAction {
    private static final String EXAMINATION_TEST_JSP = "examination-test.jsp";
    private static final String EXAMINATION_SESSION = "examinationSession";
    private static final String USER_SESSION = "userSession";

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

                session.removeAttribute(EXAMINATION_SESSION);
                return new ExaminationAction().perform(req, resp);
            }
            Integer id = idQuestions.get(0);
            examinationSession.setQuestion(questionService.getQuestion(id));
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
            } else if (i == answers.size()-1) {
                int countCorrectAnswers = examinationSession.getCountCorrectAnswers();
                countCorrectAnswers += 1;
                examinationSession.setCountCorrectAnswers(countCorrectAnswers);
            }
        }
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