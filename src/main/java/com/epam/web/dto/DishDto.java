package com.epam.web.dto;

import com.epam.web.entity.Meal;

public class DishDto {
    private Long id;
    private String name;
    private Meal meal;

    public Long getId() {
        return id;
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

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public static class Builder {
        private DishDto newDish;

        public Builder() {
            this.newDish = new DishDto();
        }

        public Builder id(Long id) {
            newDish.id = id;
            return this;
        }

        public Builder name(String name) {
            newDish.name = name;
            return this;
        }

        public Builder meal(Meal meal) {
            newDish.meal = meal;
            return this;
        }

        public DishDto build() {
            return newDish;
        }
    }
}
