package com.epam.web.entity;

public class Exercise implements Identifiable {
    public static final String TABLE = "exercise";
    public static final String ID = "id";
    public static final String NAME = "name";

    private Long id;
    private String name;

    public Exercise() {
    }

    public Exercise(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
