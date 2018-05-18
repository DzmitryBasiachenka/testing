package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IAnswerDao;
import com.bsdim.web.project.domain.Answer;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

public class AnswerDaoSql implements IAnswerDao {
    private static final String CREATE_ANSWER = "insert into answer(answer_name, correct_answer, question_id) values(?, ?, ?)";
    private static final String READ_ANSWER = "select id, answer_name, correct_answer, question_id from answer where id = ?";
    private static final String UPDATE_ANSWER = "update answer set answer_name = ?, correct_answer = ? where id = ?";
    private static final String DELETE_ANSWER = "delete from answer where id = ?";
    private static final String GET_ANSWERS = "select id, answer_name, correct_answer, question_id from answer order by id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;

    private static Logger sLogger = Logger.getLogger(AnswerDaoSql.class);

    @Override
    public Integer create(Answer answer) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ANSWER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(PARAMETER_INDEX_ONE, answer.getAnswerName());
            preparedStatement.setBoolean(PARAMETER_INDEX_TWO, answer.isCorrectAnswer());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, answer.getQuestion().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            sLogger.error("Create answer error!");
            throw new TestingRuntimeException("Create answer error!", e);
        }
    }

    @Override
    public Answer read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ANSWER);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setAnswerName(resultSet.getString("answer_name"));
                answer.setCorrectAnswer(resultSet.getBoolean("correct_answer"));
                Question question = new Question();
                question.setId(resultSet.getInt("question_id"));
                answer.setQuestion(question);
                return answer;
            }
            return null;
        } catch (SQLException e) {
            sLogger.error("Read answer error!");
            throw new TestingRuntimeException("Read answer error!", e);
        }
    }

    @Override
    public void update(Answer answer) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ANSWER);
            preparedStatement.setString(PARAMETER_INDEX_ONE, answer.getAnswerName());
            preparedStatement.setBoolean(PARAMETER_INDEX_TWO, answer.isCorrectAnswer());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, answer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            sLogger.error("Update answer error!");
            throw new TestingRuntimeException("Update answer error!", e);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ANSWER);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            sLogger.error("Delete answer error!");
            throw new TestingRuntimeException("Delete answer error!", e);
        }
    }


    @Override
    public List<Answer> getAnswers() {
        Connection connection = ConnectionContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ANSWERS);
            List<Answer> answers = new ArrayList<>();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setAnswerName(resultSet.getString("answer_name"));
                answer.setCorrectAnswer(resultSet.getBoolean("correct_answer"));
                Question question = new Question();
                question.setId(resultSet.getInt("question_id"));
                answer.setQuestion(question);
                answers.add(answer);
            }
            return answers;
        } catch (SQLException e) {
            sLogger.error("Get answers error!");
            throw new TestingRuntimeException("Get answers error!", e);
        }
    }
}
