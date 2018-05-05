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

        UserService service = new UserService();
        System.out.println(service.findById(5));

        /*SubjectService subjectService = new SubjectService();
        Subject subject = subjectService.findBySubjectName("математика");
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
