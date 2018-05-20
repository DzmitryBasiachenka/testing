package com.bsdim.web.project.service;

import java.util.List;

import com.bsdim.web.project.connection.ConnectionContext;
import com.bsdim.web.project.dao.api.IRoleDao;
import com.bsdim.web.project.dao.sql.RoleDaoSql;
import com.bsdim.web.project.domain.Role;

/**
 * The role service.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class RoleService {
    private IRoleDao dao = new RoleDaoSql();

    /**
     * Addes role.
     *
     * @param role the role.
     * @return the role id.
     */
    public Integer addRole(Role role) {
        try {
            return dao.create(role);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds role by role id.
     *
     * @param id the role id.
     * @return the role
     */
    public Role findById(Integer id) {
        try {
            return dao.read(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Updates role.
     *
     * @param role the role.
     */
    public void updateRole(Role role) {
        try {
            dao.update(role);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Deletes role.
     *
     * @param id the role id.
     */
    public void deleteRole(Integer id) {
        try {
            dao.delete(id);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Gets roles.
     *
     * @return the role list.
     */
    public List<Role> getRoles() {
        try {
            return dao.getRoles();
        } finally {
            ConnectionContext.releaseConnection();
        }
    }

    /**
     * Finds role by role name.
     *
     * @param roleName the role name.
     * @return the role.
     */
    public Role findRoleByRoleName(String roleName) {
        try {
            return dao.findRoleByRoleName(roleName);
        } finally {
            ConnectionContext.releaseConnection();
        }
    }
}
