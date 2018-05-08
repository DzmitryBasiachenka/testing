package com.bsdim.web.project.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.service.AnswerService;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.WebUtil;

//TO DO To change updating of question with help transaction
public class QuestionEditAction implements IAction {
    private static final String QUESTION_EMPTY = "questionEmpty";
    private static final String QUESTION_EMPTY_MESSAGE = "The all fields of question form should not be empty";

    private TestService testService = new TestService();
    private QuestionService questionService = new QuestionService();
    private AnswerService answerService = new AnswerService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        String questionName = req.getParameter("questionName");

        //Перед обновлением вопроса можно сначала удалять его данные потом обновлять
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

        /*System.out.println(questionName);

        System.out.println(checkbox1);
        System.out.println(checkbox2);
        System.out.println(checkbox3);
        System.out.println(checkbox4);

        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);*/

        if (WebUtil.isNotBlank(questionName, answer1, answer2, answer3, answer4)) {
            HttpSession session = req.getSession();
            UserSession userSession = (UserSession)session.getAttribute("userSession");

            String servletPath = req.getServletPath();
            int index = servletPath.lastIndexOf('/', servletPath.length());
            int questionId = Integer.parseInt(servletPath.substring(index + 1));

            List<Test> tests = testService.findTestsByUserId(userSession.getId());

            for (Test test : tests) {
                List<Question> questions = test.getQuestions();
                for (Question question : questions) {
                    if (question.getId() == questionId) {
                        if (!question.getQuestionName().equals(questionName)) {
                            question.setQuestionName(questionName);
                            questionService.updateQuestion(question);
                        }
                        List<Answer> answers = question.getAnswers();
                        for (int i = 0; i < answers.size(); i++) {
                            editAnswer(answers.get(i), checkboxes.get(i), answerNames.get(i));
                        }
                        return new TestListAction().perform(req, resp);
                    }
                }
            }
        } else {
            req.setAttribute(QUESTION_EMPTY, QUESTION_EMPTY_MESSAGE);
        }
        return new TestListAction().perform(req, resp);
    }

    private void editAnswer(Answer answer, String checkbox, String answerName) {
        answer.setAnswerName(answerName);
        if (checkbox == null) {
            answer.setCorrectAnswer(false);
        } else {
            answer.setCorrectAnswer(true);
        }
        answerService.updateAnswer(answer);
    }
}
