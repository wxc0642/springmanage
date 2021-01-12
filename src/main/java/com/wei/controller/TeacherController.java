package com.wei.controller;

import com.wei.pojo.SignInData;
import com.wei.service.GetUserPrincipal;
import com.wei.service.InfoService;
import com.wei.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
public class TeacherController {

    @Autowired
    InfoService infoService;

    @Autowired
    SignInService signInService;

    @Autowired
    GetUserPrincipal getUserPrincipal;

    //teacher的跳转页面
    @RequestMapping("/teacher/studentInfo")
    public String studentInfo(Model model){
        int group_id=infoService.searchGroupInfo(getUserPrincipal.getUsername()).getGroup_id();
        List<SignInData> customUsers=signInService.searchDataByGAndTime(group_id);
        model.addAttribute("TStudentSignInData",customUsers);
        return "level2/studentInfo";
    }

    @GetMapping("/teacher/studentInfo/{dateRange}")
    public String studentDateInfo(@PathVariable("dateRange")String dateRange,Model model){
        System.out.println(dateRange);
        return "level2/studentDateInfo";
    }



    @RequestMapping("/teacher/takeoffList")
    public String takeOffListM(){
        return "level2/takeoffListM";
    }


    @RequestMapping("/teacher/takeoff")
    public String takeOffM(){
        return "level2/takeoffM";
    }
}
