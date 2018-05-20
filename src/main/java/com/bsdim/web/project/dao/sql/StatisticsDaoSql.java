package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IStatisticsDao;
import com.bsdim.web.project.domain.Statistics;
import com.bsdim.web.project.domain.Subject;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

public class StatisticsDaoSql implements IStatisticsDao {
    private static final String CREATE_STATISTICS = "insert into statistics(test_id, count_correct_answers, count_incorrect_answers, start_testing, finish_testing, user_id) values(?, ?, ?, ?, ?, ?)";
    private static final String READ_STATISTICS = "select id, test_id, count_correct_answers, count_incorrect_answers, start_testing, finish_testing, user_id from statistics where id = ?";
    private static final String UPDATE_STATISTICS = "update statistics set count_correct_answers = ?, count_incorrect_answers = ? where id = ?";
    private static final String DELETE_STATISTICS = "delete from statistics where id = ?";
    private static final String GET_STATISTICS_LIST = "select test.id, test.test_name, subject.id, subject.subject_name, " +
            "statistics.id, statistics.count_correct_answers, statistics.count_incorrect_answers, statistics.start_testing, " +
            "statistics.finish_testing from users join statistics on users.id = statistics.user_id join test on " +
            "test.id = statistics.test_id join subject on subject.id = test.subject_id where users.id = ? order by statistics.id";

    private static final String GET_STUDENTS_STATTISTICS_BY_TEST_ID = "select users.id, users.login, users.first_name, users.last_name, " +
            "test.id, test.test_name, subject.id, subject.subject_name, statistics.id, statistics.count_correct_answers, " +
            "statistics.count_incorrect_answers, statistics.start_testing, statistics.finish_testing from users join " +
            "statistics on users.id = statistics.user_id join test on test.id = statistics.test_id join subject on " +
            "subject.id = test.subject_id where test.id = ? order by users.id";

    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;
    private static final int PARAMETER_INDEX_FOUR = 4;
    private static final int PARAMETER_INDEX_FIVE = 5;
    private static final int PARAMETER_INDEX_SIX = 6;

    private static Logger sLogger = Logger.getLogger(StatisticsDaoSql.class);

    @Override
    public Integer create(Statistics statistics) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STATISTICS, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, statistics.getTest().getId());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, statistics.getCountCorrectAnswers());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, statistics.getCountIncorrectAnswers());
            preparedStatement.setTimestamp(PARAMETER_INDEX_FOUR, new Timestamp(statistics.getStartTesting().getTime()));
            preparedStatement.setTimestamp(PARAMETER_INDEX_FIVE, new Timestamp(statistics.getFinishTesting().getTime()));
            preparedStatement.setInt(PARAMETER_INDEX_SIX, statistics.getUser().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            sLogger.error("Create statistics error!");
            throw new TestingRuntimeException("Create statistics error!", e);
        }
    }

    @Override
    public Statistics read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
            sLogger.error("Read statistics error!");
            throw new TestingRuntimeException("Read statistics error!", e);
        }
    }

    @Override
    public void update(Statistics statistics) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, statistics.getCountCorrectAnswers());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, statistics.getCountIncorrectAnswers());
            preparedStatement.setInt(PARAMETER_INDEX_THREE, statistics.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            sLogger.error("Update statistics error!");
            throw new TestingRuntimeException("Update statistics error!", e);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATISTICS);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            sLogger.error("Delete statistics error!");
            throw new TestingRuntimeException("Delete statistics error!", e);
        }
    }

    @Override
    public List<Statistics> getStatisticsListByUserId(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STATISTICS_LIST);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Statistics> statisticsList = new ArrayList<>();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getInt("test.id"));
                test.setTestName(resultSet.getString("test.test_name"));

                Subject subject = new Subject();
                subject.setId(resultSet.getInt("subject.id"));
                subject.setSubjectName(resultSet.getString("subject.subject_name"));

                test.setSubject(subject);

                Statistics statistics = new Statistics();
                statistics.setId(resultSet.getInt("statistics.id"));
                statistics.setCountCorrectAnswers(resultSet.getInt("statistics.count_correct_answers"));
                statistics.setCountIncorrectAnswers(resultSet.getInt("statistics.count_incorrect_answers"));
                statistics.setStartTesting(resultSet.getTimestamp("statistics.start_testing"));
                statistics.setFinishTesting(resultSet.getTimestamp("statistics.finish_testing"));
                statistics.setTest(test);

                statisticsList.add(statistics);
            }
            return statisticsList;
        } catch (SQLException e) {
            sLogger.error("Get statistics list by user id error!");
            throw new TestingRuntimeException("Get statistics list by user id error!", e);
        }
    }

    @Override
    public List<Statistics> getStudentStatisticsByTestId(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENTS_STATTISTICS_BY_TEST_ID);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Statistics> statisticsList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("users.id"));
                user.setLogin(resultSet.getString("users.login"));
                user.setFirstName(resultSet.getString("users.first_name"));
                user.setLastName(resultSet.getString("users.last_name"));

                Test test = new Test();
                test.setId(resultSet.getInt("test.id"));
                test.setTestName(resultSet.getString("test.test_name"));

                Subject subject = new Subject();
                subject.setId(resultSet.getInt("subject.id"));
                subject.setSubjectName(resultSet.getString("subject.subject_name"));

                test.setSubject(subject);

                Statistics statistics = new Statistics();
                statistics.setId(resultSet.getInt("statistics.id"));
                statistics.setCountCorrectAnswers(resultSet.getInt("statistics.count_correct_answers"));
                statistics.setCountIncorrectAnswers(resultSet.getInt("statistics.count_incorrect_answers"));
                statistics.setStartTesting(resultSet.getTimestamp("statistics.start_testing"));
                statistics.setFinishTesting(resultSet.getTimestamp("statistics.finish_testing"));
                statistics.setTest(test);

                statistics.setUser(user);
                statistics.setTest(test);

                statisticsList.add(statistics);
            }
            return statisticsList;
        } catch (SQLException e) {
            sLogger.error("Get student statistics by test id error!");
            throw new TestingRuntimeException("Get student statistics by test id error!", e);
        }
    }
}
