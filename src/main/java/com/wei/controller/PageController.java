package com.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


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
    @RequestMapping("/info")
    public String toInfo(){
        return "shared/info";
    }


    //student的跳转页面
    @RequestMapping("/student/individual_sta_fig")
    public String searchStaticFig(){
        return "level1/IndividualStatisticalFigure";
    }

    @RequestMapping("/student/takeoffList")
    public String takeOffList(){
        return "level1/takeoffList";
    }

    @RequestMapping("/student/takeoff")
    public String takeOff(){
        return "level1/takeoff";
    }





    //teacher的跳转页面
    @RequestMapping("/teacher/StudentInfo")
    public String studentInfo(){
        return "level2/StudentInfo";
    }
    @RequestMapping("/teacher/takeoffList")
    public String takeOffListM(){
        return "level2/takeoffListM";
    }

    @RequestMapping("/teacher/takeoff")
    public String takeOffM(){
        return "level2/takeoffM";
    }





    //superManager的跳转页面
    //整体学生的统计
    @RequestMapping("/administrator/allStatisticFig")
    public String allStatisticFig(){
        return "level3/StatisticalFigureAll";
    }

    //学生列表
    @RequestMapping("/administrator/StudentInfoAll")
    public String studentInfoAll(){
        return "level3/StudentInfoAll";
    }

    //老师列表
    @RequestMapping("/administrator/TeacherInfo")
    public String teacherInfo(){
        return "level3/TeacherInfo";
    }

}
