package com.wei.service;

import com.wei.dao.UserDao;
import com.wei.pojo.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    UserDao userDao;

    /**
     * 查找所有用户
     * @return
     */
    public List<CustomUser> getAllUser(){
        return userDao.searchAllUsers();
    }
    /**
     * 添加用户
     */
    public void saveUser(CustomUser customUser){
        userDao.addUser(customUser);
    }

}
