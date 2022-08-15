package com.firstJava.community.community.controller;

import com.firstJava.community.community.service.service1;
import com.firstJava.community.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class demo {
    @RequestMapping("/Hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring boot";
    }

    @Autowired
    private service1 service2;

    @RequestMapping("/Data")
    @ResponseBody
    public String getData(){
        return service2.find();
    }

    //Get请求
    // /students?current=1&limit=20
    @RequestMapping(path="/student",method = RequestMethod.GET)
    @ResponseBody
    public int getStudent(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit
    ){
        System.out.println(current);
        System.out.println(limit);
        return current*2+limit*8;
    }

    // /student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public int getStudet(@PathVariable("id") int id){
        return id;
    }

    //POST请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(int id,String username){//参数名字需和表单一致
        return service2.queryTest(id,username);
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav =new ModelAndView();
        mav.addObject("name","Join");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");
        return mav;
    }

    //第二种方式
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","PKU");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    // 响应JSON数据（异步请求）（网页不刷新、访问服务器得到结果）
    //由于声明了@ResponseBody,且返回的数据类型为Map<String,Object>,则将自动转为JSON数组给浏览器
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody//不加则认为返回的是html
    public Map<String,Object> getEmp(){
        Map<String,Object>emp=new HashMap<>();
        emp=new HashMap<>();
        emp.put("name","Join");
        emp.put("age",23);
        emp.put("salary",8000);
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody//不加则认为返回的是html
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object>emp=new HashMap<>();
        emp=new HashMap<>();
        emp.put("name","Join");
        emp.put("age",23);
        emp.put("salary",8000);
        list.add(emp);

        emp=new HashMap<>();
        emp.put("name","Amy");
        emp.put("age",24);
        emp.put("salary",7000);
        list.add(emp);

        emp=new HashMap<>();
        emp.put("name","WuYifan");
        emp.put("age",25);
        emp.put("salary",10000);
        list.add(emp);

        return list;
    }

    //cookie
    //服务器设置cookie发送给浏览器存储
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/alpha");
        // 设置cookie的生存时间，设置后则存入硬盘
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }
    //浏览器访问服务器时自动带回cookie，服务端获取cookie内容
    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {//选取key为code的cookie
        System.out.println(code);
        return "get cookie";
    }

    // session示例

    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }


}
