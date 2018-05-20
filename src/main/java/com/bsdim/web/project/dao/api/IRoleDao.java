package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Role;

/**
 * Represents role dao interface.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public interface IRoleDao extends IDao<Integer, Role> {
    /**
     * Gets roles.
     *
     * @return the list of roles.
     */
    List<Role> getRoles();

    /**
     * Finds role by role name.
     *
     * @param roleName the role name.
     * @return the role.
     */
    Role findRoleByRoleName(String roleName);
}
