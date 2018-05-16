package com.bsdim.web.project.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IRoleDao;
import com.bsdim.web.project.dao.api.IUserDao;
import com.bsdim.web.project.dao.sql.RoleDaoSql;
import com.bsdim.web.project.dao.sql.UserDaoSql;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;

public class UserService {
    private static final String DEFAULT_ROLE_USER = "User";

    private IUserDao userDao = new UserDaoSql();
    private IRoleDao roleDao = new RoleDaoSql();

    public void addUser(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            connection.setAutoCommit(false);
            Integer id = userDao.create(user);
            Role role = roleDao.findRoleByRoleName(DEFAULT_ROLE_USER);
            if (role != null) {
                List<Role> roles = new ArrayList<>();
                roles.add(role);

                UserRole userRole = new UserRole();
                userRole.setId(id);
                userRole.setRoles(roles);

                userDao.createUserRoles(userRole);

                connection.commit();
                connection.setAutoCommit(true);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public User findById(Integer id) {
        try {
            return userDao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateUser(User user) {
        try {
            userDao.update(user);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteUser(Integer id) {
        try {
            userDao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<UserRole> getUsers() {
        try {
            return userDao.getUsers();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public User findByLogin(String login) {
        try {
            return userDao.findByLogin(login);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public User findByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public UserRole readUserRoleById(Integer id) {
        try {
            return userDao.readUserRoleById(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteUserRole(UserRole userRole) {
        try {
            userDao.deleteUserRole(userRole);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void createUserRoles(UserRole userRole) {
        try {
            userDao.createUserRoles(userRole);
        } finally {
            ConnectionContext.releaseConnection();
        }

    }
}
