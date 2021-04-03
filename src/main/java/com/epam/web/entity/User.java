package com.epam.web.entity;

public class User implements Identifiable{
    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";

    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return null;
    }
}
