package com.ahn.aop;

import com.ahn.c3p0.UserDao;
import com.ahn.c3p0.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void testA(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean.xml");
        Book book= (Book) applicationContext.getBean("book");
        //先调用Mybook类的方法，再执行book类的方法
        book.test();
    }

    @Test
    public void testjdbc(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean.xml");
        UserService userService=(UserService)applicationContext.getBean("userService");
        userService.add();
    }
}
