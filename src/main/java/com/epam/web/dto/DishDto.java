package com.epam.web.dto;

public class DishDto {
    private Long id;
    private String name;

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

        public DishDto build() {
            return newDish;
        }
    }
}
