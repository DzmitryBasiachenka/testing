package com.bsdim.web.project;

import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.dao.sql.TestDaoSql;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.RoleService;
import com.bsdim.web.project.service.SubjectService;
import com.bsdim.web.project.service.TestService;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;

public class Main {
    public static void main(String[] args) {
        /*TestDaoSql testDaoSql = new TestDaoSql();
        for (Test test: testDaoSql.getTests()) {
            System.out.println(test);
        }*/

        /*SubjectService subjectService = new SubjectService();
        System.out.println(subjectService.findSubjectByName("php"));*/

        /*List<Question> questions = new ArrayList<>();
        Test test = new Test();
        Subject subject = new Subject();
        subject.setSubjectName("математика");

        User user = new User();
        user.setId(2);

        test.setTestName("MMM");
        test.setSubject(subject);
        test.setUser(user);

        Question question = new Question();
        question.setQuestionName("что такое куки?");

        Answer answer = new Answer();
        answer.setAnswerName("куки это куки");
        answer.setCorrectAnswer(true);

        Answer answer1 = new Answer();
        answer1.setAnswerName("куки это тачки");
        answer1.setCorrectAnswer(false);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);
        question.setAnswers(answers);


        Question question1 = new Question();
        question1.setQuestionName("как найти ненаходимое?");

        Answer answer2 = new Answer();
        answer2.setAnswerName("никак");
        answer2.setCorrectAnswer(true);

        Answer answer3 = new Answer();
        answer3.setAnswerName("прыгнуть высоко");
        answer3.setCorrectAnswer(false);

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(answer2);
        answers1.add(answer3);
        question1.setAnswers(answers1);

        questions.add(question);
        questions.add(question1);

        test.setQuestions(questions);

        TestService service = new TestService();
        service.addTest(test);*/




        /*UserService service = new UserService();
        System.out.println(service.findById(5));*/

        /*SubjectService subjectService = new SubjectService();
        Subject subject = subjectService.findSubjectByName("математика");
        System.out.println(subject);*/

        /*TestService service = new TestService();
        List<Test> tests = service.findTestByUserId(2);

        for (Test test : tests) {
            System.out.println("test_id:   " + test.getId());
            System.out.println("test_name:   " + test.getTestName());
            System.out.println("subject_name:      " + test.getSubject().getSubjectName());
            System.out.println("user_login:        " + test.getUser().getLogin());
            System.out.println();
            for (Question question : test.getQuestions()) {
                System.out.println("question_id:        " + question.getId());
                System.out.println("question_name:        " + question.getQuestionName());
                System.out.println("test_nameQ:        " + question.getTest().getTestName());
                System.out.println();
                for (Answer answer : question.getAnswers()) {
                    System.out.println("answer_id:        " + answer.getId());
                    System.out.println("answer_name:        " + answer.getAnswerName());
                    System.out.println("correct_answer:        " + answer.isCorrectAnswer());
                    System.out.println("question_nameA:        " + answer.getQuestion().getQuestionName());
                    System.out.println();
                }
            }
            System.out.println("----------------------------");
        }*/

    }
}
