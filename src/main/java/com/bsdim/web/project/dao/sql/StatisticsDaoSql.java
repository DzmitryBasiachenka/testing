package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionManager;
import com.bsdim.web.project.dao.api.IStatisticsDao;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;

public class StatisticsDaoSql implements IStatisticsDao {
    private static final String CREATE_STATISTICS = "insert into statistics(test_id, count_correct_answers, count_incorrect_answers, start_testing, finish_testing, user_id) values(?, ?, ?, ?, ?, ?)";
    private static final String READ_STATISTICS = "select id, test_id, count_correct_answers, count_incorrect_answers, start_testing, finish_testing, user_id from statistics where id = ?";
    private static final String UPDATE_STATISTICS = "update statistics set count_correct_answers = ?, count_incorrect_answers = ? where id = ?";
    private static final String DELETE_STATISTICS = "delete from statistics where id = ?";
    private static final String GET_STATISTICS_LIST = "select id, test_id, count_correct_answers, count_incorrect_answers, start_testing, finish_testing, user_id from statistics order by id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;
    private static final int PARAMETER_INDEX_FOUR = 4;
    private static final int PARAMETER_INDEX_FIVE = 5;
    private static final int PARAMETER_INDEX_SIX = 6;
    //private static final String FIND_BY_USERID = "select id, title, text, user_id from article where user_id = ?";

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public void create(Statistics statistics) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, statistics.getTest().getId());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, statistics.getCountCorrectAnswers());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, statistics.getCountIncorrectAnswers());
            preparedStatement.setTimestamp(PARAMETER_INDEX_FOUR, new Timestamp(statistics.getStartTesting().getTime()));
            preparedStatement.setTimestamp(PARAMETER_INDEX_FIVE, new Timestamp(statistics.getFinishTesting().getTime()));
            preparedStatement.setInt(PARAMETER_INDEX_SIX, statistics.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public Statistics read(Integer id) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Statistics statistics = new Statistics();
                statistics.setId(resultSet.getInt("id"));
                Test test = new Test();
                test.setId(resultSet.getInt("test_id"));
                statistics.setTest(test);
                statistics.setCountCorrectAnswers(resultSet.getInt("count_correct_answers"));
                statistics.setCountIncorrectAnswers(resultSet.getInt("count_incorrect_answers"));
                statistics.setStartTesting(resultSet.getTimestamp("start_testing"));
                statistics.setFinishTesting(resultSet.getTimestamp("finish_testing"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                statistics.setUser(user);
                return statistics;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public void update(Statistics statistics) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, statistics.getCountCorrectAnswers());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, statistics.getCountIncorrectAnswers());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, statistics.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }


    @Override
    public List<Statistics> getStatisticsList() {
        Connection connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_STATISTICS_LIST);
            List<Statistics> statisticsList = new ArrayList<>();
            while (resultSet.next()) {
                Statistics statistics = new Statistics();
                statistics.setId(resultSet.getInt("id"));
                Test test = new Test();
                test.setId(resultSet.getInt("test_id"));
                statistics.setTest(test);
                statistics.setCountCorrectAnswers(resultSet.getInt("count_correct_answers"));
                statistics.setCountIncorrectAnswers(resultSet.getInt("count_incorrect_answers"));
                statistics.setStartTesting(resultSet.getTimestamp("start_testing"));
                statistics.setFinishTesting(resultSet.getTimestamp("finish_testing"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                statistics.setUser(user);
                statisticsList.add(statistics);
            }
            resultSet.close();
            return statisticsList;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }
}
