package com.ahn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//����ǿ��MyBookʹ��ע��
@Aspect
public class MyBook {


    //ʹ��aop��ΪBook���test����ִ��֮ǰ���ӹ���
    @Before(value="execution(* com.ahn.aop.Book.*(..))")
    public void testBefore(){
        System.out.println("MyBook-ǰ����ǿtest=====");
    }

    //������ǿ
    @AfterReturning(value = "execution(* com.ahn.aop.Book.*(..))")
    public void testAfter(){
        System.out.println("MyBook-������ǿtest=====");
    }

    //����֪ͨ���ڷ���ִ��֮ǰִ�У��ڷ���ִ��֮��ִ�У�
    @Around(value = "execution(* com.ahn.aop.Book.*(..))")
    public void testAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("MyBook-����ִ��֮ǰtest=====");
        //ִ����ǿ�ķ���
        //ͨ�����������ִ����ǿ�ķ���
        proceedingJoinPoint.proceed();

        System.out.println("MyBook-����ִ��֮��test=====");
    }
}
