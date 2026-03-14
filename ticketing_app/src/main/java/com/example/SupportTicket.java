package com.example;

import java.time.LocalDate;

public class SupportTicket {
    private final long id;
    private final String title;
    private final String customerName;
    private final String priority;
    private final LocalDate createdAt;
    private final String description;
    private final boolean urgent;
    private final String status;

    public SupportTicket(long id, String title, String customerName, String priority, LocalDate createdAt, String description, boolean urgent, String status) {
        this.id = id;
        this.title = title;
        this.customerName = customerName;
        this.priority = priority;
        this.createdAt = createdAt;
        this.description = description;
        this.urgent = urgent;
        this.status = status;
    }

    public SupportTicket(String title, String customerName, String priority, LocalDate createdAt, String description, boolean urgent, String status) {
        this(0L, title, customerName, priority, createdAt, description, urgent, status);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SupportTicket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", customerName='" + customerName + '\'' +
                ", priority='" + priority + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", urgent=" + urgent +
                ", status='" + status + '\'' +
                '}';
    }

    public SupportTicket withId(long id) {
        return new SupportTicket(id, this.title, this.customerName, this.priority, this.createdAt, this.description, this.urgent, this.status);
    }
}