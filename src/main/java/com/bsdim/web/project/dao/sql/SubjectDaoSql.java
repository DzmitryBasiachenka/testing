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
import com.bsdim.web.project.dao.api.ISubjectDao;
import com.bsdim.web.project.domain.Subject;

public class SubjectDaoSql implements ISubjectDao {
    private static final String CREATE_SUBJECT = "insert into subject(subject_name) values(?)";
    private static final String READ_SUBJECT = "select id, subject_name from subject where id = ?";
    private static final String UPDATE_SUBJECT = "update subject set subject_name = ? where id = ?";
    private static final String DELETE_SUBJECT = "delete from subject where id = ?";
    private static final String GET_SUBJECTS = "select id, subject_name from subject order by id";
    private static final String FIND_SUBJECT_BY_NAME = "select id, subject_name from subject where subject_name = ?";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;

    @Override
    public Integer create(Subject subject) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(PARAMETER_INDEX_ONE, subject.getSubjectName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Subject read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_SUBJECT);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setSubjectName(resultSet.getString("subject_name"));
                return subject;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Subject subject) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUBJECT);
            preparedStatement.setString(PARAMETER_INDEX_ONE, subject.getSubjectName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, subject.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public List<Subject> getSubjects() {
        Connection connection = ConnectionContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_SUBJECTS);
            List<Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setSubjectName(resultSet.getString("subject_name"));
                subjects.add(subject);
            }
            resultSet.close();
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public Subject findSubjectByName(String subjectName) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUBJECT_BY_NAME);
            preparedStatement.setString(PARAMETER_INDEX_ONE, subjectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setSubjectName(resultSet.getString("subject_name"));
                return subject;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }
}
