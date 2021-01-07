package com.wei.dao;

import com.wei.except.ParaLengthException;
import com.wei.pojo.SignInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository
public class SignInDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名（当前用户）查找数据库中所有打卡情况
     * @param username
     * @return
     */
    public List<SignInData> searchByName(String username,Date... dates){
        if(dates.length==0){
            String sql= String
                    .format("select * from book_in_set where id=(select id from user_info where username='%s')",username);
            return jdbcTemplate.queryForList(sql,SignInData.class);
        }else if(dates.length==2){
            String sql=String
                    .format("select * from book_in_set where id=" +
                            "(select id from user_info where username='%s') " +
                            "and(morin between '%s' and '%s')",username,dates[0],dates[1]);
            return jdbcTemplate.queryForList(sql,SignInData.class);
        }else {
            return null;
        }
    }


    /**
     * 根据id查找数据库中所有打卡情况
     * @param id
     * @return
     */
    public List<SignInData> searchById(int id){
        String sql=String.format("select * from book_in_set where id='%s'",id);
        return jdbcTemplate.queryForList(sql,SignInData.class);
    }

    /**
     * 根据组查找数据库中所有打卡情况
     * @param group_id
     * @return
     */
    public List<SignInData> searchByGroupId(int group_id){
        String sql=String.format("select * from book_in_set where id=" +
                "(select id from user_info where group_id='%s')",group_id);
        return jdbcTemplate.queryForList(sql,SignInData.class);
    }

    /**
     * 查找所有数据（考虑再创建需展示的数据的实体类）
     * @return
     */
    public List<SignInData> searchAll(){
        String sql="select * from book_in_set";
        return jdbcTemplate.queryForList(sql,SignInData.class);
    }

    /**
     * 查看具体时间段内的打卡数据
     * 或---->按时间及组查看具体打卡数据【思考：一天某时段缺少数据怎么办？？？】
     */
    public List<SignInData> searchByTimeAndGroup(Date date1,Date date2,int... group_id){
        if(group_id.length==0){
            String sql=String.format("select * from book_in_set where morin between '%s' and '%s'",date1,date2);
            return jdbcTemplate.queryForList(sql,SignInData.class);
        }else{
            String sql=String.format("select * from book_in_set where id=" +
                    "(select id from user_info where group_id='%s') " +
                    "and (morin between '%s' and '%s')",group_id[0],date1,date2);
            return jdbcTemplate.queryForList(sql,SignInData.class);
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
            throw new ParaLengthException("参数长度不正确正确");
        }


    }
}
