package com.firstJava.community.community;


import com.firstJava.community.community.dao.demoDao;
import com.firstJava.community.community.dao.demoskk2;
import com.firstJava.community.community.dao.domoDao;
import com.firstJava.community.community.service.service1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//测试代码以CommunityApplication为配置类
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
		domoDao domodao=applicationContext.getBean(domoDao.class);//通过类型获取bean
		System.out.println(domodao.select());
		domodao=applicationContext.getBean("skk2",domoDao.class);//通过名称获取bean，需要指定类型
		System.out.println(domodao.select());

	}

	@Test
	public void testBeanMangement(){
		service1 service2 =applicationContext.getBean(service1.class);
		System.out.println(service2);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat=applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}
	//依赖注入

	@Autowired
	@Qualifier("skk2")//依赖的是接口，可以通过bean的ID指定bean的实现
	private domoDao domodao;//希望spring容器将domoDao注入属性domodao,依赖注入

	@Autowired
	private demoDao demodao;//依赖的是具体的实现

	@Test
	public void testDI(){
		System.out.println(domodao.select());
		System.out.println(demodao.select());
	}
}
