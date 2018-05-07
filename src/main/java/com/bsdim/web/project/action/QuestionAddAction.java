package com.bsdim.web.project.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.TestSession;
import com.bsdim.web.project.session.UserSession;

public class QuestionAddAction implements IAction {
    private static final String QUESTION_JSP = "question.jsp";
    private static final String TEST_SAVED = "testSaved";
    private static final String TEST_SAVED_MESSAGE = "Test saved";

    private TestService testService = new TestService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        TestSession testSession = (TestSession) session.getAttribute("testSession");

        if (testSession != null) {
            List<Question> questions = testSession.getQuestions();
            int countQuestions = testSession.getCountQuestions();

            String question = req.getParameter("questionName");

            String checkbox1 = req.getParameter("checkbox1");
            String checkbox2 = req.getParameter("checkbox2");
            String checkbox3 = req.getParameter("checkbox3");
            String checkbox4 = req.getParameter("checkbox4");

            String answer1 = req.getParameter("answer1");
            String answer2 = req.getParameter("answer2");
            String answer3 = req.getParameter("answer3");
            String answer4 = req.getParameter("answer4");

            List<Answer> answers = new ArrayList<>();
            answers.add(createAnswer(answer1, checkbox1));
            answers.add(createAnswer(answer2, checkbox2));
            answers.add(createAnswer(answer3, checkbox3));
            answers.add(createAnswer(answer4, checkbox4));

            questions.add(createQuestion(question, answers));

            if (questions.size() == countQuestions) {
                UserSession userSession = (UserSession) session.getAttribute("userSession");

                User user = new User();
                user.setId(userSession.getId());

                Subject subject = new Subject();
                subject.setSubjectName(testSession.getSubjectName());

                Test test = new Test();
                test.setUser(user);
                test.setTestName(testSession.getTestName());
                test.setSubject(subject);
                test.setQuestions(questions);

                testService.addTest(test);

                session.setAttribute("testSession", null);
                req.setAttribute(TEST_SAVED, TEST_SAVED_MESSAGE);

                return new TestAction().perform(req, resp);
            } else {
                return QUESTION_JSP;
            }
        } else {
            return new TestAction().perform(req, resp);
        }
    }

    private Question createQuestion(String questionName, List<Answer> answers) {
        Question question = new Question();
        question.setQuestionName(questionName);

        question.setAnswers(answers);
        return question;
    }

    private Answer createAnswer(String answerName, String checkbox) {
        Answer answer = new Answer();
        answer.setAnswerName(answerName);
        if (checkbox == null) {
            answer.setCorrectAnswer(false);
        } else {
            answer.setCorrectAnswer(true);
        }
        return answer;
    }
}
