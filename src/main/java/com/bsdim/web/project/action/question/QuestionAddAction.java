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
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.session.TestSession;
import com.bsdim.web.project.session.UserSession;
import com.bsdim.web.project.util.ActionUtil;
import com.bsdim.web.project.util.WebUtil;

public class QuestionAddAction implements IAction {
    private static final String QUESTION_ADD_JSP = "question-add.jsp";
    private static final String TEST_SESSION = "testSession";
    private static final String USER_SESSION = "userSession";
    private static final String TEST_SAVED = "testSaved";
    private static final String TEST_SAVED_MESSAGE = "Test saved";
    private static final String QUESTION_EMPTY = "questionEmpty";
    private static final String QUESTION_EMPTY_MESSAGE = "The all fields of question form should not be empty";

    private TestService service = new TestService();

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        TestSession testSession = (TestSession) session.getAttribute(TEST_SESSION);

        if (testSession != null) {
            List<Question> questions = testSession.getQuestions();
            int countQuestions = testSession.getCountQuestions();

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
                List<Answer> answers = new ArrayList<>();
                answers.add(createAnswer(answer1, checkbox1));
                answers.add(createAnswer(answer2, checkbox2));
                answers.add(createAnswer(answer3, checkbox3));
                answers.add(createAnswer(answer4, checkbox4));

                questions.add(createQuestion(questionName, answers));

                if (questions.size() == countQuestions) {
                    UserSession userSession = (UserSession) session.getAttribute(USER_SESSION);

                    User user = new User();
                    user.setId(userSession.getId());

                    Subject subject = new Subject();
                    subject.setSubjectName(testSession.getSubjectName());

                    Test test = new Test();
                    test.setUser(user);
                    String testName = ActionUtil.replaceExtraSpaces(testSession.getTestName().trim());
                    test.setTestName(testName);
                    test.setSubject(subject);
                    test.setQuestions(questions);

                    service.addTest(test);

                    session.removeAttribute(TEST_SESSION);
                    req.setAttribute(TEST_SAVED, TEST_SAVED_MESSAGE);

                    return new TestListAction().perform(req, resp);
                } else {
                    return QUESTION_ADD_JSP;
                }
            } else {
                req.setAttribute(QUESTION_EMPTY, QUESTION_EMPTY_MESSAGE);
                return QUESTION_ADD_JSP;
            }
        } else {
            return new TestListAction().perform(req, resp);
        }
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
