package com.ahn.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {


    //��dao��ʹ��jdbcTemplate����
    //��Ҫ�ߵõ�ģ�����
    //�������ļ��н�������
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add() {
        //
        System.out.println("UserDao test===");
        //�ڴ�ʹ��jbcTemplate����
        String sql="insert into t_user values(NULL,?,?)";
        //��������sql��䴫�ݲ������ɱ䣬���������Ҫ�Ĳ���������
        //����ֵ��int�ͣ�����Ӱ�������
        int rows=jdbcTemplate.update(sql,"jin","255252");
        System.out.println(rows);
    }
}
