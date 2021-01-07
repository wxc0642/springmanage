package com.wei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherController {
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
}
