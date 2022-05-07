package com.example.carservice;

public enum UserDetails {
    INSTANCE;

    private int userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;

    public int getMechanicStatus() {
        return mechanicStatus;
    }

    public void setMechanicStatus(int mechanicStatus) {
        this.mechanicStatus = mechanicStatus;
    }

    int mechanicStatus;

}
