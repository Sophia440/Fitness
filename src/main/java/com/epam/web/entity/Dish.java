package com.epam.web.entity;

import java.util.Objects;

public class Dish implements Identifiable {
    public static final String TABLE = "dish";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MEAL = "meal";

    private Long id;
    private String name;
    private Meal meal;

    public Dish() {
    }

    public Dish(Long id, String name, Meal meal) {
        this.id = id;
        this.name = name;
        this.meal = meal;
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

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meal=" + meal +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Dish dish = (Dish) object;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && meal == dish.meal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, meal);
    }
}