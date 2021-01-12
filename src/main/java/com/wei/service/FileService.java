package com.wei.service;

import com.wei.dao.SignInDao;
import com.wei.exception.TimeOutException;
import com.wei.pojo.SignInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {


    @Autowired

    SignInDao signInDao;

    /**
     * 改变格式以及存入数据库
     * @param file
     */
    public void changeFileFormat(MultipartFile file){
        InputStream is=null;
        try {
            is = file.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
        Scanner scan_is=new Scanner(is);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(scan_is.hasNextLine()) {
            String signInDataLine = scan_is.nextLine();
            String[] signInDataCols=signInDataLine.split("=");
            int id=0;
            try{
                id=Integer.parseInt(signInDataCols[0]);

            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            String time=signInDataCols[1];

            Timestamp timestamp=null;
            try {
                long dateTime = sdf.parse(time).getTime();
                timestamp=new Timestamp(dateTime);
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                signInDao.setSignInDataAndTag(id, timestamp, checkSignInTime(time));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断是否处于规定的时间段
     * @param time
     * @return
     */
    public String checkSignInTime(String time) throws Exception{
        final String MSI1="07:00:00";
        final String MSI2="09:10:00";
        final String MSO1="11:30:00";
        final String MSO2="12:30:00";
        final String MNI1="14:00:00";
        final String MNI2="14:30:00";
        final String MNO1="17:30:00";
        final String MNO2="18:10:00";
        char[] toArray=time.toCharArray();
        String time_con="";
        for(int i=0;i<11;i++){
            time_con+=toArray[i];
        }


        if(((time_con+MSI1).compareTo(time))<0&&((time_con+MSI2).compareTo(time))>0){
            return "morin";
        }else if(((time_con+MSO1).compareTo(time))<0&&((time_con+MSO2).compareTo(time))>0){
            return "morout";
        }else if(((time_con+MNI1).compareTo(time))<0&&((time_con+MNI2).compareTo(time))>0){
            return "afterin";
        }else if(((time_con+MNO1).compareTo(time))<0&&((time_con+MNO2).compareTo(time))>0){
            return "afterout";
        }else{
            throw new TimeOutException("不在打卡时间段内");
        }
    }





}
