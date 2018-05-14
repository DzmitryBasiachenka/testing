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
import com.bsdim.web.project.dao.api.IRoleDao;
import com.bsdim.web.project.domain.Question;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.Test;

public class RoleDaoSql implements IRoleDao {
    private static final String CREATE_ROLE = "insert into role(role_name) values(?)";
    private static final String READ_ROLE = "select id, role_name from role where id = ?";
    private static final String UPDATE_ROLE = "update role set role_name = ? where id = ?";
    private static final String DELETE_ROLE = "delete from role where id = ?";
    private static final String FIND_ID_ROLE_BY_ROLE_NAME = "select id, role_name from role where role_name = ?";
    private static final String GET_ROLES = "select id, role_name from role order by id";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;

    @Override
    public Integer create(Role role) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ROLE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(PARAMETER_INDEX_ONE, role.getRoleName());
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
    public Role read(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ROLE);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("role_name"));
                return role;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Role role) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE);
            preparedStatement.setString(PARAMETER_INDEX_ONE, role.getRoleName());
            preparedStatement.setInt(PARAMETER_INDEX_TWO, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE);
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        Connection connection = ConnectionContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID_ROLE_BY_ROLE_NAME);
            preparedStatement.setString(PARAMETER_INDEX_ONE, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("role_name"));
            }
            return role;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        Connection connection = ConnectionContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ROLES);
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("role_name"));
                roles.add(role);
            }
            resultSet.close();
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException();//throw new RepositoryException(e);
        }
    }
}
