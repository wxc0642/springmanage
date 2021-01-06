package com.wei.pojo;


import org.springframework.stereotype.Component;

@Component
public class CustomUser {
    private int id;
    private int group_id;
    private String username;
    private String password;

    private String type;

    public CustomUser() {
    }

    public CustomUser(int id, int group_id, String username, String password, String type) {
        this.id = id;
        this.group_id = group_id;
        this.username = username;
        this.password = password;

        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
