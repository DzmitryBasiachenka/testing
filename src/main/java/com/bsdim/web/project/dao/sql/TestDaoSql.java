package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.connection.ConnectionManager;
import com.bsdim.web.project.dao.api.ITestDao;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Entity;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;

public class TestDaoSql implements ITestDao {
    private static final String CREATE_TEST = "insert into test(test_name, subject_id, user_id) values(?, ?, ?)";
    private static final String READ_TEST = "select id, test_name, subject_id, user_id from test where id = ?";
    private static final String UPDATE_TEST = "update test set test_name = ?, subject_id = ? where id = ?";
    private static final String DELETE_TEST = "delete from test where id = ?";
    /*private static final String READ_TEST_TRANSACTION = "select id, test_name, subject_id, user_id from test where id = last_insert_id()";
    private static final String CREATE_ANSWER = "insert into answer(answer_name, correct_answer, question_id) values(?, ?, ?)";
    private static final String CREATE_QUESTION = "insert into question(question_name, test_id) values(?, ?)";
    private static final String FIND_SUBJECT_BY_NAME = "select id, subject_name from subject where subject_name = ?";
    private static final String READ_QUESTION = "select id, question_name, test_id from question where id = last_insert_id()";
    private static final String READ_TEST = "select id, test_name, subject_id, user_id from test where id = ?";*/

    private static final String GET_TESTS = "select id, test_name, subject_id, user_id from test order by id";
    /*private static final String FIND_TESTS_BY_USER_ID = "select test.id, test_name, subject.subject_name " +
            "from users join test on users.id = test.user_id join subject on test.subject_id = subject.id where users.id = ?";*/
    private static final String FIND_TESTS_BY_USER_ID = "select users.id, users.login, test.id, test.test_name, subject.id, " +
            "subject.subject_name, question.id, question.question_name, answer.id, answer.answer_name, answer.correct_answer " +
            "from users join test on users.id = test.user_id join subject on test.subject_id = subject.id join question on " +
            "test.id = question.test_id join answer on question.id = answer.question_id where users.id = ? order by test.id";

    private static final String FIND_TESTS_BY_SUBJECT_NAME = "select users.id, users.login, users.first_name, users.last_name, test.id, " +
            "test.test_name, question.id, question.question_name from users join test on users.id = test.user_id join subject " +
            "on subject.id = test.subject_id join question on test.id = question.test_id where subject.subject_name = ? order by users.id, test.id";

    private static final String FIND_TEST_BY_ID = "select users.id, users.login, test.id, test.test_name, question.id, question.question_name, " +
            "answer.id, answer.answer_name, answer.correct_answer from users join test on users.id = test.user_id join question on " +
            "test.id = question.test_id join answer on question.id = answer.question_id where test.id = ? order by question.id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;

    /*@Override
    public Integer create(Test test) {
        Connection connection = ConnectionContext.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUBJECT_BY_NAME);
            preparedStatement.setString(PARAMETER_INDEX_ONE, test.getSubject().getSubjectName());
            ResultSet resultSet = preparedStatement.executeQuery();
            Subject subject;
            if (resultSet.next()) {
                subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setSubjectName(resultSet.getString("subject_name"));
            } else {
                throw new SQLException();
            }

            preparedStatement = connection.prepareStatement(CREATE_TEST);
            preparedStatement.setString(PARAMETER_INDEX_ONE, test.getTestName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, subject.getId());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, test.getUser().getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(READ_TEST_TRANSACTION);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                test.setId(resultSet.getInt("id"));
            } else {
                throw new SQLException();
            }

            List<Question> questions = test.getQuestions();
            for (Question question : questions) {
                preparedStatement = connection.prepareStatement(CREATE_QUESTION);
                preparedStatement.setString(PARAMETER_INDEX_ONE, question.getQuestionName());
                preparedStatement.setInt(PARAMETER_INDEX_TWO, test.getId());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(READ_QUESTION);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    question.setId(resultSet.getInt("id"));
                } else {
                    throw new SQLException();
                }

                List<Answer> answers = question.getAnswers();
                for (Answer answer : answers) {
                    preparedStatement = connection.prepareStatement(CREATE_ANSWER);
                    preparedStatement.setString(PARAMETER_INDEX_ONE, answer.getAnswerName());
                    preparedStatement.setBoolean(PARAMETER_INDEX_TWO, answer.isCorrectAnswer());
                    preparedStatement.setInt(PARAMETER_INDEX_THREE, question.getId());
                    preparedStatement.executeUpdate();
                }
            }
            connection.commit();
            return null;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
            throw new RuntimeException();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }*/

    @Override
    public Integer create(Test test) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TEST, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(PARAMETER_INDEX_ONE, test.getTestName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, test.getSubject().getId());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, test.getUser().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException();//e.printStackTrace();//throw new RepositoryException(e);
        }
    }

    @Override
    public Test read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_TEST);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getInt("id"));
                test.setTestName(resultSet.getString("test_name"));
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("subject_id"));
                test.setSubject(subject);
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                test.setUser(user);
                return test;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Test test) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEST);
            preparedStatement.setString(PARAMETER_INDEX_ONE, test.getTestName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, test.getSubject().getId());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, test.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();//throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEST);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }


    @Override
    public List<Test> getTests() {
        Connection connection = ConnectionContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_TESTS);
            List<Test> tests = new ArrayList<>();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getInt("id"));
                test.setTestName(resultSet.getString("test_name"));
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("subject_id"));
                test.setSubject(subject);
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                test.setUser(user);
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public Test findTestById(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TEST_BY_ID);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("users.id"));
                user.setLogin(resultSet.getString("users.login"));

                Test test = new Test();
                test.setId(resultSet.getInt("test.id"));
                test.setTestName(resultSet.getString("test.test_name"));

                test.setUser(user);

                List<Question> questions = new ArrayList<>();
                while (test.getId() == resultSet.getInt("test.id")) {
                    Question question = new Question();
                    question.setId(resultSet.getInt("question.id"));
                    question.setQuestionName(resultSet.getString("question.question_name"));

                    question.setTest(test);

                    List<Answer> answers = new ArrayList<>();
                    while (question.getId() == resultSet.getInt("question.id")) {
                        Answer answer = new Answer();
                        answer.setId(resultSet.getInt("answer.id"));
                        answer.setAnswerName(resultSet.getString("answer.answer_name"));
                        answer.setCorrectAnswer(resultSet.getBoolean("answer.correct_answer"));

                        answer.setQuestion(question);
                        answers.add(answer);

                        resultSet.next();
                        if (resultSet.isAfterLast()) {
                            break;
                        }
                    }
                    question.setAnswers(answers);
                    questions.add(question);
                    if (resultSet.isAfterLast()) {
                        break;
                    }

                }
                test.setQuestions(questions);
                return test;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    /*@Override
    public List<Test> findTestByUserId(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TESTS_BY_USER_ID);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Test> tests = new ArrayList<>();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getInt("id"));
                test.setTestName(resultSet.getString("test_name"));
                Subject subject = new Subject();
                subject.setSubjectName(resultSet.getString("subject_name"));
                test.setSubject(subject);
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }*/

    @Override
    public List<Test> findTestsByUserId(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TESTS_BY_USER_ID);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Test> tests = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("users.id"));
                user.setLogin(resultSet.getString("users.login"));

                Test test = new Test();
                test.setId(resultSet.getInt("test.id"));
                test.setTestName(resultSet.getString("test.test_name"));

                Subject subject = new Subject();
                subject.setId(resultSet.getInt("subject.id"));
                subject.setSubjectName(resultSet.getString("subject.subject_name"));

                test.setSubject(subject);
                test.setUser(user);

                List<Question> questions = new ArrayList<>();
                while (test.getId() == resultSet.getInt("test.id")) {
                    Question question = new Question();
                    question.setId(resultSet.getInt("question.id"));
                    question.setQuestionName(resultSet.getString("question.question_name"));

                    question.setTest(test);

                    List<Answer> answers = new ArrayList<>();
                    while (question.getId() == resultSet.getInt("question.id")) {
                        Answer answer = new Answer();
                        answer.setId(resultSet.getInt("answer.id"));
                        answer.setAnswerName(resultSet.getString("answer.answer_name"));
                        answer.setCorrectAnswer(resultSet.getBoolean("answer.correct_answer"));

                        answer.setQuestion(question);
                        answers.add(answer);

                        resultSet.next();
                        if (resultSet.isAfterLast()) {
                            break;
                        }
                    }
                    question.setAnswers(answers);
                    questions.add(question);
                    if (resultSet.isAfterLast()) {
                        break;
                    }

                }
                test.setQuestions(questions);
                tests.add(test);
                if (resultSet.isAfterLast()) {
                    break;
                }
                resultSet.previous();
            }
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Test> findTestsBySubjectName(String subjectName) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TESTS_BY_SUBJECT_NAME);
            preparedStatement.setString(PARAMETER_INDEX_ONE, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Test> tests = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("users.id"));
                user.setLogin(resultSet.getString("users.login"));
                user.setFirstName(resultSet.getString("users.first_name"));
                user.setLastName(resultSet.getString("users.last_name"));

                Test test = new Test();
                test.setId(resultSet.getInt("test.id"));
                test.setTestName(resultSet.getString("test.test_name"));

                test.setUser(user);

                List<Question> questions = new ArrayList<>();
                while (test.getId() == resultSet.getInt("test.id")) {
                    Question question = new Question();
                    question.setId(resultSet.getInt("question.id"));
                    question.setQuestionName(resultSet.getString("question.question_name"));

                    question.setTest(test);

                    resultSet.next();

                    questions.add(question);
                    if (resultSet.isAfterLast()) {
                        break;
                    }
                }
                test.setQuestions(questions);
                tests.add(test);
                if (resultSet.isAfterLast()) {
                    break;
                }
                resultSet.previous();
            }
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
