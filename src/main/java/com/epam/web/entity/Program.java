package com.epam.web.entity;

import java.util.List;

public class Program implements Identifiable {
    public static final String TABLE = "program";
    public static final String ID = "id";
    public static final String INSTRUCTOR_ID = "instructor_id";
    public static final String CLIENT_ID = "client_id";
    public static final String STATUS = "status";

    private Long id;
    private Long instructorId;
    private Long clientId;
    private Status status;
    private List<Exercise> exercises;

    public Program() {
    }

    public Program(Long id, Long instructorId, Long clientId, Status status) {
        this.id = id;
        this.instructorId = instructorId;
        this.clientId = clientId;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", instructorId=" + instructorId +
                ", clientId=" + clientId +
                ", status=" + status +
                ", exercises=" + exercises +
                '}';
    }
}
