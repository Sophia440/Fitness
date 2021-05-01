package com.epam.web.entity;

import java.time.LocalDate;

public class Membership implements Identifiable {
    public static final String TABLE = "membership";
    public static final String ID = "id";
    public static final String CLIENT_ID = "client_id";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String PAYMENT_DATE = "payment_date";

    private Long id;
    private Long clientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate paymentDate;

    public Membership() {
    }

    public Membership(Long id, Long clientId, LocalDate startDate, LocalDate endDate, LocalDate paymentDate) {
        this.id = id;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentDate = paymentDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
