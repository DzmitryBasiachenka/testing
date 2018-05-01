package com.bsdim.web.project.dao.api;

import java.util.List;

import com.bsdim.web.project.domain.Role;

public interface IRoleDao extends IDao<Integer, Role> {
    List<Role> getRoles();
}
