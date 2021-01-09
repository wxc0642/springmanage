package com.wei.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;

@Component
public class SignInData {
    private int id;
    private Date morin;
    private Date morout;
    private Date afterin;
    private Date afterout;
    private String timeTag;

    public SignInData() {
    }

    public SignInData(int id, Date morin, Date morout, Date afterin, Date afterout,String timeTag) {
        this.id = id;
        this.morin = morin;
        this.morout = morout;
        this.afterin = afterin;
        this.afterout = afterout;
        this.timeTag=timeTag;
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


    public String getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
    }

    @Override
    public String toString() {
        return "SignInData{" +
                "id=" + id +
                ", morin=" + morin +
                ", morout=" + morout +
                ", afterin=" + afterin +
                ", afterout=" + afterout +
                ", timeTag='" + timeTag + '\'' +
                '}';
    }
}

/**
 * 采用TreeSet数据结构存储打卡数据
 * 1.根据id比较
 * 2.根据时间先后比较
 */


//class SignInDataComparator implements Comparator<SignInData>{
//    @Override
//    public int compare(SignInData o1, SignInData o2) {
//        if (o1.getId() != o2.getId()) {
//            return o1.getId() - o2.getId();
//        } else {
//            Date d1=null;
//            Date d2=null;
//            for
//            long compareRlt = o1.getMorin().getTime() - o2.getMorin().getTime();
//            return (int) compareRlt;
//        }
//    }
//}
