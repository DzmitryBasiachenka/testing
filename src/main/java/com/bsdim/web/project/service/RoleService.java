package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IRoleDao;
import com.bsdim.web.project.dao.sql.RoleDaoSql;
import com.bsdim.web.project.domain.Role;

public class RoleService {
    private IRoleDao dao = new RoleDaoSql();

    public void addRole(Role role) {
        try {
            dao.create(role);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Role findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void updateRole(Role role) {
        try {
            dao.update(role);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public void deleteRole(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public List<Role> getRoles() {
        try {
            return dao.getRoles();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    public Role findRoleByRoleName(String roleName) {
        try {
            return dao.findRoleByRoleName(roleName);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
