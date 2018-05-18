package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IQuestionDao;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

public class QuestionDaoSql implements IQuestionDao {
    private static final String CREATE_QUESTION = "insert into question(question_name, test_id) values(?, ?)";
    private static final String READ_QUESTION = "select id, question_name, test_id from question where id = ?";
    private static final String UPDATE_QUESTION = "update question set question_name = ? where id = ?";
    private static final String DELETE_QUESTION = "delete from question where id = ?";
    private static final String GET_QUESTIONS = "select id, question_name, test_id from question order by id";
    private static final String GET_ID_QUESTIONS_BY_TEST_ID = "select question.id from test join question" +
            " on test.id = question.test_id where test.id = ? order by question.id";
    private static final String GET_QUESTION_WITH_ANSWERS = "select question.id, question.question_name, answer.id, " +
            "answer.answer_name, answer.correct_answer from question join answer on question.id = answer.question_id " +
            "where question.id = ? order by answer.id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;

    private static Logger sLogger = Logger.getLogger(QuestionDaoSql.class);

    @Override
    public Integer create(Question question) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(PARAMETER_INDEX_ONE, question.getQuestionName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, question.getTest().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            sLogger.error("Create question error!");
            throw new TestingRuntimeException("Create question error!", e);
        }
    }

    @Override
    public Question read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_QUESTION);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestionName(resultSet.getString("question_name"));
                Test test = new Test();
                test.setId(resultSet.getInt("test_id"));
                question.setTest(test);
                return question;
            }
            return null;
        } catch (SQLException e) {
            sLogger.error("Read question error!");
            throw new TestingRuntimeException("Read question error!", e);
        }
    }

    @Override
    public void update(Question question) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUESTION);
            preparedStatement.setString(PARAMETER_INDEX_ONE, question.getQuestionName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            sLogger.error("Update question error!");
            throw new TestingRuntimeException("Update question error!", e);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUESTION);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            sLogger.error("Delete question error!");
            throw new TestingRuntimeException("Delete question error!", e);
        }
    }

    @Override
    public List<Question> getQuestions() {
        Connection connection = ConnectionContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_QUESTIONS);
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestionName(resultSet.getString("question_name"));
                Test test = new Test();
                test.setId(resultSet.getInt("test_id"));
                question.setTest(test);
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            sLogger.error("Get questions error!");
            throw new TestingRuntimeException("Get questions error!", e);
        }
    }

    @Override
    public List<Integer> getIdQuestionsByTestId(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_QUESTIONS_BY_TEST_ID);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> idQuestions = new ArrayList<>();
            while (resultSet.next()) {
                idQuestions.add(resultSet.getInt("id"));
            }
            return idQuestions;
        } catch (SQLException e) {
            sLogger.error("Get id questions by test id error!");
            throw new TestingRuntimeException("Get id questions by test id error!", e);
        }
    }

    @Override
    public Question getQuestion(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUESTION_WITH_ANSWERS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestionName(resultSet.getString("question_name"));

                resultSet.previous();
                List<Answer> answers = new ArrayList<>();
                while(resultSet.next()) {
                    Answer answer = new Answer();
                    answer.setId(resultSet.getInt("answer.id"));
                    answer.setAnswerName(resultSet.getString("answer.answer_name"));
                    answer.setCorrectAnswer(resultSet.getBoolean("answer.correct_answer"));

                    answer.setQuestion(question);
                    answers.add(answer);
                }
                question.setAnswers(answers);
                return question;
            }
            return null;
        } catch (SQLException e) {
            sLogger.error("Get question error!");
            throw new TestingRuntimeException("Get question error!", e);
        }
    }
}
