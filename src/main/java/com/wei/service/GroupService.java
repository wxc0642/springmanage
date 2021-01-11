package com.wei.service;

import com.wei.dao.GroupDao;
import com.wei.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {
    @Autowired
    GroupDao groupDao;


    public List<Group> mapToList(List<Map<String,Object>> mapList){
        List<Group> lists=new ArrayList<Group>();
        for(Map<String,Object> map:mapList){
            Group group=new Group();
            group.setGroup_id((int)map.get("group_id"));
            group.setGroupName((String)map.get("groupName"));
            lists.add(group);
        }
        return lists;
    }


    public String fetchGroupName(int group_id){
        return groupDao.searchForGroupName(group_id);
    }

    //获取所有组
    public List<Group> getAllGroup(){
        return mapToList(groupDao.searchForGroups());
    }
}
