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
import com.bsdim.web.project.dao.api.IQuestionDao;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Test;

public class QuestionDaoSql implements IQuestionDao {
    private static final String CREATE_QUESTION = "insert into question(question_name, test_id) values(?, ?)";
    private static final String READ_QUESTION = "select id, question_name, test_id from question where id = ?";
    private static final String UPDATE_QUESTION = "update question set question_name = ? where id = ?";
    private static final String DELETE_QUESTION = "delete from question where id = ?";
    private static final String GET_QUESTIONS = "select id, question_name, test_id from question order by id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    //private static final int PARAMETER_INDEX_THREE = 3;

    @Override
    public void create(Question question) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUESTION);
            preparedStatement.setString(PARAMETER_INDEX_ONE, question.getQuestionName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, question.getTest().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    @Override
    public Question read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_QUESTION);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
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
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
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
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
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
            resultSet.close();
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
