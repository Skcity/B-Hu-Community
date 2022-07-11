package com.firstJava.community.community;

import com.firstJava.community.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//测试代码以CommunityApplication为配置类
public class MailTest {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testHtmlMail(){
        Context contex=new Context();
        contex.setVariable("username","sunday");

        String content = templateEngine.process("/mail/mail",contex);
        System.out.println(content);
        mailClient.sendMail("sk3826962@icloud.com","html_test",content);

    }

    @Test
    public void testTextMail(){
        mailClient.sendMail("sk3826962@icloud.com","TEST","Welcome");
    }
}
