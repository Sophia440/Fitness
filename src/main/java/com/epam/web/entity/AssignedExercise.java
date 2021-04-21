package com.epam.web.entity;

import java.time.LocalDate;

public class AssignedExercise implements Identifiable {
    public static final String TABLE = "assigned_exercise";
    public static final String ID = "id";
    public static final String PROGRAM_ID = "program_id";
    public static final String EXERCISE_ID = "exercise_id";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";

    private Long id;
    private Long programId;
    private Long exerciseId;
    private LocalDate startDate;
    private LocalDate endDate;

    public AssignedExercise() {
    }

    public AssignedExercise(Long id, Long programId, Long exerciseId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.programId = programId;
        this.exerciseId = exerciseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public Long getId() {
        return id;
    }
}
