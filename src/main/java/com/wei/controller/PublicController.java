package com.wei.controller;

import com.wei.pojo.CustomUser;
import com.wei.pojo.Group;
import com.wei.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class PublicController {


//登录
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/Login";
    }

    //导航页
    @RequestMapping({"/","/index"})
    public String toIndex(){
        System.out.println("进入函数");
        return "/index";
    }


    //公共页面
    @Autowired
    InfoService infoService;

    Principal principal;

    @RequestMapping("/info")
    public String toInfo(Model model){
        CustomUser customUser=infoService.searchBaseInfo(principal.getName());
        Group group=infoService.searchGroupInfo(principal.getName());
        model.addAttribute("baseInfo",customUser);
        model.addAttribute("groupInfo",group);
        return "shared/info";
    }

}
