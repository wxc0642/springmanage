package com.wei.service;

import com.wei.dao.UserDao;
import com.wei.pojo.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {

    @Autowired
    UserDao userDao;

    /**
     * map转换为实体List
     * @param maps
     * @return
     */
    public List<CustomUser> mapToList(List<Map<String,Object>> maps){
        List<CustomUser> lists=new ArrayList<CustomUser>();
        for(Map<String,Object>map:maps){
            CustomUser c=new CustomUser();
            c.setId((int)map.get("id"));
            c.setGroup_id((int)map.get("group_id"));
            c.setUsername((String)map.get("username"));
            c.setPassword((String)map.get("password"));
            c.setType((String)map.get("type"));
            lists.add(c);
        }
        return lists;
    }



    /**
     * 查找所有用户
     * @return
     */
    public List<CustomUser> getAllUser(){
        return mapToList(userDao.searchAllUsers());
    }

    /**
     * 根据group_id找用户
     */
    public List<CustomUser> getUserByGroupId(int group_id){
        return mapToList(userDao.searchUsersByGroup(group_id));
    }

    /**
     * 添加用户
     */
    public void saveUser(CustomUser customUser){
        userDao.addUser(customUser);
    }

    /**
     * 根据删除用户
     */
    public void deleteUser(int id){
        userDao.deleteUser(id);
    }
}
