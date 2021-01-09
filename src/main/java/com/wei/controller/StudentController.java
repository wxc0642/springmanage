package com.wei.controller;

import com.wei.pojo.SignInData;
import com.wei.service.GetUserPrincipal;
import com.wei.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
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
    @RequestMapping("/student/individual_sta_fig")
    public String searchStaticFig(Model model){
        List<SignInData> studentAllSignInData=signInService.searchAllSignInData(getUserPrincipal.getUsername());
        for(SignInData signInData:studentAllSignInData){
            System.out.println(signInData);
        }
        model.addAttribute("IndividualAllData",studentAllSignInData);
        return "level1/IndividualStatisticalFigure";
    }

    @RequestMapping("/student/individual_sta_fig/date")
    public String searchStaticFig(Model model, Date date1, Date date2){
        List<SignInData> studentSignInDataByTime=signInService.searchAllSignInData(getUserPrincipal.getUsername(),date1,date2);
        model.addAttribute("IndividualDataByTime",studentSignInDataByTime);
        return "level1/DefinedTimeData";
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
