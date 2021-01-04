package com.wei.pojo;

public class Group {
    private int group_id;
    private String groupName;

    public Group() {
    }

    public Group(int group_id, String groupName) {
        this.group_id = group_id;
        this.groupName = groupName;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group_id=" + group_id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
