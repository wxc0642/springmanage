package com.wei.controller;

import com.wei.pojo.SignInData;
import com.wei.service.GetUserPrincipal;
import com.wei.service.InfoService;
import com.wei.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<SignInData> signInAllDataList=signInService.searchDataByGAndTime(group_id);
        model.addAttribute("TStudentSignInData",signInAllDataList);
        return "level2/studentInfo";
    }

    @GetMapping("/teacher/studentInfo/{dateRange}")
    public String studentDateInfo(@PathVariable("dateRange")String dateRange,Model model){
        String date1String=dateRange.substring(0,19);
        String date2String=dateRange.substring(22);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
            Date date1=sdf.parse(date1String);
            Date date2=sdf.parse(date2String);
            List<SignInData> signInDataList=signInService.searchDataByTAndGroup(date1,date2);
            for(SignInData signInData:signInDataList){
                System.out.println(signInData);
            }
            model.addAttribute("TStudentSignInData",signInDataList);
        }catch (Exception e){
            e.printStackTrace();
        }
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
