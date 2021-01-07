package com.wei.dao;

import com.wei.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GroupDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //增加一个组
    public void addGroup(String groupName){
        String sql=String.format("insert into group_set(groupName) values('%s')",groupName);
        jdbcTemplate.update(sql);
    }

    //删除一个组
    public void deleteGroup(int group_id){
        String sql=String.format("delete from group_set where group_id='%s'",group_id);
        jdbcTemplate.update(sql);
    }

    //查找全部组
    public List<Group> searchForGroups(){
        String sql="select * from group_set order by group_id";
        return jdbcTemplate.queryForList(sql,Group.class);
    }

    //查找group_id对应的组名
    public String searchForGroupName(int group_id){
        String sql="select groupName from group_set where group_id="+group_id;
        Group group=jdbcTemplate.queryForObject(sql,Group.class);
        return group.getGroupName();
    }
}
