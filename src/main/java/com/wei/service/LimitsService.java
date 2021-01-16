package com.wei.service;

import com.wei.pojo.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LimitsService {
    /**
     * 获取所有权限
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Limit> getAllLimits(){
        String sql="select * from limit_set";
        List<Map<String,Object>> mapList=jdbcTemplate.queryForList(sql);
        List<Limit> limitList=new ArrayList<Limit>();
        for(Map<String,Object>map:mapList){
            Limit l=new Limit();
            l.setType((String)map.get("type"));
            l.setRole((String)map.get("role"));
            limitList.add(l);
        }
        return limitList;
    }
}
