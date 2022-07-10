package com.firstJava.community.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
@Primary
@Repository("xxx")
public class demoDao implements domoDao{

    @Override
    public String select() {
        return "xxxooo";
    }
}
