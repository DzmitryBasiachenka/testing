package com.bsdim.web.project.domain;

import java.util.List;

public class UserRole extends User {
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
