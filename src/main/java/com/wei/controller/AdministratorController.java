package com.wei.controller;

import com.wei.pojo.CustomUser;
import com.wei.pojo.SignInData;
import com.wei.service.FileService;
import com.wei.service.SignInService;
import com.wei.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AdministratorController {

    @Autowired
    UserInfoService userInfoService;
    //superManager的跳转页面
    //整体学生的统计
    @RequestMapping("/administrator/allStatisticFig")
    public String allStatisticFig(){

        return "level3/StatisticalFigureAll";
    }

    //用户列表
    @RequestMapping("/administrator/UserInfoList")
    public String studentInfoAll(@RequestParam("group_id")int group_id, Model model){
        List<CustomUser> allCustomUsers=userInfoService.getAllUser();
        List<CustomUser> groupUsers=userInfoService.getUserByGroupId(group_id);
        model.addAttribute("AllUserList",allCustomUsers);
        model.addAttribute("GroupUser",groupUsers);
        return "level3/userList";
    }

    /**
     * 添加（Restful风格）
     * 在用户列表页添加“增加用户”按钮，由<a></a>标签跳转到"/administrator/addUser"
     * 在"/administrator/addUser"页面增加“保存”按钮
     * 1.先getMapping,查出用户信息
     * 2.PostMapping,调用方法，保存信息，并且重定向到整个用户列表位置
     */
    @GetMapping("/administrator/addUser")
    public String toAddUserPage(Model model){
        List<CustomUser> customUsersList=userInfoService.getAllUser();
        model.addAttribute("customUsers",customUsersList);
        return "level3/addUser";
    }

    @PostMapping("/administrator/addUser")
    public String addUser(CustomUser customUser){
        //测试是否有值传进来
        System.out.println(customUser);
        //保存添加的用户
        userInfoService.saveUser(customUser);
        //回到用户列表页面
        return "redirect:/administrator/UserInfoList";
    }


    /**
     * 修改用户信息（Restful风格）
     * 1.GetMapping 查出对应id的用户的数据，选中，或者value值（当进入修改页面时，应该对应显示信息）
     * 2.PostMapping 修改后
     */


    /**
     * 文件上传
     */
    @Autowired
    private FileService fileService;
    @Autowired
    SignInService signInService;

    @RequestMapping(value = "/file/upload")
    public String fileUpload(@RequestParam("file")MultipartFile file,Model model){
        fileService.changeFileFormat(file);
        List<SignInData> signInDataList=signInService.searchAll();
        model.addAttribute("allSignInData",signInDataList);
        return "level3/StatisticalFigureAll";
    }

    @RequestMapping("/file/download")
    public ResponseEntity fileDownload() throws Exception{
        FileSystemResource file=new FileSystemResource("C:\\Users\\wxc\\Desktop\\springmanage\\src\\main\\resources\\static\\images\\404.png");
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=SignInData.txt");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

}
