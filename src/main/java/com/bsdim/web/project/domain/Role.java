package com.bsdim.web.project.domain;

public class Role extends Entity {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return new StringBuilder("roleId: ")
                .append(getId())
                .append(" roleName: ")
                .append(roleName).toString();
    }
}
