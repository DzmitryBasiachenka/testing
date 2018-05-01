package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;

public interface IUserDao extends IDao<Integer, User> {
    List<User> getUsers();
    User findByLogin(String login);
    UserRole readUserRoleById(Integer id);
    void deleteUserRole(Integer id);
    void createUserRole(UserRole userRole);
}
