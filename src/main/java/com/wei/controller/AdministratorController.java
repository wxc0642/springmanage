package com.wei.controller;

import com.wei.dao.SignInDao;
import com.wei.pojo.CustomUser;
import com.wei.pojo.Group;
import com.wei.pojo.SignInData;
import com.wei.service.FileService;
import com.wei.service.GroupService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class AdministratorController {

    @Autowired
    UserInfoService userInfoService;
    //superManager的跳转页面
    //整体学生的统计
    @RequestMapping("/administrator/allSignInData")
    public String allStatisticFig(Model model){
        List<SignInData> signInDataList=signInService.searchAll();
        List<CustomUser> customUsers=userInfoService.getAllUser();
        model.addAttribute("allSignInData",signInDataList);
        model.addAttribute("allUsers",customUsers);
        return "level3/signInData";
    }

    //用户列表
    @RequestMapping("/administrator/UserInfoList")
    public String userInfoAll(Model model){
    //public String studentInfoAll(@RequestParam("group_id")int group_id, Model model){
        List<CustomUser> allCustomUsers=userInfoService.getAllUser();
    //    List<CustomUser> groupUsers=userInfoService.getUserByGroupId(group_id);
        model.addAttribute("AllUserList",allCustomUsers);
    //    model.addAttribute("GroupUser",groupUsers);
        return "level3/showUsers";
    }

    /**
     * 添加（Restful风格）
     * 在用户列表页添加“增加用户”按钮，由<a></a>标签跳转到"/administrator/addUser"
     * 在"/administrator/addUser"页面增加“保存”按钮
     * 1.先getMapping,查出用户信息
     * 2.PostMapping,调用方法，保存信息，并且重定向到整个用户列表位置
     */
    @Autowired
    GroupService groupService;
    @GetMapping("/administrator/addUser")
    public String toAddUserPage(Model model){
        List<Group> groups=groupService.getAllGroup();
        model.addAttribute("groups",groups);
        return "level3/addUserForm";
    }

    @PostMapping("/administrator/addUser")
    public String addUser(CustomUser customUser){
        userInfoService.saveUser(customUser);
        //重定向到用户列表页面
        return "redirect:/administrator/UserInfoList";
    }


    /**
     * 修改用户信息（Restful风格）
     * 1.GetMapping 查出对应id的用户的数据，选中，或者value值（当进入修改页面时，应该对应显示信息）
     * 2.PostMapping 修改后
     */
     @GetMapping("/administrator/update/{id}")
     public String toUpdateUser(@PathVariable("id")int id, Model model){
         CustomUser customUser=userInfoService.searchIndividual(id);
         List<Group> groups=groupService.getAllGroup();

         model.addAttribute("individualInfo",customUser);
         model.addAttribute("groups",groups);

         return "level3/update";
     }

     @PostMapping("/administrator/update/{id}")
     public String updateUser(CustomUser customUser){
         userInfoService.saveUser(customUser);
         return "redirect:/administrator/UserInfoList";
     }

     @GetMapping("/administrator/delete/{id}")
     public String deleteUser(@PathVariable("id")int id){
         userInfoService.deleteUser(id);
         return "redirect:/administrator/UserInfoList";
     }

    /**
     * 文件上传，使用@RequestMapping
     */
    @Autowired
    private FileService fileService;
    @Autowired
    SignInService signInService;
    @RequestMapping("/administrator/upload")
    public String fileUpload(@RequestParam("file")MultipartFile file){
        fileService.changeFileFormat(file);
        return "redirect:/administrator/allSignInData";
    }


//    @RequestMapping("/administrator/download")
//    public ResponseEntity fileDownload() throws Exception{
//        FileSystemResource file=new FileSystemResource("C:\\Users\\wxc\\Desktop\\springmanage\\src\\main\\resources\\static\\images\\404.png");
//        HttpHeaders headers=new HttpHeaders();
//        headers.add("Content-Disposition","attachment;filename=SignInData.txt");
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.contentLength())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(new InputStreamResource(file.getInputStream()));
//    }
    @Autowired
    SignInDao signInDao;
    @RequestMapping("/administrator/download")
    public void fileDownload(HttpServletResponse response) throws Exception{
        List<Map<String,Object>> signInDataList=signInDao.searchAll();
        String fileName="SignInData.xls";
        String sheetName="所有数据";
        fileService.exportExcel(response,signInDataList,fileName,sheetName,20);
    }

}
