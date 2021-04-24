package com.epam.web.entity;

public class AssignedDish implements Identifiable {
    public static final String TABLE = "assigned_dish";
    public static final String ID = "id";
    public static final String DIET_ID = "diet_id";
    public static final String DISH_ID = "dish_id";

    private Long id;
    private Long dietId;
    private Long dishId;

    public AssignedDish() {
    }

    public AssignedDish(Long id, Long dietId, Long dishId) {
        this.id = id;
        this.dietId = dietId;
        this.dishId = dishId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getDietId() {
        return dietId;
    }

    public void setDietId(Long dietId) {
        this.dietId = dietId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    @Override
    public String toString() {
        return "AssignedDish{" +
                "id=" + id +
                ", dietId=" + dietId +
                ", dishId=" + dishId +
                '}';
    }
}