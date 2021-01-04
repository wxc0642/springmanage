package com.wei.dao;

import com.alibaba.fastjson.JSON;
import com.wei.pojo.CustomUser;

import com.wei.pojo.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //增删改查
    //查找单个用户
    public CustomUser searchUser(String username){
        String sql="select * from user_info where username='?'";
//        RowMapper<CustomUser> rowMapper=new BeanPropertyRowMapper<CustomUser>(CustomUser.class);
//        return this.jdbcTemplate.queryForObject(sql,rowMapper,username);

        return JSON.parseObject(JSON.toJSONString(jdbcTemplate.queryForMap(sql)),CustomUser.class);
    }

    //查找单个用户权限
    public Limit searchLimit(String username){
        String sql="select * from limit_set where type=(select type from user_info where username='?')";
//        RowMapper<Limit> rowMapper=new BeanPropertyRowMapper<Limit>(Limit.class);
//        return this.jdbcTemplate.queryForObject(sql,rowMapper,username);
        return JSON.parseObject(JSON.toJSONString(jdbcTemplate.queryForMap(sql)),Limit.class);

    }
}
