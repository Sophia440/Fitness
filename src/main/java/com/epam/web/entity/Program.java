package com.epam.web.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The Program entity.
 */
public class Program implements Identifiable {
    public static final String TABLE = "program";
    public static final String ID = "id";
    public static final String INSTRUCTOR_ID = "instructor_id";
    public static final String CLIENT_ID = "client_id";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String STATUS = "status";

    private Long id;
    private Long instructorId;
    private Long clientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private List<Exercise> exercises;

    public Program() {
    }

    public Program(Long id, Long instructorId, Long clientId, LocalDate startDate, LocalDate endDate, Status status) {
        this.id = id;
        this.instructorId = instructorId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Program(Long id, Long instructorId, Long clientId, LocalDate startDate, LocalDate endDate, Status status, List<Exercise> exercises) {
        this.id = id;
        this.instructorId = instructorId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.exercises = exercises;
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
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", exercises=" + exercises +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Program program = (Program) object;
        return Objects.equals(id, program.id) && Objects.equals(instructorId, program.instructorId) && Objects.equals(clientId, program.clientId) && Objects.equals(startDate, program.startDate) && Objects.equals(endDate, program.endDate) && status == program.status && Objects.equals(exercises, program.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instructorId, clientId, startDate, endDate, status, exercises);
    }
}
