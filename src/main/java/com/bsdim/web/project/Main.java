package com.bsdim.web.project;

import java.util.List;

import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.service.StatisticsService;

public class Main {
    public static void main(String[] args) {
        /*TestDaoSql testDaoSql = new TestDaoSql();
        for (Test test: testDaoSql.getTests()) {
            System.out.println(test);
        }*/

        StatisticsService service = new StatisticsService();
        List<Statistics> statisticsList = service.getStudentStatisticsByTestId(2);

        for (Statistics statistics : statisticsList) {
            System.out.println(statistics.getUser().getLogin());
            System.out.println(statistics.getUser().getFirstName());
            System.out.println(statistics.getUser().getLastName());
            System.out.println(statistics.getTest().getTestName());
            System.out.println(statistics.getTest().getSubject().getSubjectName());
            System.out.println(statistics.getCountCorrectAnswers());
            System.out.println(statistics.getCountIncorrectAnswers());
            System.out.println(statistics.getStartTesting());
            System.out.println(statistics.getFinishTesting());
            System.out.println("------------------------------------");
        }


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
}
