package com.epam.web.entity;

import java.time.LocalDate;
import java.util.List;

public class Diet implements Identifiable {
    public static final String TABLE = "diet";
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
    private List<Dish> dishes;

    public Diet() {
    }

    public Diet(Long id, Long instructorId, Long clientId, LocalDate startDate, LocalDate endDate, Status status) {
        this.id = id;
        this.instructorId = instructorId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "id=" + id +
                ", instructorId=" + instructorId +
                ", clientId=" + clientId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", dishes=" + dishes +
                '}';
    }
}
