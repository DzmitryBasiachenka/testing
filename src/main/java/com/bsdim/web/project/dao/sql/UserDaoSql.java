package com.bsdim.web.project.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IUserDao;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;

public class UserDaoSql implements IUserDao {
    private static final String CREATE_USER = "insert into users(login, password, email, first_name, last_name) values(?, ?, ?, ?, ?)";
    private static final String READ_USER = "select id, login, password, email, first_name, last_name from users where id = ?";
    private static final String UPDATE_USER = "update users set login = ?, password = ?, email = ?, first_name = ?, last_name = ? where id = ?";
    private static final String DELETE_USER = "delete from users where id = ?";
    //private static final String GET_USERS = "select id, login, password, email, first_name, last_name from users order by id";
    private static final String FIND_BY_LOGIN = "select id, login, password, email, first_name, last_name from users where login = ?";
    private static final String FIND_BY_EMAIL = "select id, login, password, email, first_name, last_name from users where email = ?";
    private static final String GET_USER_WITH_ROLES = "select * from user_role join users on users.id = user_role.user_id join " +
            "role on role.id = user_role.role_id where users.id = ?";
    private static final String DELETE_USER_ROLE = "delete from user_role where user_id = ?";
    private static final String CREATE_USER_ROLE = "insert into user_role(user_id, role_id) values(?, ?)";

    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;
    private static final int PARAMETER_INDEX_FOUR = 4;
    private static final int PARAMETER_INDEX_FIVE = 5;
    private static final int PARAMETER_INDEX_SIX = 6;

    @Override
    public Integer create(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getLogin());
            preparedStatement.setString(PARAMETER_INDEX_TWO, user.getPassword());
            preparedStatement.setString(PARAMETER_INDEX_THREE, user.getEmail());
            preparedStatement.setString(PARAMETER_INDEX_FOUR, user.getFirstName());
            preparedStatement.setString(PARAMETER_INDEX_FIVE, user.getLastName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public User read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USER);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getLogin());
            preparedStatement.setString(PARAMETER_INDEX_TWO, user.getPassword());
            preparedStatement.setString(PARAMETER_INDEX_THREE, user.getEmail());
            preparedStatement.setString(PARAMETER_INDEX_FOUR, user.getFirstName());
            preparedStatement.setString(PARAMETER_INDEX_FIVE, user.getLastName());
            preparedStatement.setInt(PARAMETER_INDEX_SIX, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();//throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public List<User> getUsers() {
//        Connection connection = ConnectionContext.getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(GET_USERS);
//            List<User> users = new ArrayList<>();
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPassword(resultSet.getString("password"));
//                user.setEmail(resultSet.getString("email"));
//                user.setFirstName(resultSet.getString("first_name"));
//                user.setLastName(resultSet.getString("last_name"));
//                users.add(user);
//            }
//            resultSet.close();
//            return users;
//        } catch (SQLException e) {
//            throw new RuntimeException();//throw new RepositoryException(e);
//        }
//    }

    @Override
    public User findByLogin(String login) {
        return readData(FIND_BY_LOGIN, login);
    }

    @Override
    public User findByEmail(String email) {
        return readData(FIND_BY_EMAIL, email);
    }

    private User readData(String request, String data) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(PARAMETER_INDEX_ONE, data);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    public UserRole readUserRoleById(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_WITH_ROLES);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserRole userRole = new UserRole();
            List<Role> roles = new ArrayList<>();

            if (resultSet.next()) {
                userRole.setId(resultSet.getInt("users.id"));
                userRole.setLogin(resultSet.getString("users.login"));
                userRole.setEmail(resultSet.getString("users.email"));
                userRole.setFirstName(resultSet.getString("users.first_name"));
                userRole.setLastName(resultSet.getString("users.last_name"));

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Role role = new Role();
                    role.setId(resultSet.getInt("role.id"));
                    role.setRoleName(resultSet.getString("role.role_name"));
                    roles.add(role);
                }
                userRole.setRoles(roles);
                return userRole;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteUserRole(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_ROLE);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUserRoles(UserRole userRole) {
        Connection connection = ConnectionContext.getConnection();
        try {
            List<Role> roles = userRole.getRoles();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_ROLE);
            for (Role role : roles) {
                preparedStatement.setInt(PARAMETER_INDEX_ONE, role.getId());
                preparedStatement.setInt(PARAMETER_INDEX_TWO, role.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
