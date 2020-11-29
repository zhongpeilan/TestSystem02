package com.Dao.UserDaoImpl;

import com.Dao.UserDao;
import com.Entity.User;
import com.Util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoimpl implements UserDao {
    //使用JDBCTemplate工具类，目的与数据库完成链接
    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User queryByUserName(String username) {
        //声明sql
        //调用sql，并返回数据
        String sql="SELECT user_id,password FROM user where username=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username);
    }

    @Override
    public void insert(User user) {
        //声明sql
        //调用sql，并返回数据
      String sql="INSERT into user(username,password,name,telephone,sex,age,role ) VALUES(?,?,?,?,?,?,?);";
      jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getTelephone(),user.getSex(),user.getAge(),user.getRole());
    }

    @Override
    public int queryCountByUserName(String username) {
        String sql="SELECT user_id,password FROM user where username=?";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

