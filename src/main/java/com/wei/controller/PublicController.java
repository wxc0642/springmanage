package com.wei.controller;

import com.wei.pojo.CustomUser;
import com.wei.pojo.Group;
import com.wei.pojo.SignInData;
import com.wei.service.FileService;
import com.wei.service.GetUserPrincipal;
import com.wei.service.InfoService;
import com.wei.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class PublicController {


//登录
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
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


    @Autowired
    GetUserPrincipal getUserPrincipal;


    @RequestMapping("/shared/info")
    public String toInfo(Model model){
        String username=getUserPrincipal.getUsername();
        CustomUser customUser=infoService.searchBaseInfo(username);
        Group group=infoService.searchGroupInfo(username);
        model.addAttribute("baseInfo",customUser);
        model.addAttribute("groupInfo",group);
        return "shared/info";
    }





    /**
     * 上传试验
     */
    @RequestMapping("/shared/upload")
    public String uploadPage(){
        return "shared/upload";
    }

    @Autowired
    private FileService fileService;
    @RequestMapping("/shared/uploading")
    public String uploadTest(@RequestParam("file") MultipartFile file){
        fileService.changeFileFormat(file);
        return "redirect:/administrator/allSignInData";
    }

    @Autowired
    SignInService signInService;
    @RequestMapping("/shared/data")
    public String uploadData(Model model){
        List<SignInData> lists=signInService.searchAll();
        model.addAttribute("SignInDateData",lists);
        return "shared/uploadTest";
    }

}
