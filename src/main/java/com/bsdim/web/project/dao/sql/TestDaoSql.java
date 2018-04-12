package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionManager;
import com.bsdim.web.project.dao.api.ITestDao;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;

public class TestDaoSql implements ITestDao {
    private static final String CREATE_TEST = "insert into test(test_name, subject_id, user_id) values(?, ?, ?)";
    private static final String READ_TEST = "select id, test_name, subject_id, user_id from test where id = ?";
    private static final String UPDATE_TEST = "update test set test_name = ? where id = ?";
    private static final String DELETE_TEST = "delete from test where id = ?";
    private static final String GET_TESTS = "select id, test_name, subject_id, user_id from test order by id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public void create(Test test) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TEST);
            preparedStatement.setString(PARAMETER_INDEX_ONE, test.getTestName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, test.getSubject().getId());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, test.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//e.printStackTrace();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public Test read(Integer id) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_TEST);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
                return test;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public void update(Test test) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEST);
            preparedStatement.setString(PARAMETER_INDEX_ONE, test.getTestName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, test.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//e.printStackTrace();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEST);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }


    @Override
    public List<Test> getTests() {
        Connection connection = connectionManager.getConnection();
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
        } finally {
            connectionManager.putConnection(connection);
        }
    }
}
