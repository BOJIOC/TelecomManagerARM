package com.example.telecommanager;

public class FaultReport {
    private int id;
    private String description;
    private String status;
    private long timestamp;

    // Конструктор с тремя параметрами
    public FaultReport(String description, String status, long timestamp) {
        this.description = description;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Конструктор с четырьмя параметрами
    public FaultReport(int id, String description, String status, long timestamp) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.timestamp = timestamp;
    }

    public FaultReport() {
        this.description = "";
        this.status = "NEW";
        this.timestamp = System.currentTimeMillis();
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date date = new java.util.Date(this.timestamp);
        return sdf.format(date);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
