package com.ahn.c3p0;

public class UserService {

    //ʹ������ע�뷽ʽ
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        userDao.add();
        System.out.println("UserService test===");
    }
}
