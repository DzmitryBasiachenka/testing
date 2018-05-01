package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.dao.api.IRoleDao;
import com.bsdim.web.project.dao.sql.RoleDaoSql;
import com.bsdim.web.project.domain.Role;

public class RoleService {
    private IRoleDao dao = new RoleDaoSql();

    public void addRole(Role role) {
        dao.create(role);
    }

    public Role findById(Integer id) {
        return dao.read(id);
    }

    public void updateRole(Role role) {
        dao.update(role);
    }

    public void deleteRole(Integer id) {
        dao.delete(id);
    }

    public List<Role> getRoles() {
        return dao.getRoles();
    }
}
