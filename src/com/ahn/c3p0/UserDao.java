package com.ahn.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {


    //在dao中使用jdbcTemplate对象
    //需要线得到模板对象
    //在配置文件中进行配置
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add() {
        //
        System.out.println("UserDao test===");
        //在此使用jbcTemplate对象
        String sql="insert into t_user values(NULL,?,?)";
        //后面是向sql语句传递参数（可变，具体参照需要的参数个数）
        //返回值是int型，即受影响的行数
        int rows=jdbcTemplate.update(sql,"jin","255252");
        System.out.println(rows);
    }
}
