package com.bsdim.web.project.domain;

public class User extends Entity {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
         return new StringBuilder("userId: ")
                 .append(getId())
                 .append(" login: ")
                 .append(login)
                 .append(" password: ")
                 .append(password)
                 .append(" email: ")
                 .append(email)
                 .append(" firstName: ")
                 .append(firstName)
                 .append(" lastName: ")
                 .append(lastName).toString();
    }
}
