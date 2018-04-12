package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionManager;
import com.bsdim.web.project.dao.api.IUserDao;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.User;

public class UserDaoSql implements IUserDao {
    private static final String CREATE_USER = "insert into users(login, password, first_name, last_name, role_id) values(?, ?, ?, ?, ?)";
    private static final String READ_USER = "select id, login, password, first_name, last_name, role_id from users where id = ?";
    private static final String UPDATE_USER = "update users set login = ?, password = ?, first_name = ?, last_name = ?, role_id = ? where id = ?";
    private static final String DELETE_USER = "delete from users where id = ?";
    private static final String GET_USERS = "select id, login, password, first_name, last_name, role_id from users order by id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;
    private static final int PARAMETER_INDEX_FOUR = 4;
    private static final int PARAMETER_INDEX_FIVE = 5;
    private static final int PARAMETER_INDEX_SIX = 6;

    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public void create(User user) {
        Connection connection = connectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getLogin());
            preparedStatement.setString(PARAMETER_INDEX_TWO, user.getPassword());
            preparedStatement.setString(PARAMETER_INDEX_THREE, user.getFirstName());
            preparedStatement.setString(PARAMETER_INDEX_FOUR, user.getLastName());
            preparedStatement.setInt(PARAMETER_INDEX_FIVE, user.getRole().ordinal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public User read(Integer id) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USER);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(Role.values()[resultSet.getInt("role_id")]);
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getLogin());
            preparedStatement.setString(PARAMETER_INDEX_TWO, user.getPassword());
            preparedStatement.setString(PARAMETER_INDEX_THREE, user.getFirstName());
            preparedStatement.setString(PARAMETER_INDEX_FOUR, user.getLastName());
            preparedStatement.setInt(PARAMETER_INDEX_FIVE, user.getRole().getId());
            preparedStatement.setInt(PARAMETER_INDEX_SIX, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();//throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }

    @Override
    public List<User> getUsers() {
        Connection connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_USERS);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole((Role.values()[resultSet.getInt("role_id")]));
                users.add(user);
            }
            resultSet.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        } finally {
            connectionManager.putConnection(connection);
        }
    }
}
