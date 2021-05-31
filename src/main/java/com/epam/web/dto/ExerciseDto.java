package com.epam.web.dto;

/**
 * A data transfer object for the Exercise class.
 *
 */
public class ExerciseDto {

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
        private ExerciseDto newExercise;

        public Builder() {
            this.newExercise = new ExerciseDto();
        }

        public Builder id(Long id) {
            newExercise.id = id;
            return this;
        }

        public Builder name(String name) {
            newExercise.name = name;
            return this;
        }

        public ExerciseDto build() {
            return newExercise;
        }
    }
}
