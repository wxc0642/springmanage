package com.wei.service;

import com.wei.dao.SignInDao;
import com.wei.pojo.SignInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignInService {

    @Autowired
    SignInDao signInDao;

    /**
     * 查找单人
     * @param username
     * @return
     */
    public List<SignInData> searchAllSignInData(String username){
        return signInDao.searchByName(username);
    }

    /**
     * 按时间段查找
     * @param username
     * @param date1
     * @param date2
     * @return
     */
    public List<SignInData> searchDataByTime(String username, Date date1, Date date2){
        return signInDao.searchByName(username,date1,date2);
    }

    /**
     * 按组、时间段查找
     */

    /**
     * 以下两个方法仅提供给管理员（涉及删除，数据变动，考虑并发）
     */

    /**
     * 按时间段删除记录
     */
    public void deleteDataByTime(Date date1,Date date2){

    }


    /**
     * 按id删除记录
     */
    public void deleteDataById(int id){
        signInDao.deleteSignInDataById(id);
    }

}
