package com.firstJava.community.community.service;


import com.firstJava.community.community.dao.UserMapper;
import com.firstJava.community.community.dao.domoDao;
import com.firstJava.community.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service

public class service1 {

    @Autowired
    @Qualifier("skk2")
    private domoDao domodao;

    public service1(){
        System.out.println("service1 constructor");
    }

    @PostConstruct//在构造器之后调用
    public void init(){
        System.out.println("initialize service1");
    }

    @PreDestroy//在销毁之前调用，用于释放某些资源
    public void destroy(){
        System.out.println("destroy service1");
    }

    public String find(){
        return domodao.select();
    }

    //test
    @Autowired
    private UserMapper userMapper;
    public String queryTest(int id,String username){
        return userMapper.selectByIdandName(id,username);
    }


}
