package com.wei.dao;

import com.wei.exception.ParaLengthException;
import com.wei.pojo.SignInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public class SignInDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名（当前用户）查找数据库中所有打卡情况
     * @param username
     * @return
     */
    public List<Map<String,Object>> searchByName(String username,Date... dates){
        if(dates.length==0){
            String sql= String
                    .format("select * from book_in_set where id=(select id from user_info where username='%s')",username);

            return jdbcTemplate.queryForList(sql);
            }else if(dates.length==2){
            String sql=String
                    .format("select * from book_in_set where id=" +
                            "(select id from user_info where username='%s') " +
                            "and(morin between '%s' and '%s')",username,dates[0],dates[1]);
            return jdbcTemplate.queryForList(sql);
        }else {
            return null;
        }
    }


    /**
     * 根据id查找数据库中所有打卡情况
     * @param id
     * @return
     */
    public List<Map<String,Object>> searchById(int id){
        String sql=String.format("select * from book_in_set where id='%s'",id);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据组查找数据库中所有打卡情况
     * @param group_id
     * @return
     */
    public List<Map<String,Object>> searchByGroupId(int group_id){
        String sql=String.format("select * from book_in_set where id=" +
                "(select id from user_info where group_id='%s')",group_id);
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查找所有数据
     * @return
     */
    public List<Map<String,Object>> searchAll(){
        String sql="select * from book_in_set";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查看具体时间段内的打卡数据
     * 或---->按时间及组查看具体打卡数据【思考：一天某时段缺少数据怎么办？？？】
     */
    public List<Map<String,Object>> searchByTimeAndGroup(Date date1,Date date2,int... group_id){
        if(group_id.length==0){
            String sql=String.format("select * from book_in_set where morin between '%s' and '%s'",date1,date2);
            return jdbcTemplate.queryForList(sql);
        }else{
            String sql=String.format("select * from book_in_set where id=" +
                    "(select id from user_info where group_id='%s') " +
                    "and (morin between '%s' and '%s')",group_id[0],date1,date2);
            return jdbcTemplate.queryForList(sql);
        }
    }

    /**
     * 根据id和时间段查看打卡数据
     * @param id
     * @param date1
     * @param date2
     * @return
     */
    public List<SignInData> searchByTimeAndId(int id,Date date1,Date date2){
        String sql=String.format("select * from book_in_set where",id,date1,date2);
        return jdbcTemplate.queryForList(sql,SignInData.class);
    }


    /**
     * 只有管理员可以删除数据。
     * 提供两个方法，一个是根据id删除，一个是根据日期删除。
     */

    /**
     * 根据id删除
     */
    public void deleteSignInDataById(int id){
        String sql=String.format("delete from book_in_set where id='%s'",id);
        jdbcTemplate.update(sql);
    }

    public void deleteSignInDataByTime(Date date1,Date date2,int... id) throws ParaLengthException {
        if(id.length==0){
            String sql=String.format("delete from book_in_set where morin between '%s' and '%s'",date1,date2);
            jdbcTemplate.update(sql);
        }else if(id.length==1){
            String sql=String.format("delete from book_in_set where id='%s'" +
                    "and (morin between '%s' and '%s')",id[0],date1,date2);
            jdbcTemplate.update(sql);
        }else{
            throw new ParaLengthException("参数长度不正确");
        }
    }


    /**
     * 从外部读入打卡记录
     */
    public void setSignInDataAndTag(int id,Date date,String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String timeTag=sdf.format(date);
        //查看是否有时间标签，如果有，说明这一天已经有一条记录在内
        String sql1=String.format("select timeTag from book_in_set where id='%s' and timeTag='%s'",id,timeTag);
        //查看是否所要存储的位置是否已有数据，若有，说明这一条数据重复
        String sql2=String.format("select '%s' from book_in_set where id='%s' and timeTag='%s'",time,id,timeTag);
        //有时间标签时，说明id,某个时间段的数据已创建，只需插入打卡时间数据即可
        String sql3=String.format("insert into book_in_set(%s) values('%s') where id='%s'",time,date,id);
        //没有时间标签时，需插入id,打卡时间,时间标签
        String sql4=String.format("insert into book_in_set(id,%s,timeTag) values('%s','%s','%s')",time,id,date,timeTag);
        String beTimeTag=jdbcTemplate.queryForObject(sql1,String.class);
        Date beDate=jdbcTemplate.queryForObject(sql2,Date.class);
        if(beTimeTag!=null){
            if(beDate!=null){
                System.out.println("重复数据");
            }else {
                jdbcTemplate.update(sql3);
            }
        }else{
            jdbcTemplate.update(sql4);
        }

    }




    /**
     * 没打卡日期、以及打卡的日期处理（双休）
     */

}
