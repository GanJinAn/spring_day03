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

//使用jdbc模板对数据库进行crud操作
public class JdbcTemplateTest {

    @Test
    public void add(){
        //1、设置数据库信息
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //加载驱动
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库路径
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2、创建模板对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //调用jdbc里面的方法进行数据库操作
        String sql="insert into t_user values(NULL,?,?)";
        //后面是向sql语句传递参数（可变，具体参照需要的参数个数）
        //返回值是int型，即受影响的行数
        int rows=jdbcTemplate.update(sql,"ahn","123456");
        System.out.println(rows);
    }

    //修改操作
    @Test
    public void update(){
        //1、设置数据库信息
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //加载驱动
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库路径
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2、创建模板对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //调用jdbc里面的方法进行数据库操作
        String sql="UPDATE t_user set password=? WHERE username=?";
        //后面是向sql语句传递参数（可变，具体参照需要的参数个数）
        //返回值是int型，即受影响的行数
        int rows=jdbcTemplate.update(sql,"78910","ahn");
        System.out.println(rows);
    }

    //删除操作
    @Test
    public void delete(){
        //1、设置数据库信息
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //加载驱动
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库路径
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2、创建模板对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //调用jdbc里面的方法进行数据库操作
        String sql="DELETE FROM t_user WHERE username=?";
        //后面是向sql语句传递参数（可变，具体参照需要的参数个数）
        //返回值是int型，即受影响的行数
        int rows=jdbcTemplate.update(sql,"ahn");
        System.out.println(rows);
    }

    //查询操作
    @Test
    public void queryForCount(){
        //1、设置数据库信息
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //加载驱动
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库路径
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2、创建模板对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //调用jdbc里面的方法进行数据库操作
        String sql="select count(*) from t_user";
        //queryForObject方法 第一个参数为sql语句，第二个参数为返回值的类
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(count);
    }

    //查询操作
    //查询返回对象类型
    @Test
    public void queryForObject(){
        //1、设置数据库信息
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //加载驱动
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库路径
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2、创建模板对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //调用jdbc里面的方法进行数据库操作
        String sql="select * from t_user where username=?";
        //返回对象类型需要自己创建一个类实现接口RowMapper
        //第一个参数为SQL语句，第二个参数为RowMapper对象，第三个参数是可变参数，即在SQL语句中需要赋值的参数
        User user=jdbcTemplate.queryForObject(sql,new MyRowMappper(),"ahn");
        System.out.println(user);
    }

    //查询返回list集合
    @Test
    public void queryForList(){
        //1、设置数据库信息
        DriverManagerDataSource dataMS=new DriverManagerDataSource();
        //加载驱动
        dataMS.setDriverClassName("com.mysql.jdbc.Driver");
        //设置数据库路径
        dataMS.setUrl("jdbc:mysql:///springdb");
        dataMS.setUsername("root");
        dataMS.setPassword("root");

        //2、创建模板对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataMS);
        //调用jdbc里面的方法进行数据库操作
        String sql="select * from t_user";
        //返回对象类型需要自己创建一个类实现接口RowMapper
        //第一个参数为SQL语句，第二个参数为RowMapper对象，第三个参数是可变参数，即在SQL语句中需要赋值的参数
        List<User> list=jdbcTemplate.query(sql,new MyRowMappper());
        System.out.println(list);
    }

    public void testC3p0() throws PropertyVetoException {
        //导入相关jar包
        //创建对象
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql:///springdb");
        dataSource.setUser("root");
        dataSource.setPassword("root");
    }
}
//创建另一个类实现rowmapper接口将数据封装到对象
class MyRowMappper implements RowMapper<User>{

    //第一个参数为结果集，第二个参数为结果集数据的位置
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        //在此需要将结果集得到的数据封装到对象中
        //1、从结果集获得数据
        String username=resultSet.getString("username");
        String password=resultSet.getString("password");
        User uer=new User(username,password);
        return uer;
    }
}
