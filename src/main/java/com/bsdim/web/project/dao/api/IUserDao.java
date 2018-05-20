package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;

/**
 * Represents user dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface IUserDao extends IDao<Integer, User> {
    /**
     * Gets users.
     *
     * @return the userRole list.
     */
    List<UserRole> getUsers();

    /**
     * Finds user by login.
     *
     * @param login the login.
     * @return the user.
     */
    User findByLogin(String login);

    /**
     * Finds user by email.
     *
     * @param email the email.
     * @return the user.
     */
    User findByEmail(String email);

    /**
     * Reads user role by id.
     *
     * @param id the user id.
     * @return the user role.
     */
    UserRole readUserRoleById(Integer id);

    /**
     * Deleted user role.
     *
     * @param userRole the user role.
     */
    void deleteUserRole(UserRole userRole);

    /**
     * Creates user role.
     *
     * @param userRole the user role.
     */
    void createUserRoles(UserRole userRole);
}
