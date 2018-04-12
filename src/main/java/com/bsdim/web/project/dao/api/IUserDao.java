package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.User;

public interface IUserDao extends IDao<Integer, User> {
    List<User> getUsers();
}
