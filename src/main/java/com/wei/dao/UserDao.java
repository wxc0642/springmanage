package com.wei.dao;

import com.alibaba.fastjson.JSON;
import com.wei.pojo.CustomUser;

import com.wei.pojo.Group;
import com.wei.pojo.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 以下两个方法主要是为了给框架中的User实现UsersService接口类提供参数
     */


    /**
     * 根据名字查找单个用户
     * @param username
     * @return
     */
    public CustomUser searchUser(String username){
        String sql=String.format("select * from user_info where username='%s'",username);
//        RowMapper<CustomUser> rowMapper=new BeanPropertyRowMapper<CustomUser>(CustomUser.class);
//        return this.jdbcTemplate.queryForObject(sql,rowMapper,username);

        return JSON.parseObject(JSON.toJSONString(jdbcTemplate.queryForMap(sql)),CustomUser.class);
    }

    /**
     * 根据名字查找对应用户权限
     * @param username
     * @return
     */
    public Limit searchLimit(String username){
        String sql="select * from limit_set where type=(select type from user_info where username='"+username+"')";
        return JSON.parseObject(JSON.toJSONString(jdbcTemplate.queryForMap(sql)),Limit.class);
    }


    /**
     * 以下部分内容为页面数据查找
     */


    //查找同一组的人员

    //按id查找

    //按名字查找所在组
    public Group searchGroup(String username){
        String sql="select * from group_set where group_id=(select group_id from user_info where username='"+username+"')";
        return JSON.parseObject(JSON.toJSONString(jdbcTemplate.queryForMap(sql)),Group.class);
    }



}
