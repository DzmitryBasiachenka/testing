package com.bsdim.web.project;

import java.util.ArrayList;
import java.util.List;

import com.bsdim.web.project.dao.sql.TestDaoSql;
import com.bsdim.web.project.domain.Role;
import com.bsdim.web.project.domain.Test;
import com.bsdim.web.project.domain.User;
import com.bsdim.web.project.domain.UserRole;
import com.bsdim.web.project.service.RoleService;
import com.bsdim.web.project.service.UserService;
import com.bsdim.web.project.session.UserSession;

public class Main {
    public static void main(String[] args) {
        /*TestDaoSql testDaoSql = new TestDaoSql();
        for (Test test: testDaoSql.getTests()) {
            System.out.println(test);
        }*/

        UserService service = new UserService();

        UserRole userRole = new UserRole();
        userRole.setId(4);
        Role role = new Role();
        role.setId(4);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        userRole.setRoles(roles);

        service.createUserRole(userRole);
        /*UserRole userRole = service.readUserRoleById(3);


        System.out.println(userRole.getId());
        System.out.println(userRole.getLogin());
        System.out.println(userRole.getEmail());
        System.out.println(userRole.getFirstName());
        System.out.println(userRole.getLastName());
        for (Role role: userRole.getRoles()) {
            System.out.println(role.getId());
            System.out.println(role.getRoleName());
        }*/

        //service.deleteUserRole(4);
    }
}
