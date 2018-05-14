package com.bsdim.web.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.sql.TestDaoSql;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.AnswerService;
import com.bsdim.web.project.service.QuestionService;
import com.bsdim.web.project.service.RoleService;
import com.bsdim.web.project.service.StatisticsService;
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

        /*User user = new User();
        //user.setId(7);
        user.setLogin("444");
        user.setPassword("444");
        user.setEmail("email5");
        user.setFirstName("fn");
        user.setLastName("ln");

        //create(user);
        //System.out.println(read(3));
        //update(user);
        System.out.println(create1(user));*/






        /*Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);*/

        /*TestService testService = new TestService();
        Test test = testService.findById(8);
        System.out.println(test.getId());
        System.out.println(test.getTestName());
        System.out.println(test.getUser().getId());
        System.out.println(test.getUser().getLogin());
        for (Question question : test.getQuestions()) {
            System.out.println(question);
            for (Answer answer : question.getAnswers()) {
                System.out.println(answer);
            }
        }
        System.out.println("----------------------");*/

        /*TestService testService = new TestService();
        List<Test> tests = testService.findTestsBySubjectName("php");
        for (Test test : tests) {
            System.out.println(test.getId());
            System.out.println(test.getTestName());
            System.out.println(test.getUser().getId());
            System.out.println(test.getUser().getLogin());
            System.out.println(test.getUser().getFirstName());
            System.out.println(test.getUser().getLastName());
            for (Question question : test.getQuestions()) {
                System.out.println(question);
                System.out.println(test.getQuestions().size());
            }
            System.out.println("----------------------");
        }*/

        /*AnswerService service = new AnswerService();
        System.out.println(service.findById(2));

        QuestionService questionService = new QuestionService();
        System.out.println(questionService.findById(2));

        RoleService roleService = new RoleService();
        System.out.println(roleService.findById(1));

        StatisticsService statisticsService = new StatisticsService();
        System.out.println(statisticsService.findById(1));

        SubjectService subjectService = new SubjectService();
        System.out.println(subjectService.findById(1));

        TestService testService = new TestService();
        System.out.println(testService.findById(2));

        UserService userService = new UserService();
        System.out.println(userService.findById(1));*/


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

    public static void create(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(login, password, email, first_name, last_name) values(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public static Integer create1(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(login, password, email, first_name, last_name) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();
            Integer id = null;
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public static User read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, login, password, email, first_name, last_name from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public static void update(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set login = ?, password = ?, email = ?, first_name = ?, last_name = ? where id = ?");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();//throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
