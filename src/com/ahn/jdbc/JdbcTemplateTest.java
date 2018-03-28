package com.ahn.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//ʹ��jdbcģ������ݿ����crud����
public class JdbcTemplateTest {

    @Test
    public void add(){
        //1���������ݿ���Ϣ
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //��������
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ�·��
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2������ģ�����
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //����jdbc����ķ����������ݿ����
        String sql="insert into t_user values(NULL,?,?)";
        //��������sql��䴫�ݲ������ɱ䣬���������Ҫ�Ĳ���������
        //����ֵ��int�ͣ�����Ӱ�������
        int rows=jdbcTemplate.update(sql,"ahn","123456");
        System.out.println(rows);
    }

    //�޸Ĳ���
    @Test
    public void update(){
        //1���������ݿ���Ϣ
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //��������
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ�·��
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2������ģ�����
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //����jdbc����ķ����������ݿ����
        String sql="UPDATE t_user set password=? WHERE username=?";
        //��������sql��䴫�ݲ������ɱ䣬���������Ҫ�Ĳ���������
        //����ֵ��int�ͣ�����Ӱ�������
        int rows=jdbcTemplate.update(sql,"78910","ahn");
        System.out.println(rows);
    }

    //ɾ������
    @Test
    public void delete(){
        //1���������ݿ���Ϣ
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //��������
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ�·��
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2������ģ�����
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //����jdbc����ķ����������ݿ����
        String sql="DELETE FROM t_user WHERE username=?";
        //��������sql��䴫�ݲ������ɱ䣬���������Ҫ�Ĳ���������
        //����ֵ��int�ͣ�����Ӱ�������
        int rows=jdbcTemplate.update(sql,"ahn");
        System.out.println(rows);
    }

    //��ѯ����
    @Test
    public void queryForCount(){
        //1���������ݿ���Ϣ
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //��������
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ�·��
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2������ģ�����
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //����jdbc����ķ����������ݿ����
        String sql="select count(*) from t_user";
        //queryForObject���� ��һ������Ϊsql��䣬�ڶ�������Ϊ����ֵ����
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(count);
    }

    //��ѯ����
    //��ѯ���ض�������
    @Test
    public void queryForObject(){
        //1���������ݿ���Ϣ
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //��������
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ�·��
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2������ģ�����
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //����jdbc����ķ����������ݿ����
        String sql="select * from t_user where username=?";
        //���ض���������Ҫ�Լ�����һ����ʵ�ֽӿ�RowMapper
        //��һ������ΪSQL��䣬�ڶ�������ΪRowMapper���󣬵����������ǿɱ����������SQL�������Ҫ��ֵ�Ĳ���
        User user=jdbcTemplate.queryForObject(sql,new MyRowMappper(),"ahn");
        System.out.println(user);
    }

    //��ѯ����list����
    @Test
    public void queryForList(){
        //1���������ݿ���Ϣ
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //��������
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //�������ݿ�·��
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2������ģ�����
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //����jdbc����ķ����������ݿ����
        String sql="select * from t_user";
        //���ض���������Ҫ�Լ�����һ����ʵ�ֽӿ�RowMapper
        //��һ������ΪSQL��䣬�ڶ�������ΪRowMapper���󣬵����������ǿɱ����������SQL�������Ҫ��ֵ�Ĳ���
        List<User> list=jdbcTemplate.query(sql,new MyRowMappper());
        System.out.println(list);
    }

    public void testC3p0() throws PropertyVetoException {
        //�������jar��
        //��������
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql:///springdb");
        dataSource.setUser("root");
        dataSource.setPassword("root");
    }
}
//������һ����ʵ��rowmapper�ӿڽ����ݷ�װ������
class MyRowMappper implements RowMapper<User>{

    //��һ������Ϊ��������ڶ�������Ϊ��������ݵ�λ��
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        //�ڴ���Ҫ��������õ������ݷ�װ��������
        //1���ӽ�����������
        String username=resultSet.getString("username");
        String password=resultSet.getString("password");
        User uer=new User(username,password);
        return uer;
    }
}
