package com.wei.service;

import com.wei.dao.SignInDao;
import com.wei.pojo.SignInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SignInService {

    @Autowired
    SignInDao signInDao;

    /**
     * Map转List
     */
    public List<SignInData> mapToList(List<Map<String,Object>> maps){
        List<SignInData> lists=new ArrayList<SignInData>();
        for(Map<String,Object>map :maps){
            SignInData signInData=new SignInData();
            signInData.setId((int)map.get("id"));
            signInData.setMorin((Date)map.get("morin"));
            signInData.setMorout((Date)map.get("morout"));
            signInData.setAfterin((Date)map.get("afterin"));
            signInData.setAfterout((Date)map.get("afterout"));
            signInData.setTimeTag((String)map.get("timeTag"));
            lists.add(signInData);
        }
        return lists;
    }

    /**
     * 查找单人记录
     * @param username
     * @return
     */
    public  List<SignInData> searchAllSignInData(String username,Date... dates) {



        if(dates.length==0){
            return mapToList(signInDao.searchByName(username));
        }else if(dates.length==2){
            Timestamp date1stamp=new Timestamp(dates[0].getTime());
            Timestamp date2stamp=new Timestamp(dates[1].getTime());
            return mapToList(signInDao.searchByName(username,date1stamp,date2stamp));
        }else {
            return null;
        }
    }


    /**
     * 按组、时间段查找
     */
    public List<SignInData> searchDataByTAndGroup(Date date1,Date date2,int... group_id){
        Timestamp date1stamp=new Timestamp(date1.getTime());
        Timestamp date2stamp=new Timestamp(date2.getTime());
        if(group_id.length==0){
            return mapToList(signInDao.searchByTimeAndGroup(date1stamp,date2stamp));
        }else if(group_id.length==1){
            return mapToList(signInDao.searchByTimeAndGroup(date1stamp,date2stamp,group_id[0]));
        }else {
            return null;
        }
    }

    /**
     * 按组、时间段查找（时间段可添加）
     * @param group_id
     * @param dates
     * @return
     */
    public List<SignInData> searchDataByGAndTime(int group_id,Date... dates){
        if(dates.length==0){
            return mapToList(signInDao.searchByGroupId(group_id));
        }else if(dates.length==2){
            Timestamp date1stamp=new Timestamp(dates[0].getTime());
            Timestamp date2stamp=new Timestamp(dates[1].getTime());
            return mapToList(signInDao.searchByTimeAndGroup(date1stamp,date2stamp,group_id));
        }else{
            return null;
        }
    }

    /**
     * 查找所有数据
     */
    public List<SignInData> searchAll(){
        return mapToList(signInDao.searchAll());
    }


    /**
     * 以下两个方法仅提供给管理员（涉及删除，数据变动，考虑并发）
     */

    /**
     * 按时间段删除记录
     */
    public void deleteDataByTime(Date date1,Date date2,int... id){
        try {
            if (id.length == 0) {
                signInDao.deleteSignInDataByTime(date1, date2);
            }else if(id.length==1){
                signInDao.deleteSignInDataByTime(date1, date2, id[0]);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 按id删除记录
     */
    public void deleteDataById(int id){
        signInDao.deleteSignInDataById(id);
    }

}
