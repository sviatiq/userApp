package domain;

import java.util.Set;

public class User {
    private String name;
    private String surname;
    private String email;
    private Set<Role> roles;
    private String cellphone;

    public User() {
    }

    public User(String name, String surname, String email, Set<Role> roles, String cellphone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.cellphone = cellphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}