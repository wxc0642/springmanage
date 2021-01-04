package com.wei.pojo;

import java.util.Date;

public class SignInData {
    private int id;
    private Date morin;
    private Date morout;
    private Date afterin;
    private Date afterout;

    public SignInData() {
    }

    public SignInData(int id, Date morin, Date morout, Date afterin, Date afterout) {
        this.id = id;
        this.morin = morin;
        this.morout = morout;
        this.afterin = afterin;
        this.afterout = afterout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMorin() {
        return morin;
    }

    public void setMorin(Date morin) {
        this.morin = morin;
    }

    public Date getMorout() {
        return morout;
    }

    public void setMorout(Date morout) {
        this.morout = morout;
    }

    public Date getAfterin() {
        return afterin;
    }

    public void setAfterin(Date afterin) {
        this.afterin = afterin;
    }

    public Date getAfterout() {
        return afterout;
    }

    public void setAfterout(Date afterout) {
        this.afterout = afterout;
    }

    @Override
    public String toString() {
        return "SignInData{" +
                "id=" + id +
                ", morin=" + morin +
                ", morout=" + morout +
                ", afterin=" + afterin +
                ", afterout=" + afterout +
                '}';
    }
}
