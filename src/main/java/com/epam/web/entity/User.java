package com.epam.web.entity;

import java.util.Objects;

public class User implements Identifiable {
    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "password";
    public static final String DISCOUNT = "discount";

    private Long id;
    private String login;
    private String password;
    private Role role;
    private int discount;

    public User() {
    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(Long id, String login, String password, Role role, int discount) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return discount == user.discount && Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, discount);
    }
}
