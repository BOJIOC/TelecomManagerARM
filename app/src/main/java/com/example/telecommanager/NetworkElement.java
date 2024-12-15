package com.example.telecommanager;

public class NetworkElement {
    private int id;  // ID элемента
    private String name;
    private String type;
    private String status;

    // Конструктор с тремя параметрами
    public NetworkElement(String name, String type, String status) {
        this.id = 0;  // ID будет автоинкрементом в базе данных
        this.name = name;
        this.type = type;
        this.status = status;
    }


    // Геттеры и сеттеры для всех полей
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
