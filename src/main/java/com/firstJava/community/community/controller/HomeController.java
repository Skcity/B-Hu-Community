package com.firstJava.community.community.controller;

import com.firstJava.community.community.entity.DiscussPost;
import com.firstJava.community.community.entity.Page;
import com.firstJava.community.community.entity.User;
import com.firstJava.community.community.service.DiscussPostService;
import com.firstJava.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.model.IAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/index",method= RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用之前，SpringMVC会自动实例化Model和Page，并将Page注入Model，
        //所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list=discussPostService.findDiscussPosts(0, page.getOffsect(), page.getPageMax());
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(list!=null){
            for(DiscussPost post:list){
                Map<String,Object> map=new HashMap<>();
                map.put("post",post);
                User user =userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        //返回逻辑视图，要在/resources/template下建立对应的index.html文件
        return "/index";
    }
}
