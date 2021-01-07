package com.wei.service;

import com.wei.dao.UserDao;
import com.wei.pojo.CustomUser;
import com.wei.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    UserDao userDao;

    public CustomUser searchBaseInfo(String username){
        return userDao.searchUser(username);
    }

    public Group searchGroupInfo(String username){
        return userDao.searchGroup(username);
    }

}
