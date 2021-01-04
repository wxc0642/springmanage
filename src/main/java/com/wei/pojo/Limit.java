package com.wei.pojo;

public class Limit {
    private String type;
    private String role;

    public Limit() {
    }

    public Limit(String type, String role) {
        this.type = type;
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Limit{" +
                "type='" + type + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
