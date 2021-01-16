package com.wei.controller;

import com.wei.pojo.SignInData;
import com.wei.service.GetUserPrincipal;
import com.wei.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    SignInService signInService;


    @Autowired
    GetUserPrincipal getUserPrincipal;

    //student的跳转页面
    @RequestMapping("/student/selfData")
    public String searchStaticFig(Model model){
        List<SignInData> studentAllSignInData=signInService.searchAllSignInData(getUserPrincipal.getUsername());
        model.addAttribute("IndividualAllData",studentAllSignInData);
        return "level1/selfSignInData";
    }

    @GetMapping("/student/selfData/{dateRange}")
    public String searchDateInfo(@PathVariable("dateRange")String dateRange, Model model){
        String username=getUserPrincipal.getUsername();
        String date1String=dateRange.substring(0,19);
        String date2String=dateRange.substring(22);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse(date1String);
            Date date2 = sdf.parse(date2String);
            List<SignInData> lists=signInService.searchAllSignInData(username,date1,date2);
            model.addAttribute("SignInDateData",lists);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "level1/selfDateData";
    }



    @RequestMapping("/student/takeoffList")
    public String takeOffList(){
        return "level1/takeoffList";
    }

    @RequestMapping("/student/takeoff")
    public String takeOff(){
        return "level1/takeoff";
    }
}
