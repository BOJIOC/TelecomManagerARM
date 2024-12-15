package com.example.telecommanager;

public class FaultReport {
    private int id; // Добавлено
    private String description;
    private String status;
    private long timestamp;

    public FaultReport(String description, String status, long timestamp) {
        this.description = description;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Новый конструктор для загрузки данных из базы
    public FaultReport(int id, String description, String status, long timestamp) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры для id
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

    // Метод для получения даты в строковом формате
    public String getDate() {
        // Преобразуем timestamp в строку (например, в формате "dd/MM/yyyy")
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date date = new java.util.Date(this.timestamp);
        return sdf.format(date);
    }

    // Метод для получения timestamp
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
