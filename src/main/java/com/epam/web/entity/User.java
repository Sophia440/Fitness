package com.epam.web.entity;

import java.math.BigDecimal;

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
    private BigDecimal discount;

    public User() {
    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(Long id, String login, String password, Role role, BigDecimal discount) {
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
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
}
