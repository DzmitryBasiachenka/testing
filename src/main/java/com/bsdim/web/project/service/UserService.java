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
import com.bsdim.web.project.exception.TestingRuntimeException;
import org.apache.log4j.Logger;

/**
 * The user service.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class UserService {
    private static final String DEFAULT_ROLE_USER = "User";

    private static Logger sLogger = Logger.getLogger(UserService.class);

    private IUserDao userDao = new UserDaoSql();
    private IRoleDao roleDao = new RoleDaoSql();

    /**
     * Adds user.
     *
     * @param user the user.
     */
    public void addUser(User user) {
        Connection connection = ConnectionContext.getConnection();
        try {
            connection.setAutoCommit(false);
            Integer userId = userDao.create(user);
            Role role = roleDao.findRoleByRoleName(DEFAULT_ROLE_USER);
            if (role != null) {
                List<Role> roles = new ArrayList<>();
                roles.add(role);

                UserRole userRole = new UserRole();
                userRole.setId(userId);
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
                sLogger.error("Add user connection rollback error!");
                throw new TestingRuntimeException("Add user connection rollback error!", e);
            }
            sLogger.error("Add user error!");
            throw new TestingRuntimeException("Add user error!", e);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds user by user id.
     *
     * @param id the user id.
     * @return the user.
     */
    public User findById(Integer id) {
        try {
            return userDao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Updates user.
     *
     * @param user the user.
     */
    public void updateUser(User user) {
        try {
            userDao.update(user);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes user.
     *
     * @param id the user id.
     */
    public void deleteUser(Integer id) {
        try {
            userDao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets users.
     *
     * @return the user list.
     */
    public List<UserRole> getUsers() {
        try {
            return userDao.getUsers();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds user by login.
     *
     * @param login the login.
     * @return the user.
     */
    public User findByLogin(String login) {
        try {
            return userDao.findByLogin(login);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds user by email.
     *
     * @param email the email.
     * @return the user.
     */
    public User findByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Reads user role by user id.
     *
     * @param id the user id.
     * @return the user role.
     */
    public UserRole readUserRoleById(Integer id) {
        try {
            return userDao.readUserRoleById(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes user role.
     *
     * @param userRole the user role.
     */
    public void deleteUserRole(UserRole userRole) {
        try {
            userDao.deleteUserRole(userRole);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Creates user role.
     *
     * @param userRole the user role.
     */
    public void createUserRoles(UserRole userRole) {
        try {
            userDao.createUserRoles(userRole);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
