package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.IUserDao;
import com.bsdim.web.project.dao.sql.UserDaoSql;
import com.bsdim.web.project.domain.User;

public class UserService {
    private IUserDao dao = new UserDaoSql();

    public void addUser(User user) {
        dao.create(user);
    }

    public User findById(Integer id) {
        return dao.read(id);
    }

    public void updateUser(User user) {
        dao.update(user);
    }

    public void deleteUser(Integer id) {
        dao.delete(id);
    }

    public List<User> getUsers() {
        return dao.getUsers();
    }
}
